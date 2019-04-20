package com.example.chat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chat.GeneralUtils;
import com.example.chat.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.BaseHolder> {

    private static final int OWN_MESSAGE = 0;
    private static final int FRIEND_MESSAGE = 1;
    private static final int BLURB_MESSAGE = 2;

    public interface MessageListener {
        void onMessage(int id);
    }
    private final MessageListener listener;
    private final List<MessageRecyclerCell> items = new ArrayList<>();
    private final Context context;

    public MessageAdapter(Context context,MessageListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void swapData(List<MessageRecyclerCell> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == OWN_MESSAGE) {
            return new OwnMessageHolder(inflater.inflate(R.layout.own_message_list_item, parent, false));
        } else if (viewType == FRIEND_MESSAGE) {
           return new FriendMessageHolder(inflater.inflate(R.layout.friend_message_list_item, parent, false));
        } else if (viewType ==BLURB_MESSAGE) {
            return new BlurbMessageHolder(inflater.inflate(R.layout.blurb_message_list_item, parent, false));
        }else{
            throw new IllegalStateException();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        MessageRecyclerCell recyclerViewItem = items.get(position);
        if (recyclerViewItem instanceof OwnMessage) {
            return OWN_MESSAGE;
        } else if (recyclerViewItem instanceof FriendMessage) {
            return FRIEND_MESSAGE;
        } else if (recyclerViewItem instanceof BlurbMessage) {
            return BLURB_MESSAGE;
        }else{
            return super.getItemViewType(position);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class FriendMessageHolder extends BaseHolder {
        private final ImageView friendIconImageView;
        private final TextView friendMessageTextView;
        private final TextView friendDateCreationTextView;

        FriendMessageHolder(View itemView) {
            super(itemView);
            friendMessageTextView = itemView.findViewById(R.id.friend_message_text);
            friendIconImageView = itemView.findViewById(R.id.friend_icon);
            friendDateCreationTextView = itemView.findViewById(R.id.friend_date_of_creation);
        }

        @Override
        void bind(MessageRecyclerCell message) {
            FriendMessage friendMessage = (FriendMessage) message;
            friendMessageTextView.setText(friendMessage.getMessage().getMessageText());
            Picasso.with(context).load(R.drawable.cat2).into(friendIconImageView);

            String day = GeneralUtils.getDay(friendMessage.getMessage());
            String month = GeneralUtils.getMonth(friendMessage.getMessage());
            String year = GeneralUtils.getYear(friendMessage.getMessage());
            String hour = GeneralUtils.getHours(friendMessage.getMessage());
            String minutes = GeneralUtils.getMinutes(friendMessage.getMessage());
            String dateCreated = day + " " + month + " " + year + "   " + hour + ":" + minutes;
            friendDateCreationTextView.setText(dateCreated);
        }
    }

    private class OwnMessageHolder extends BaseHolder {

        private final ImageView ownIconImageView;
        private final TextView ownMessageTextView;
        private final TextView ownDateCreationTextView;

        OwnMessageHolder(View itemView) {
            super(itemView);
            ownMessageTextView = itemView.findViewById(R.id.own_message_text);
            ownIconImageView = itemView.findViewById(R.id.own_icon);
            ownDateCreationTextView = itemView.findViewById(R.id.own_date_of_creation);
        }

        @Override
        void bind(MessageRecyclerCell message) {
            OwnMessage ownMessage = (OwnMessage) message;
            ownMessageTextView.setText(ownMessage.getMessage().getMessageText());
            Picasso.with(context).load(R.drawable.cat1).into(ownIconImageView);

            String day = GeneralUtils.getDay(ownMessage.getMessage());
            String month = GeneralUtils.getMonth(ownMessage.getMessage());
            String year = GeneralUtils.getYear(ownMessage.getMessage());
            String hour = GeneralUtils.getHours(ownMessage.getMessage());
            String minutes = GeneralUtils.getMinutes(ownMessage.getMessage());
            String dateCreated = day + " " + month + " " + year + "   " + hour + ":" + minutes;
            ownDateCreationTextView.setText(dateCreated);
            itemView.setOnClickListener(view -> listener.onMessage(ownMessage.getMessage().getUserId()));
        }
    }

    private class BlurbMessageHolder extends BaseHolder {
        private final TextView blurbButtonTextView;

        BlurbMessageHolder(View itemView) {
            super(itemView);
            blurbButtonTextView = itemView.findViewById(R.id.blurb_button);
        }

        @Override
        void bind(MessageRecyclerCell message) {
            BlurbMessage blurbMessage = (BlurbMessage) message;
            blurbButtonTextView.setOnClickListener(view -> listener.onMessage(blurbMessage.getMessage().getUserId()));
        }
    }

    static abstract class BaseHolder extends RecyclerView.ViewHolder {
        BaseHolder(@NonNull View itemView) {
            super(itemView);
        }
        abstract void bind(MessageRecyclerCell cell);
    }
}