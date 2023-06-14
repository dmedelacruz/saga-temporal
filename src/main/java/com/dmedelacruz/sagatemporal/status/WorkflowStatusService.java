package com.dmedelacruz.sagatemporal.status;

import com.dmedelacruz.sagatemporal.configuration.SearchAttributes;
import io.temporal.workflow.Workflow;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkflowStatusService {

    public void updateWorkflowStatus(WorkflowStatus workflowStatus) {
        Map<String, List<?>> searchAttributes = Workflow.getSearchAttributes();
        HashMap<String, List<?>> oldAttributes = new HashMap<>(searchAttributes);
        oldAttributes.put(SearchAttributes.WORKFLOW_STATUS.getSearchAttributeKey(), Collections.singletonList(workflowStatus.getStatusMessage()));
        Workflow.upsertSearchAttributes(oldAttributes);
    }

}
