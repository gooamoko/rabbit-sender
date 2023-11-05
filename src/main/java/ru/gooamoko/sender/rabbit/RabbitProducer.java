package ru.gooamoko.sender.rabbit;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.gooamoko.sender.config.QueueConfigProperties;

@Service
public class RabbitProducer {
    private final RabbitTemplate rabbitTemplate;
    private final QueueConfigProperties properties;


    public RabbitProducer(RabbitTemplate rabbitTemplate, QueueConfigProperties properties) {
        this.rabbitTemplate = rabbitTemplate;
        this.properties = properties;
    }

    public void send(String message) {
        rabbitTemplate.convertAndSend(properties.getExchange(), properties.getRoutingKey(), message);
    }
}
