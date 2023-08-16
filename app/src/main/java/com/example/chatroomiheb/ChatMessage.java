package com.example.chatroomiheb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChatMessage {
    private String messageId;
    private String message;
    private String sender;
    private long timestamp;
    private String time;

    public ChatMessage() {
        // Empty constructor required for Firestore
    }

    public ChatMessage(String messageId, String message, String sender, long timestamp) {
        this.messageId = messageId;
        this.message = message;
        this.sender = sender;
        this.timestamp = timestamp;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFormattedTimestamp() {
        // Format the timestamp to "M/d/yyyy - H:mm" format
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy - H:mm", Locale.getDefault());
        return dateFormat.format(new Date(timestamp));
    }
}
