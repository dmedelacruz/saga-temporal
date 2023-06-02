package com.dmedelacruz.sagatemporal.controller;

import com.dmedelacruz.sagatemporal.saga.BookingSaga;
import com.dmedelacruz.sagatemporal.workflow.booking.BookingWorkflowRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/temporal")
@RequiredArgsConstructor
public class TestController {

//    private final NamespaceService namespaceService;
//    private final WorkflowService workflowService;
//    private final OrderActivityWorker worker;

    private final BookingSaga bookingSaga;

    @PostMapping
    public void createNamespace(@RequestParam("namespace") String namespace, @RequestBody BookingWorkflowRequest request) {
//        worker.registerWorker("test-1", "test-queue");
//        workflowService.registerWorkflow();
//        namespaceService.createNamespace(namespace);
//        workflowService.startWorkflow();

        bookingSaga.processBooking(namespace, request);
    }

}
