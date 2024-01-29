package ru.gooamoko.sender.rabbit;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.gooamoko.sender.config.ExchangeConfigProperties;
import ru.gooamoko.sender.model.SmsMessage;

@Service
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final RabbitTemplate rabbitTemplate;
    private final ExchangeConfigProperties properties;


    public NotificationService(RabbitTemplate rabbitTemplate, ExchangeConfigProperties properties) {
        this.rabbitTemplate = rabbitTemplate;
        this.properties = properties;
    }

    public void sendSmsNotification(SmsMessage message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String messageText = mapper.writeValueAsString(message);
            rabbitTemplate.convertAndSend(properties.getExchange(), properties.getSms().getRoutingKey(), messageText);
        } catch (Exception e) {
            log.error("Can't serialize object.", e);
        }
    }

    public void sendEmailNotification(String email, String theme, String text) {
        String message = email + "|" + theme + "|" + text;
        rabbitTemplate.convertAndSend(properties.getExchange(), properties.getEmail().getRoutingKey(), message);
    }
}
