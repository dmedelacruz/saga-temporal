package com.dmedelacruz.sagatemporal.configuration;

import io.temporal.serviceclient.WorkflowServiceStubs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemporalClientConfiguration {

    @Bean
    public WorkflowServiceStubs workflowServiceStubs() {
        return WorkflowServiceStubs.newLocalServiceStubs();
//        return WorkflowServiceStubs.newInstance(
//                WorkflowServiceStubsOptions.newBuilder()
//                        .setTarget(TARGET_ENDPOINT)
//                        .build());
    }

}
