package com.example.chatroomiheb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText messageEditText;
    private Button sendButton;

    private CollectionReference chatMessageRef;
    private List<ChatMessage> messageList;
    private ChatAdapter chatAdapter;
    private ImageButton homeButton , exploreButton , MessageButton , downloadSendImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activitychat_main);

        recyclerView = findViewById(R.id.recyclerView);
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        chatMessageRef = firestore.collection("chatmessage");
        Intent intent = getIntent();
        String nameSender = intent.getStringExtra("nameSender");

        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(this, messageList, nameSender);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true); // Display new messages at the bottom
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(chatAdapter);



        chatMessageRef.addSnapshotListener((value, error) -> {
            if (error != null) {
                // Handle the error
                return;
            }

            for (DocumentChange dc : value.getDocumentChanges()) {
                QueryDocumentSnapshot document = dc.getDocument();
                ChatMessage chatMessage = document.toObject(ChatMessage.class);
                messageList.add(chatMessage);
            }

            // Sort the message list based on timestamp in ascending order
            Collections.sort(messageList, (m1, m2) -> Long.compare(m1.getTimestamp(), m2.getTimestamp()));

            chatAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(messageList.size() - 1);
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString().trim();
                if (!message.isEmpty()) {
                    String messageId = "message1";
                    String sender = nameSender;

                    long timestamp = System.currentTimeMillis();
                    ChatMessage chatMessage = new ChatMessage(messageId, message, sender, timestamp);

                    // Format the timestamp to "M/d/yyyy - H:mm" format
                    SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy - H:mm", Locale.getDefault());
                    String formattedTime = dateFormat.format(new Date(timestamp));
                    chatMessage.setTime(formattedTime);

                    chatMessageRef.document(messageId).set(chatMessage);
                    messageEditText.setText("");



                }
            }
        });


        exploreButton = findViewById(R.id.homeButton);
        exploreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainChatActivity.this, MainHOMEActivity.class);
                startActivity(intent);
            }
        });

        homeButton = findViewById(R.id.exploreButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainChatActivity.this, MainProfileActivity.class);
                startActivity(intent);

            }
        });
        MessageButton = findViewById(R.id.profileButton);
        MessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainChatActivity.this, MainChatActivity.class);
                startActivity(intent);
            }
        });



    }
}
