package com.example.brenner.fidelity_wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SendActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        //HomePage button
        Button homePageButton = (Button) findViewById(R.id.home_button);
        homePageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                homePage(v);
            }
        });
        Button sendButton = (Button) findViewById(R.id.sendBtn);
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                attemptSend();
            }
        });
    }
    protected void homePage(View v){
        Intent homePage = new Intent(this, HomePageActivity.class);
        startActivity(homePage);
    }


    private void attemptSend(){
        //POST-REQUEST
        //Log.d("", "sendButton working");
    }
}
