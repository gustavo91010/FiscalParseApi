package com.ajudaqui.core.parser;

import com.ajudaqui.core.model.Input;

public interface FiscalParser<T extends RawFiscalDocument, I extends Input> {
    T parse(I input);
}
