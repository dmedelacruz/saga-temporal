package com.dmedelacruz.sagatemporal.activities.verification;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface VerificationActivity {

    @ActivityMethod
    VerificationActivityResponse verify(VerificationActivityRequest request);

    @ActivityMethod
    VerificationActivityResponse reverseVerification(String verificationId);
}
