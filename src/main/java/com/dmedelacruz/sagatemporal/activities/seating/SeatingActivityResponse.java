package com.dmedelacruz.sagatemporal.activities.seating;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.dmedelacruz.sagatemporal.activities.common.Reversal;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatingActivityResponse {
    private String seatNumber;
    private boolean updated;
    private Reversal reversal;
}
