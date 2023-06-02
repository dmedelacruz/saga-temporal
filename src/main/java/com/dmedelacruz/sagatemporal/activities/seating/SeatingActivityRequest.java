package com.dmedelacruz.sagatemporal.activities.seating;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatingActivityRequest {
    private String name;
    private String seatNumber;
}
