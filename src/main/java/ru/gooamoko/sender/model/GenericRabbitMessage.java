package ru.gooamoko.sender.model;

public class GenericRabbitMessage {
    private String user;
    private String service;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
