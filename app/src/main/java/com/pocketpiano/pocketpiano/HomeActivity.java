package com.pocketpiano.pocketpiano;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void startFreeStyle(View view) {
        Intent myIntent = new Intent(this, FreeStyleActivity.class);
        startActivity(myIntent);
    }

    public void startPractice(View view) {
        Intent myIntent = new Intent(this, FreeStyleActivity.class);
        startActivity(myIntent);
    }
}
