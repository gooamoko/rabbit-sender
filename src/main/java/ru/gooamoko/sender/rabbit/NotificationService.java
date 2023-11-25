package ru.gooamoko.sender.rabbit;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.gooamoko.sender.config.ExchangeConfigProperties;

@Service
public class NotificationService {
    private final RabbitTemplate rabbitTemplate;
    private final ExchangeConfigProperties properties;


    public NotificationService(RabbitTemplate rabbitTemplate, ExchangeConfigProperties properties) {
        this.rabbitTemplate = rabbitTemplate;
        this.properties = properties;
    }

    public void sendSmsNotification(String phone, String text) {
        String message = phone + "|" + text;
        rabbitTemplate.convertAndSend(properties.getExchange(), properties.getSms().getRoutingKey(), message);
    }

    public void sendEmailNotification(String email, String theme, String text) {
        String message = email + "|" + theme + "|" + text;
        rabbitTemplate.convertAndSend(properties.getExchange(), properties.getEmail().getRoutingKey(), message);
    }
}
