package com.example.chat.adapter;


import com.example.chat.Message;

public class BlurbMessage implements MessageRecyclerCell {

    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public BlurbMessage(Message message) {
        this.message = message;
    }
}
