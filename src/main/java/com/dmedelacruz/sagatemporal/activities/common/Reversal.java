package com.dmedelacruz.sagatemporal.activities.common;

import java.time.LocalDateTime;

public record Reversal(LocalDateTime reversalDate, String reason) {
}
