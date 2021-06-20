package com.co.meli.quasar.entity;

public class Space extends Node{
    private String message;

    public Space(Position position, String message) {
        this.message = message;
        this.setPosition(position);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
