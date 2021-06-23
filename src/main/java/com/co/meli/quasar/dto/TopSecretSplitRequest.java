package com.co.meli.quasar.dto;

import java.util.List;

public class TopSecretSplitRequest {
    private double distance;
    private List<String> message;

    public TopSecretSplitRequest(double distance, List<String> message) {
        this.distance = distance;
        this.message = message;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
