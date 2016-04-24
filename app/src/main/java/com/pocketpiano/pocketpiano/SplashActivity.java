package com.pocketpiano.pocketpiano;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(2000);
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
