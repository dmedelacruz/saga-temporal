package com.dmedelacruz.sagatemporal.activities.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.dmedelacruz.sagatemporal.activities.common.Reversal;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentActivityResponse {
    private String receiptNumber;
    private LocalDateTime paymentDate;
    private String confirmationNumber;
    private Reversal reversal;
}
