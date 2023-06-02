package com.dmedelacruz.sagatemporal.namespace;//package com.martrust.com.dmedelacruz.temporal.namespace;
//
//import com.google.protobuf.util.Durations;
//import io.temporal.api.workflowservice.v1.RegisterNamespaceRequest;
//import io.temporal.api.workflowservice.v1.RegisterNamespaceResponse;
//import io.temporal.serviceclient.WorkflowServiceStubs;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class NamespaceServiceImpl implements NamespaceService {
//
//    private final WorkflowServiceStubs service;
//
//    @Override
//    public void createNamespace(String namespace) {
//
//        RegisterNamespaceRequest req = RegisterNamespaceRequest.newBuilder()
//                .setNamespace(namespace)
//                .setWorkflowExecutionRetentionPeriod(Durations.fromDays(3)) // keeps the Workflow Execution
//                //Event History for up to 3 days in the Persistence store. Not setting this value will throw an error.
//                .build();
//        RegisterNamespaceResponse registerNamespaceResponse = service.blockingStub().registerNamespace(req);
//        System.out.println(registerNamespaceResponse);
//
//    }
//
//}
