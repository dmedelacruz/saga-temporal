package com.dmedelacruz.sagatemporal.configuration;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

//@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkflowClientFactory {

    private static final WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
//    private static final WorkflowServiceStubs service = WorkflowServiceStubs.newInstance(
//            WorkflowServiceStubsOptions.newBuilder()
//                    .setTarget(TARGET_ENDPOINT)
//                    .build());

    private static final Map<String, WorkflowClient> workflowClientMap = new HashMap<>();

    public static WorkflowClient getClient(String namespace) {
        WorkflowClient workflowClient = workflowClientMap.get(namespace);
        if(workflowClient == null) {
            workflowClient = WorkflowClient.newInstance(service, WorkflowClientOptions.newBuilder().setNamespace(namespace).build());
            workflowClientMap.put(namespace, workflowClient);
        }
        return workflowClient;
    }

}
