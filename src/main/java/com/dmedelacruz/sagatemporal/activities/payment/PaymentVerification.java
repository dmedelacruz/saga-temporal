package com.dmedelacruz.sagatemporal.activities.payment;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PaymentVerification {

    private final String workflowId;
    private final Boolean needsManualVerification;
    private Boolean isVerified;
    private Boolean isRejected;
    private Boolean isWaiting;

    public PaymentVerification(String workflowId, Boolean needsManualVerification) {
        this.workflowId = workflowId;
        this.needsManualVerification = needsManualVerification;
        if(!needsManualVerification) {
            this.isVerified = Boolean.TRUE;
            this.isWaiting = Boolean.FALSE;
        } else {
            this.isVerified = Boolean.FALSE;
            this.isWaiting = Boolean.TRUE;
        }
        this.isRejected = Boolean.FALSE;
    }

    protected PaymentVerification verify() {
        if(this.isRejected) {
            throw new IllegalArgumentException("Payment Already Rejected");
        }
        this.isVerified = Boolean.TRUE;
        turnOffWaiting();
        return this;
    }

    protected PaymentVerification reject() {
        if(this.isRejected) {
            throw new IllegalArgumentException("Payment Already Rejected");
        }
        this.isRejected = Boolean.TRUE;
        this.isVerified = Boolean.FALSE;
        turnOffWaiting();
        return this;
    }

    private void turnOffWaiting() {
        this.isWaiting = Boolean.FALSE;
    }
}
