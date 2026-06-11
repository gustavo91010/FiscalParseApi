package com.ajudaqui.core.model;

public record Issuer(
    String businessName,
    String tradeName,
    String cnpj,
    String address
) {}
