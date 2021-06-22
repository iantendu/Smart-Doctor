package com.example.smartdoctor;

public class Messages {
    String message;
    String sender;

    public Messages(String message, String sender, String reciever) {
        this.message = message;
        this.sender = sender;
        this.reciever = reciever;
    }

    public Messages() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    String reciever;
}
