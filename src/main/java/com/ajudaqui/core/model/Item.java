package com.ajudaqui.core.model;

import java.math.BigDecimal;

public record Item(
    String description,
    String code,
    BigDecimal quantity,
    String unit,
    BigDecimal unitValue,
    BigDecimal totalValue
) {}
