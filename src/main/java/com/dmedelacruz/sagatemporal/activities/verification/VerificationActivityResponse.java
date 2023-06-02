package com.dmedelacruz.sagatemporal.activities.verification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.dmedelacruz.sagatemporal.activities.common.Reversal;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VerificationActivityResponse {
    private String verificationId;
    private Boolean verified;
    private String name;
    private Reversal reversal;
}
