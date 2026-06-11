package com.ajudaqui.inputs;

import com.ajudaqui.core.model.Input;

public record UrlInput(String url) implements Input {
    @Override
    public Object getData() {
        return url;
    }
}
