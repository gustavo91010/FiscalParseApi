package com.ajudaqui.core.factory;

import com.ajudaqui.core.model.DocumentType;
import com.ajudaqui.core.model.State;
import com.ajudaqui.core.parser.FiscalParser;
import com.ajudaqui.parsers.nfce.pe.NFCePEParser;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ParserFactory {
    public FiscalParser<?, ?> getParser(DocumentType type, State state) {
        if (type == DocumentType.NFCE && state == State.PE) {
            return new NFCePEParser();
        }
        throw new UnsupportedOperationException("Parser não suportado para " + type + " em " + state);
    }
}
