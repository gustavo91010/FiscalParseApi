package com.ajudaqui.core.model;

import java.util.List;

public record FiscalDocument(
    DocumentType documentType,
    State state,
    Issuer issuer,
    DocumentInfo document,
    Totals totals,
    List<Payment> payments,
    List<Item> items
) {}
