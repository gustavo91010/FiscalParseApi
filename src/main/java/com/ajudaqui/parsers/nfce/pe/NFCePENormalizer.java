package com.ajudaqui.parsers.nfce.pe;

import com.ajudaqui.core.model.*;
import com.ajudaqui.core.normalizer.Normalizer;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class NFCePENormalizer implements Normalizer<NFCePERawDocument> {
    
    @Override
    public FiscalDocument normalize(NFCePERawDocument raw) {
        List<Payment> payments = raw.payments().stream()
            .map(m -> new Payment(
                translatePaymentMethod(m.get("method")),
                parseBigDecimal(m.get("amount"))
            ))
            .collect(Collectors.toList());

        BigDecimal totalPaid = payments.stream()
            .map(Payment::amount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new FiscalDocument(
            DocumentType.NFCE,
            State.PE,
            new Issuer(
                raw.rawData().getOrDefault("issuerName", ""),
                "", 
                raw.rawData().getOrDefault("cnpj", ""),
                ""
            ),
            new DocumentInfo(
                DocumentType.NFCE, 
                State.PE, 
                raw.rawData().getOrDefault("accessKey", ""), 
                raw.rawData().getOrDefault("number", ""),
                raw.rawData().getOrDefault("series", ""),
                parseDateTime(raw.rawData().get("emissionDate")),
                ""
            ),
            new Totals(
                parseBigDecimal(raw.rawData().get("totalProducts")),
                parseBigDecimal(raw.rawData().get("totalInvoice")),
                parseBigDecimal(raw.rawData().get("discount")),
                totalPaid,
                parseBigDecimal(raw.rawData().get("change"))
            ),
            payments,
            raw.items().stream().map(m -> new Item(
                m.get("description"),
                m.get("code"),
                parseBigDecimal(m.get("quantity")),
                m.get("unit"),
                parseBigDecimal(m.get("unitValue")),
                parseBigDecimal(m.get("totalValue"))
            )).collect(Collectors.toList())
        );
    }

    private String translatePaymentMethod(String code) {
        if (code == null) return "OUTROS";
        return switch (code) {
            case "01" -> "DINHEIRO";
            case "02" -> "CHEQUE";
            case "03" -> "CARTAO_CREDITO";
            case "04" -> "CARTAO_DEBITO";
            case "05" -> "CREDITO_LOJA";
            case "10" -> "VALE_ALIMENTACAO";
            case "11" -> "VALE_REFEICAO";
            case "12" -> "VALE_PRESENTE";
            case "13" -> "VALE_COMBUSTIVEL";
            case "15" -> "BOLETO_BANCARIO";
            case "90" -> "SEM_PAGAMENTO";
            case "99" -> "OUTROS";
            default -> "OUTROS (" + code + ")";
        };
    }

    private LocalDateTime parseDateTime(String value) {
        if (value == null || value.isBlank()) return null;
        try {
            // NFe costuma usar o formato ISO com offset: 2024-03-20T10:00:00-03:00
            return OffsetDateTime.parse(value).toLocalDateTime();
        } catch (Exception e) {
            try {
                return LocalDateTime.parse(value);
            } catch (Exception e2) {
                return null;
            }
        }
    }

    private BigDecimal parseBigDecimal(String value) {
        if (value == null || value.isBlank()) return BigDecimal.ZERO;
        try {
            // No XML o padrão é internacional (ponto como decimal)
            // Caso venha vírgula (se algum parser HTML for usado no futuro), tratamos aqui
            String cleaned = value.trim().replace(",", ".");
            return new BigDecimal(cleaned);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }
}
