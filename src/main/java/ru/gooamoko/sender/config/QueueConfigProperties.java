package ru.gooamoko.sender.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class QueueConfigProperties {
    private String routingKey;
    private String queue;

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }
}
