package com.example.app.format;

import org.springframework.stereotype.Component;

@Component("push")
public class Push implements MessageFormatter {
    @Override
    public String format(String message) {
        return message.toUpperCase();
    }
}
