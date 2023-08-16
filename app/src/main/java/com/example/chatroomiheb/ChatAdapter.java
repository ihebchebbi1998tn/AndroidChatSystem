package com.example.chatroomiheb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<ChatMessage> messageList;
    private Context context;
    private String nameSender;
    private Map<String, Integer> senderImageMap;

    public ChatAdapter(Context context, List<ChatMessage> messageList, String nameSender) {
        this.context = context;
        this.messageList = messageList;
        this.nameSender = nameSender;
        this.senderImageMap = createSenderImageMap();
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatMessage chatMessage = messageList.get(position);
        holder.bind(chatMessage);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    private Map<String, Integer> createSenderImageMap() {
        Map<String, Integer> imageMap = new HashMap<>();
        imageMap.put("Iheb", R.drawable.img_18);
        imageMap.put("Ala", R.drawable.img_19);
        imageMap.put("Ahmed", R.drawable.img_20);
        imageMap.put("Mouhib", R.drawable.img_21);
        imageMap.put("Marwa", R.drawable.img_22);
        imageMap.put("Zeineb", R.drawable.img_24);
        imageMap.put("Oussema", R.drawable.img_25);

        return imageMap;
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView senderTextView;
        TextView messageTextView;
        ImageView imageView;
        TextView timeTextView;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            senderTextView = itemView.findViewById(R.id.senderTextView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            imageView = itemView.findViewById(R.id.avatarImageView);
            timeTextView = itemView.findViewById(R.id.timeMessage);
        }

        public void bind(ChatMessage chatMessage) {
            senderTextView.setText(chatMessage.getSender());
            messageTextView.setText(chatMessage.getMessage());
            timeTextView.setText(chatMessage.getFormattedTimestamp());

            // Set background image based on the sender
            if (chatMessage.getSender() != null) {
                Integer imageResId = senderImageMap.get(chatMessage.getSender());
                if (imageResId != null) {
                    imageView.setBackgroundResource(imageResId);
                } else {
                    imageView.setBackgroundResource(R.drawable.img_18);
                }
            } else {
                imageView.setBackgroundResource(R.drawable.img_18);
            }

            // Set background color based on the sender
            if (chatMessage.getSender() != null && chatMessage.getSender().equals(nameSender)) {
                itemView.setBackgroundResource(R.drawable.senderwhite);
            } else {
                itemView.setBackgroundResource(R.drawable.reciever);
            }
        }
    }
}