package com.dmedelacruz.sagatemporal.activities.payment;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface PaymentActivity {

    @ActivityMethod
    PaymentActivityResponse processPayment(String workflowId, PaymentActivityRequest request);

    @ActivityMethod
    PaymentActivityResponse reversePayment(String receiptNumber, String confirmationNumber);

}
