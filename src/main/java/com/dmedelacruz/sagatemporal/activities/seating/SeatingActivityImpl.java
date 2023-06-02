package com.dmedelacruz.sagatemporal.activities.seating;

import com.dmedelacruz.sagatemporal.activities.common.Reversal;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SeatingActivityImpl implements SeatingActivity{
    @Override
    public SeatingActivityResponse updateSeating(SeatingActivityRequest request) {
//        return SeatingActivityResponse.builder()
//                .seatNumber(request.getSeatNumber())
//                .updated(true)
//                .build();
        throw new RuntimeException("Error in update seating");
    }

    @Override
    public SeatingActivityResponse failedUpdateSeating(String seatNumber) {
        return SeatingActivityResponse.builder()
                .reversal(new Reversal(LocalDateTime.now(), "Reversed Seat Number"))
                .build();
    }
}
