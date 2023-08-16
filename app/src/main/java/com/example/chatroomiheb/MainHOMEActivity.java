package com.example.chatroomiheb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;

public class MainHOMEActivity extends AppCompatActivity {

    private ImageButton homeButton , exploreButton , MessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.home_main);

        exploreButton = findViewById(R.id.homeButton);



        exploreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        homeButton = findViewById(R.id.exploreButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainHOMEActivity.this, MainProfileActivity.class);
                startActivity(intent);

            }
        });
        MessageButton = findViewById(R.id.profileButton);
        MessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainHOMEActivity.this, MainChatActivity.class);
                startActivity(intent);
            }
        });
    }
}
