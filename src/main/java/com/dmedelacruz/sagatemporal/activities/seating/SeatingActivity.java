package com.dmedelacruz.sagatemporal.activities.seating;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface SeatingActivity {

    @ActivityMethod
    SeatingActivityResponse updateSeating(SeatingActivityRequest request);

    @ActivityMethod
    SeatingActivityResponse failedUpdateSeating(String seatNumber);
}
