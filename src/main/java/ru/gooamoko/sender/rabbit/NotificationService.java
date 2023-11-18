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

    public void sendSmsNotification(String message) {
        // TODO: 18.11.2023 Добавить номер телефона
        rabbitTemplate.convertAndSend(properties.getExchange(), properties.getSms().getRoutingKey(), message);
    }

    public void sendEmailNotification(String message) {
        // TODO: 18.11.2023 Добавить адрес почты и тему письма
        rabbitTemplate.convertAndSend(properties.getExchange(), properties.getEmail().getRoutingKey(), message);
    }
}
