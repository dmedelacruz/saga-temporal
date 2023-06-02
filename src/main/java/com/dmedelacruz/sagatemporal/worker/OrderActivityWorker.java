package com.dmedelacruz.sagatemporal.worker;

import com.dmedelacruz.sagatemporal.configuration.WorkflowClientFactory;
import io.temporal.client.WorkflowClient;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderActivityWorker {

    public Worker createWorker(String namespace, String taskQueue, List<Class<?>> workflows, List<Object> activities) {

        WorkflowClient client = WorkflowClientFactory.getClient(namespace);
        WorkerFactory workerFactory = WorkerFactory.newInstance(client);
        Worker worker = workerFactory.newWorker(taskQueue);
        worker.registerWorkflowImplementationTypes(workflows.toArray(Class[]::new));
        worker.registerActivitiesImplementations(activities.toArray(Object[]::new));

        workerFactory.start();

        return worker;
    }

//    public void registerWorker(String namespace, String taskQueue) {
//
//        /*
//         * TRIGGERED WORKFLOW EXECUTION
//         * Create Get Workflow Client
//         * Create a Worker Given a taskQueue
//         * Register Workflow implementations
//         * Register ActivitiesImplementations
//         * call start();
//         */
//
//        WorkflowClient client = WorkflowClientFactory.getClient(namespace);
//        WorkerFactory workerFactory = WorkerFactory.newInstance(client);
//        Worker worker = workerFactory.newWorker(taskQueue);
//        worker.registerWorkflowImplementationTypes(OrderWorkflow.class);
//        worker.registerActivitiesImplementations(new ProcessOrderActivityImpl());
//
//        workerFactory.start(); //THIS STARTS THE WORKFLOW
//
//    }

}
