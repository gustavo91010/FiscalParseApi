package com.ajudaqui.core.model;

import java.math.BigDecimal;

public record Payment(
    String method,
    BigDecimal amount
) {}
