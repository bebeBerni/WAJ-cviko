package com.example.app;
import com.example.app.format.MessageFormatter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// @Component
@Component("smsNotification")
public class SMSNotification implements NotificationService {

    private final MessageFormatter messageFormatter;

    public SMSNotification(@Qualifier("plain") MessageFormatter messageFormatter) {
        this.messageFormatter = messageFormatter;
    }

    @Override
    public String send(String message) {
        String formattedMessage = messageFormatter.format(message);
        return "Posielam notifikáciu sms: " + formattedMessage;
    }
}
