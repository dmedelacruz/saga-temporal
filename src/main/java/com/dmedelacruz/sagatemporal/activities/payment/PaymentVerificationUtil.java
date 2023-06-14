package com.dmedelacruz.sagatemporal.activities.payment;

import java.util.HashMap;
import java.util.Map;

public class PaymentVerificationUtil {

    private static final Map<String, PaymentVerification> paymentVerificationMap = new HashMap<>();

    public static PaymentVerification createPaymentVerificationRecord(String workflowId, Boolean needsManualVerification) {
        if(paymentVerificationMap.get(workflowId) == null) {
           PaymentVerification paymentVerification = new PaymentVerification(workflowId, needsManualVerification);
           paymentVerificationMap.put(workflowId, paymentVerification);
           return paymentVerification;
        } else {
            throw new IllegalArgumentException("Payment Verification Already Exists");
        }
    }

    public static PaymentVerification getPaymentVerification(String workflowId) {
        PaymentVerification paymentVerification = paymentVerificationMap.get(workflowId);
        if (paymentVerification == null) {
            throw new IllegalArgumentException("No Record Found");
        }
        return paymentVerification;
    }

    public static void verifyPayment(String workflowId) {
        if (paymentVerificationMap.get(workflowId) == null) {
            throw new IllegalArgumentException("No Record Found");
        } else {
            paymentVerificationMap.get(workflowId).verify();
        }
    }

    public static void rejectPayment(String workflowId) {
        if (paymentVerificationMap.get(workflowId) == null) {
            throw new IllegalArgumentException("No Record Found");
        } else {
            paymentVerificationMap.get(workflowId).reject();
        }
    }
}
