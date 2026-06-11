package com.ajudaqui.core.normalizer;

import com.ajudaqui.core.model.DocumentType;
import com.ajudaqui.core.model.State;
import com.ajudaqui.parsers.nfce.pe.NFCePENormalizer;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NormalizerFactory {
    public Normalizer<?> getNormalizer(DocumentType type, State state) {
        if (type == DocumentType.NFCE && state == State.PE) {
            return new NFCePENormalizer();
        }
        throw new UnsupportedOperationException("Normalizer não suportado para " + type + " em " + state);
    }
}
