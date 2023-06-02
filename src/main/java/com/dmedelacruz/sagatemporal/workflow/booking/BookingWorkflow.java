package com.dmedelacruz.sagatemporal.workflow.booking;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface BookingWorkflow {

    /*
     * Indicates the start of workflow execution
     * Workflow completes when this method completes
     */
    @WorkflowMethod
    BookingWorkflowResponse processBooking(BookingWorkflowRequest request);

}
