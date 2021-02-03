package com.rest.webservice.thetodoapprestapi.basic.auth;

public class Authentication {
    private String message;

    public Authentication(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("HelloWold [message=%s]", message);
    }
}