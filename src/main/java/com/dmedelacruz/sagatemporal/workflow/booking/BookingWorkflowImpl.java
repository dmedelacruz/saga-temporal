package com.dmedelacruz.sagatemporal.workflow.booking;

import com.dmedelacruz.sagatemporal.activities.payment.*;
import com.dmedelacruz.sagatemporal.activities.seating.SeatingActivity;
import com.dmedelacruz.sagatemporal.activities.seating.SeatingActivityRequest;
import com.dmedelacruz.sagatemporal.activities.seating.SeatingActivityResponse;
import com.dmedelacruz.sagatemporal.activities.verification.VerificationActivity;
import com.dmedelacruz.sagatemporal.activities.verification.VerificationActivityRequest;
import com.dmedelacruz.sagatemporal.activities.verification.VerificationActivityResponse;
import com.dmedelacruz.sagatemporal.status.WorkflowStatus;
import com.dmedelacruz.sagatemporal.status.WorkflowStatusService;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.failure.ActivityFailure;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class BookingWorkflowImpl implements BookingWorkflow {

    private final RetryOptions retryoptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(100))
            .setBackoffCoefficient(2)
            .setMaximumAttempts(1)
            .build();

    private final ActivityOptions defaultActivityOptions = ActivityOptions.newBuilder()
            // Timeout options specify when to automatically timeout Activities if the process is taking too long.
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            // Optionally provide customized RetryOptions.
            // Temporal retries failures by default, but you can have your own retry configuration.
            .setRetryOptions(retryoptions)
            .build();

    private final WorkflowStatusService workflowStatusService = new WorkflowStatusService();

    private final VerificationActivity verificationActivity = Workflow.newActivityStub(VerificationActivity.class, defaultActivityOptions);
    private final PaymentActivity paymentActivity = Workflow.newActivityStub(PaymentActivity.class, defaultActivityOptions);
    private final SeatingActivity seatingActivity = Workflow.newActivityStub(SeatingActivity.class, defaultActivityOptions);

    @Override
    public BookingWorkflowResponse processBooking(String workflowId, BookingWorkflowRequest request) {

        Saga.Options sagaOptions = new Saga.Options.Builder().setParallelCompensation(true).build();
        Saga saga = new Saga(sagaOptions);

        try {

            //Verify -> Payment -> Update Seating
            VerificationActivityResponse verificationActivityResponse = verificationActivity.verify(VerificationActivityRequest.builder()
                    .name(request.getName())
                    .build());
            saga.addCompensation(() -> verificationActivity.reverseVerification(verificationActivityResponse.getVerificationId()));

            PaymentActivityResponse paymentResponse = paymentActivity.processPayment(workflowId, PaymentActivityRequest.builder()
                    .name(verificationActivityResponse.getName())
                    .verificationId(verificationActivityResponse.getVerificationId())
                    .verified(verificationActivityResponse.getVerified())
                    .build());
            saga.addCompensation(() -> paymentActivity.reversePayment(paymentResponse.getReceiptNumber(), paymentResponse.getConfirmationNumber()));

            PaymentVerification paymentVerification = PaymentVerificationUtil.getPaymentVerification(workflowId);
            if (paymentVerification.getIsWaiting()) {
                System.out.println("PAYMENT VERIFICATION STATUS: " + paymentVerification.getIsWaiting());
                workflowStatusService.updateWorkflowStatus(WorkflowStatus.PENDING_APPROVAL);
                Workflow.await(() -> !paymentVerification.getIsWaiting());
            }

            SeatingActivityResponse seatingResponse = seatingActivity.updateSeating(workflowId, SeatingActivityRequest.builder()
                    .seatNumber(request.getSeatNumber())
                    .name(request.getName())
                    .build());
            saga.addCompensation(() -> seatingActivity.failedUpdateSeating(seatingResponse.getSeatNumber()));

        } catch (ActivityFailure | InterruptedException activityFailure) {
            saga.compensate();
            workflowStatusService.updateWorkflowStatus(WorkflowStatus.COMPLETED_WITH_ERRORS);
            return BookingWorkflowResponse.builder().success(false).build();
        }

        workflowStatusService.updateWorkflowStatus(WorkflowStatus.SUCCESS);

        return BookingWorkflowResponse.builder().success(true).build();
    }

    @Override
    public void updatePayment(String workflowId, Boolean isApproved) {
        if(isApproved) {
            PaymentVerificationUtil.verifyPayment(workflowId);
        } else {
            PaymentVerificationUtil.rejectPayment(workflowId);
        }
    }
}
