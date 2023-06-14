package com.dmedelacruz.sagatemporal.activities.payment;

import com.dmedelacruz.sagatemporal.activities.common.Reversal;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class PaymentActivityImpl implements PaymentActivity {
    @Override
    public PaymentActivityResponse processPayment(String workflowId, PaymentActivityRequest request) {

        PaymentVerificationUtil.createPaymentVerificationRecord(workflowId, true);

        String receiptNumber = UUID.randomUUID().toString();
        LocalDateTime paymentDate = LocalDateTime.now();
        String confirmationNumber = UUID.randomUUID().toString();
        return PaymentActivityResponse.builder()
                .receiptNumber(receiptNumber)
                .paymentDate(paymentDate)
                .confirmationNumber(confirmationNumber)
                .build();
    }

    @Override
    public PaymentActivityResponse reversePayment(String receiptNumber, String confirmationNumber) {
        return PaymentActivityResponse.builder()
                .reversal(new Reversal(LocalDateTime.now(), "Reversed Payment for receipt: " + receiptNumber))
                .build();
    }

}
