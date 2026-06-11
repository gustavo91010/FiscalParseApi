package com.ajudaqui.core.model;

import java.time.LocalDateTime;

public record DocumentInfo(
    DocumentType type,
    State state,
    String accessKey,
    String number,
    String series,
    LocalDateTime emissionDate,
    String protocol
) {}
