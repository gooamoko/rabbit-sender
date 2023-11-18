package ru.gooamoko.sender.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean("smsQueue")
    public Queue smsQueue(ExchangeConfigProperties properties) {
        return new Queue(properties.getSms().getQueue());
    }

    @Bean("emailQueue")
    public Queue emailQueue(ExchangeConfigProperties properties) {
        return new Queue(properties.getEmail().getQueue());
    }

    @Bean
    public TopicExchange topicExchange(ExchangeConfigProperties properties) {
        return new TopicExchange(properties.getExchange());
    }

    @Bean("smsBinding")
    public Binding smsBinding(@Qualifier("smsQueue") Queue queue, TopicExchange exchange, ExchangeConfigProperties properties) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(properties.getSms().getRoutingKey());
    }

    @Bean("emailBinding")
    public Binding emailBinding(@Qualifier("emailQueue") Queue queue, TopicExchange exchange, ExchangeConfigProperties properties) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(properties.getEmail().getRoutingKey());
    }
}
