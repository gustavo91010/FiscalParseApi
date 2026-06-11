package com.ajudaqui.core.model;

import java.math.BigDecimal;

public record Totals(
    BigDecimal totalProducts,
    BigDecimal totalInvoice,
    BigDecimal discount,
    BigDecimal totalPaid,
    BigDecimal change
) {}
