package com.dmedelacruz.sagatemporal.configuration;

public enum SearchAttributes {


    WORKFLOW_STATUS("Workflow Status");

    private String searchAttributeKey;

    SearchAttributes(String searchAttributeKey) {
        this.searchAttributeKey = searchAttributeKey;
    }

    public String getSearchAttributeKey() {
        return searchAttributeKey;
    }
}
