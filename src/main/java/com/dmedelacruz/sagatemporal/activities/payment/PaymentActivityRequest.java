package com.dmedelacruz.sagatemporal.activities.payment;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentActivityRequest {
    private String name;
    private String verificationId;
    private boolean verified;
}
