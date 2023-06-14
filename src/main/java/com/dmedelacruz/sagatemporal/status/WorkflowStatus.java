package com.dmedelacruz.sagatemporal.status;

public enum WorkflowStatus {
    RUNNING("Running"),
    SUCCESS("Successful"),
    PENDING_APPROVAL("Pending Approval"),
    COMPLETED_WITH_ERRORS("Completed With Errors"),
    FAILED("FAILED");

    private String statusMessage;

    WorkflowStatus(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
