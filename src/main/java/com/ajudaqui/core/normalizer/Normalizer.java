package com.ajudaqui.core.normalizer;

import com.ajudaqui.core.model.FiscalDocument;
import com.ajudaqui.core.parser.RawFiscalDocument;

public interface Normalizer<T extends RawFiscalDocument> {
    FiscalDocument normalize(T raw);
}
