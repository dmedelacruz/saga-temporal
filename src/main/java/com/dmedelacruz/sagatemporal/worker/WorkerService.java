package com.dmedelacruz.sagatemporal.worker;

import io.temporal.client.WorkflowClient;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    /*
     * Start A Worker
     */
    public void startWorker(WorkflowClient client, String taskQueue, List<Class<?>> workflows, List<Object> activities) {
        WorkerFactory workerFactory = WorkerFactory.newInstance(client);
        Worker worker = workerFactory.newWorker(taskQueue);
        worker.registerWorkflowImplementationTypes(workflows.toArray(Class[]::new));
        worker.registerActivitiesImplementations(activities.toArray(Object[]::new));
        workerFactory.start();
    }

    /*
     * Schedule a Worker
     */

}
