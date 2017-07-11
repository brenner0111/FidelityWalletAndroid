package com.example.brenner.fidelity_wallet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class RequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_request);
        Button homeButton = (Button) findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                homePage(v);
            }
        });
        Button requestButton = (Button) findViewById(R.id.requestBtn);
        requestButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                attemptRequest();
            }
        });
    }
    protected void homePage(View v){
        Intent homePage = new Intent(this, HomePageActivity.class);
        startActivity(homePage);
    }

    private void attemptRequest(){
        //POST-REQUEST
        //Log.d("", "requestBtn working");
    }

}
