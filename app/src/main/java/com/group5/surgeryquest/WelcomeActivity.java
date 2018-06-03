package com.group5.surgeryquest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.group5.surgeryquest.roottaskcollectionviews.RootTaskCollectionActivity;
//import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        /*
        TEMPORARY: this block of code is to see if PIN value is retained correctly
        using SharedPreferences in SurgeryQuestApplication.java

        String pinTest = ((SurgeryQuestApplication) getApplication()).getPinNum();
        TextView textView = findViewById(R.id.textView);
        textView.setText(pinTest);
        */
    }

    // Called when user is finished reading and clicks 'START' button.
    public void startHandler(View view) {
        Intent intent = new Intent(this, RootTaskCollectionActivity.class);
        startActivity(intent);
        finish();
    }
}
