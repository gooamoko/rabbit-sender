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

    @Bean("paymentsConfig")
    @ConfigurationProperties("payments")
    public QueueConfigProperties queueProperties() {
        return new QueueConfigProperties();
    }


    @Bean
    public Queue queue(@Qualifier("paymentsConfig") QueueConfigProperties properties) {
        return new Queue(properties.getQueue());
    }

    @Bean
    public TopicExchange topicExchange(@Qualifier("paymentsConfig") QueueConfigProperties properties) {
        return new TopicExchange(properties.getExchange());
    }

    @Bean
    public Binding phonePaymentsBinding(Queue queue, TopicExchange exchange, QueueConfigProperties properties) {
        return BindingBuilder.bind(queue).to(exchange).with(properties.getRoutingKey());
    }
}
