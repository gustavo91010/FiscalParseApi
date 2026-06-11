package com.ajudaqui.core.service;

import com.ajudaqui.core.detector.DocumentDetector;
import com.ajudaqui.core.factory.ParserFactory;
import com.ajudaqui.core.model.FiscalDocument;
import com.ajudaqui.core.model.Input;
import com.ajudaqui.core.normalizer.Normalizer;
import com.ajudaqui.core.normalizer.NormalizerFactory;
import com.ajudaqui.core.parser.FiscalParser;
import com.ajudaqui.core.parser.RawFiscalDocument;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FiscalParseService {

    @Inject
    DocumentDetector detector;

    @Inject
    ParserFactory parserFactory;

    @Inject
    NormalizerFactory normalizerFactory;

    public FiscalDocument parse(Input input) {
        DocumentDetector.DetectionResult result = detector.detect(input);
        if (result == null) {
            throw new IllegalArgumentException("Não foi possível detectar o tipo de documento.");
        }

        FiscalParser parser = parserFactory.getParser(result.type(), result.state());
        RawFiscalDocument raw = (RawFiscalDocument) parser.parse(input);
        
        Normalizer normalizer = normalizerFactory.getNormalizer(result.type(), result.state());
        return normalizer.normalize(raw);
    }
}
