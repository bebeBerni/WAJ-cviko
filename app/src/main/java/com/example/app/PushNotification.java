package com.example.app;
import com.example.app.format.MessageFormatter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// @Component
@Component("pushNotification")
public class PushNotification implements NotificationService {

    private final MessageFormatter messageFormatter;

    public PushNotification(@Qualifier("push") MessageFormatter messageFormatter) {
        this.messageFormatter = messageFormatter;
    }
    @Override
    public String send(String message) {
        String formattedMessage = messageFormatter.format(message);
        return "Posielam notifikáciu push: " + formattedMessage;
    }
}
