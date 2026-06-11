package com.ajudaqui.parsers.nfce.pe;

import com.ajudaqui.core.parser.RawFiscalDocument;
import java.util.Map;
import java.util.List;

public record NFCePERawDocument(
    Map<String, String> rawData,
    List<Map<String, String>> items,
    List<Map<String, String>> payments
) implements RawFiscalDocument {}
