package com.dmedelacruz.sagatemporal.configuration;

import com.dmedelacruz.sagatemporal.status.WorkflowStatus;

import java.util.HashMap;
import java.util.Map;

public class SearchAttributeUtil {

    public static Map<String, Object> initializeSearchAttributes() {
        //initialize SearchAttributes Here
        Map<String, Object> searchAttributes = new HashMap<>();
        searchAttributes.put(SearchAttributes.WORKFLOW_STATUS.getSearchAttributeKey(), WorkflowStatus.RUNNING.getStatusMessage());
        return searchAttributes;
    }

}
