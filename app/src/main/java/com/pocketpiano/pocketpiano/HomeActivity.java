package com.pocketpiano.pocketpiano;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.pocketpiano.pocketpiano.instruments.PianoNodes;

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
        PianoNodes piano = new PianoNodes(this);
        piano.playRandomNode();
    }
}
