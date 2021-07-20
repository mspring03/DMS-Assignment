package com.example.dmsprojectforjava;

public class ServerResponse { // 수신
    String topic;
    String subject;

    public ServerResponse(String topic, String subject) {
        this.topic = topic;
        this.subject = subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
