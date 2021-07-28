package com.example.dmsprojectforjava;

import androidx.annotation.NonNull;

public class ServerResponse { // 수신
    private String topic;
    private String subject;

    public ServerResponse(String topic, String subject) {
        this.topic = topic;
        this.subject = subject;
    }

    @NonNull

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
