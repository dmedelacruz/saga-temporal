package com.dmedelacruz.sagatemporal.saga;

import com.dmedelacruz.sagatemporal.activities.payment.PaymentActivity;
import com.dmedelacruz.sagatemporal.activities.seating.SeatingActivity;
import com.dmedelacruz.sagatemporal.activities.verification.VerificationActivity;
import com.dmedelacruz.sagatemporal.configuration.WorkflowClientFactory;
import com.dmedelacruz.sagatemporal.worker.WorkerService;
import com.dmedelacruz.sagatemporal.workflow.booking.BookingWorkflow;
import com.dmedelacruz.sagatemporal.workflow.booking.BookingWorkflowImpl;
import com.dmedelacruz.sagatemporal.workflow.booking.BookingWorkflowRequest;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingSaga {

    private static final String BOOKING_TASK_QUEUE_PREFIX = "booking-";

    private final PaymentActivity paymentActivity;
    private final SeatingActivity seatingActivity;
    private final VerificationActivity verificationActivity;

    private final WorkerService workerService;


    public void processBooking(String namespace, BookingWorkflowRequest bookingWorkflowRequest) {

        WorkflowClient client = WorkflowClientFactory.getClient(namespace);
        String taskQueue = BOOKING_TASK_QUEUE_PREFIX + UUID.randomUUID();

        List<Class<?>> workflowList = List.of(BookingWorkflowImpl.class);
        List<Object> activities = Arrays.asList(verificationActivity, paymentActivity, seatingActivity);

        //Start A Worker Instance
        workerService.startWorker(client, taskQueue, workflowList, activities);

        //Create A Workflow Instance
        WorkflowOptions options = WorkflowOptions.newBuilder().setTaskQueue(taskQueue).build();
//        WorkflowOptions options = WorkflowOptions.newBuilder().setCronSchedule().setTaskQueue(taskQueue).build();
        BookingWorkflow bookingWorkflow = client.newWorkflowStub(BookingWorkflow.class, options);

        //Start Workflow
        bookingWorkflow.processBooking(bookingWorkflowRequest);
    }

}
