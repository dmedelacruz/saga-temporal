package com.dmedelacruz.sagatemporal.activities.seating;

import com.dmedelacruz.sagatemporal.activities.common.Reversal;
import com.dmedelacruz.sagatemporal.activities.payment.PaymentVerification;
import com.dmedelacruz.sagatemporal.activities.payment.PaymentVerificationUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SeatingActivityImpl implements SeatingActivity{
    @Override
    public SeatingActivityResponse updateSeating(String workflowId, SeatingActivityRequest request) {
        PaymentVerification paymentVerification = PaymentVerificationUtil.getPaymentVerification(workflowId);

        if(paymentVerification.getIsVerified()) {
            return SeatingActivityResponse.builder()
                    .seatNumber(request.getSeatNumber())
                    .updated(true)
                    .build();
        }

        if(paymentVerification.getIsRejected()) {
            throw new RuntimeException("Payment Rejected");
        }

        throw new RuntimeException("Unknown Error");

    }

    @Override
    public SeatingActivityResponse failedUpdateSeating(String seatNumber) {
        return SeatingActivityResponse.builder()
                .reversal(new Reversal(LocalDateTime.now(), "Reversed Seat Number"))
                .build();
    }
}
