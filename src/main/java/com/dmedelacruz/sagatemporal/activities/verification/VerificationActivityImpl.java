package com.dmedelacruz.sagatemporal.activities.verification;

import com.dmedelacruz.sagatemporal.activities.common.Reversal;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class VerificationActivityImpl implements VerificationActivity{
    @Override
    public VerificationActivityResponse verify(VerificationActivityRequest request) {
        String verificationId = UUID.randomUUID().toString();
        return VerificationActivityResponse.builder()
                .verificationId(verificationId)
                .verified(true)
                .name(request.getName())
                .build();
    }

    @Override
    public VerificationActivityResponse reverseVerification(String verificationId) {
        return VerificationActivityResponse.builder()
                .reversal(new Reversal(LocalDateTime.now(), "Reversed Verification for: " + verificationId))
                .build();
    }
}
