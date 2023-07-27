package com.example.digitalstudentpassport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class DashBoardNew extends AppCompatActivity {

    CardView personal_detailsCard;
    CardView AnnouncementCard;
    FirebaseAuth auth;
    FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dash_board_new);
        init();
        instance();
        initiateCategories();
    }
    void init()
    {
        personal_detailsCard = findViewById(R.id.personal_detailsCard);
        AnnouncementCard = findViewById(R.id.AnnouncementCard);
    }
    private void instance()
    {
        auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }
    private void initiateCategories()
    {
        personal_detailsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),StudentPersonalDetails.class));
            }
        });
        AnnouncementCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Announcement.class));
            }
        });
    }
}