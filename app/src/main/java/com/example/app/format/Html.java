package com.example.app.format;

// implementácia

import org.springframework.stereotype.Component;

@Component("html")
public class Html implements MessageFormatter {
    @Override
    public String format(String message) {
        return "<html><body><h2>" + message + "</h2></body></html>";
    }
}
