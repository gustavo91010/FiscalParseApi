package com.ajudaqui.core.detector;

import com.ajudaqui.core.model.DocumentType;
import com.ajudaqui.core.model.Input;
import com.ajudaqui.core.model.State;
import com.ajudaqui.inputs.UrlInput;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DocumentDetector {
    public record DetectionResult(DocumentType type, State state) {}

    public DetectionResult detect(Input input) {
        if (input instanceof UrlInput urlInput) {
            String url = urlInput.url();

            if (url.contains("sefaz.pb.gov.br")) {
                if (url.contains("nfce")) {
                    return new DetectionResult(DocumentType.NFCE, State.PB);
                }
            }
            if (url.contains("sefaz.pe.gov.br")) {
                if (url.contains("nfce")) {
                    return new DetectionResult(DocumentType.NFCE, State.PE);
                }
            }
        }
        return null;
    }
}
