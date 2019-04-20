package com.example.chat.adapter;


import com.example.chat.Message;

public class OwnMessage implements MessageRecyclerCell {

    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public OwnMessage(Message message) {
        this.message = message;
    }
}
