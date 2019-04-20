package com.example.chat;

public class Message {

    private int userId;
    private String messageText;
    private long dateOfCreation;

    public int getUserId() {
        return userId;
    }

    public String getMessageText() {
        return messageText;
    }

    public long getDateOfCreation() {
        return dateOfCreation;
    }

    public Message(int userId, String messageText, long dateOfCreation) {
        this.userId = userId;
        this.messageText = messageText;
        this.dateOfCreation = dateOfCreation;
    }

    public Message(int userId) {
        this.userId = userId;
    }
}
