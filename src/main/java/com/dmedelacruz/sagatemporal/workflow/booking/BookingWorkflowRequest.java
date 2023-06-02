package com.dmedelacruz.sagatemporal.workflow.booking;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingWorkflowRequest {
    private String name;
    private String seatNumber;
    private String email;
}
