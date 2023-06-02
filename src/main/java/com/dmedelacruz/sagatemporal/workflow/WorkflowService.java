package com.dmedelacruz.sagatemporal.workflow;//package com.martrust.com.dmedelacruz.temporal.workflow;
//
//import com.martrust.com.dmedelacruz.temporal.configuration.WorkflowClientFactory;
//import io.temporal.client.WorkflowClient;
//import io.temporal.client.WorkflowClientOptions;
//import io.temporal.client.WorkflowOptions;
//import io.temporal.serviceclient.WorkflowServiceStubs;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class WorkflowService {
//
//    private final WorkflowClientFactory workflowClientFactory;
//
//    public void registerWorkflow() {
//
//        WorkflowClient client = workflowClientFactory.getClient("test-1");
//
//        LogisticsWorkflow logisticsWorkflow = client.newWorkflowStub(LogisticsWorkflow.class,
//                WorkflowOptions.newBuilder()
//                        .setWorkflowId("logistics-workflow-1")
//                        .setTaskQueue("logistis-taskqueue-1")
//                        .build()
//        );
//
//        WorkflowClient.start(logisticsWorkflow::processLogistics);
//    }
//
//    public void startWorkflow() {
//
//        WorkflowClient client = workflowClientFactory.getClient("test-1");
//        LogisticsWorkflow logisticsWorkflow = client.newWorkflowStub(LogisticsWorkflow.class,
//                WorkflowOptions.newBuilder()
//                        .setWorkflowId("logistics-workflow-1")
//                        .setTaskQueue("logistis-taskqueue-1")
//                        .build()
//        );
//        logisticsWorkflow.processLogistics();
//
//    }
//
//}
