package com.dmedelacruz.sagatemporal.activities.seating;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface SeatingActivity {

    @ActivityMethod
    SeatingActivityResponse updateSeating(String workflowId, SeatingActivityRequest request) throws InterruptedException;

    @ActivityMethod
    SeatingActivityResponse failedUpdateSeating(String seatNumber);
}
