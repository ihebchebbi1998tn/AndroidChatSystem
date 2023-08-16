package com.example.chatroomiheb;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainProfileActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
      public String nameMessage ;
      public Bitmap bitmap ;
    private ImageButton homeButton, exploreButton, MessageButton;
    private ImageView profileImageView;
    private Uri selectedImageUri;
    private EditText namemessage;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_main);

        profileImageView = findViewById(R.id.profileImageView);
        namemessage = findViewById(R.id.namemessage);
        sendButton = findViewById(R.id.sendButton);

        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainProfileActivity.this, MainHOMEActivity.class);
                startActivity(intent);
            }
        });

        exploreButton = findViewById(R.id.exploreButton);
        exploreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your code for the Explore button click here
            }
        });

        MessageButton = findViewById(R.id.chatbutton);
        MessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainProfileActivity.this, MainChatActivity.class);
                intent.putExtra("nameSender", nameMessage);
                intent.putExtra("imgsender", bitmap);

                startActivity(intent);
            }
        });

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameMessage = namemessage.getText().toString();

                // Check if the entered name is in the list of winners
                String[] winners = {"Marwa", "Iheb", "Ahmed", "Ala", "Mouhib", "Oussema", "Asser", "Zeineb", "Ksontini"};
                boolean isWinner = false;
                for (String winner : winners) {
                    if (winner.equals(nameMessage)) {
                        isWinner = true;
                        break;
                    }
                }

                // Display a toast message based on whether the entered name is a winner or not
                if (isWinner) {
                    Intent intent = new Intent(MainProfileActivity.this, MainChatActivity.class);
                    intent.putExtra("nameSender", nameMessage);
                    intent.putExtra("imgsender", bitmap);
                    Toast.makeText(getApplicationContext(), nameMessage, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "You are not part of the winner team.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            try {
                 bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                profileImageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}