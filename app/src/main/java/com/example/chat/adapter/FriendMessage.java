package com.example.chat.adapter;
        import com.example.chat.Message;

public class FriendMessage implements MessageRecyclerCell {

    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public FriendMessage(Message message) {
        this.message = message;
    }
}
