package com.dmedelacruz.sagatemporal.activities.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdatePaymentVerificationRequest {
    private String workflowId;
    private Boolean isApproved;
}
