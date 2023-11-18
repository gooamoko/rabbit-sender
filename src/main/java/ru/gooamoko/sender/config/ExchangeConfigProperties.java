package ru.gooamoko.sender.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Класс для конфигурационных параметров очереди
 */
@ConfigurationProperties("notifications")
public class ExchangeConfigProperties {
    private String exchange;
    private QueueConfigProperties sms;
    private QueueConfigProperties email;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public QueueConfigProperties getSms() {
        return sms;
    }

    public void setSms(QueueConfigProperties sms) {
        this.sms = sms;
    }

    public QueueConfigProperties getEmail() {
        return email;
    }

    public void setEmail(QueueConfigProperties email) {
        this.email = email;
    }
}
