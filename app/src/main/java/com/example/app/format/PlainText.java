package com.example.app.format;

import org.springframework.stereotype.Component;

@Component("plain")
public class PlainText implements MessageFormatter {
    @Override
    public String format(String message) {
        return message;
    }
}
