/*
 * Copyright Nathan Chanthamontry 3/27/18. All rights reserved.
 *
 *
 */

package com.group5.surgeryquest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.group5.surgeryquest.roottaskcollectionviews.RootTaskCollectionActivity;


public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        // If we've already set a PIN, go right into the app
        String currentPIN = ((SurgeryQuestApplication) getApplicationContext()).getPinNum();
        if(!currentPIN.equals(SurgeryQuestApplication.PIN_NOT_SET)) {
            // The PIN is not the default value so it has already been set
            Intent intent = new Intent(this, RootTaskCollectionActivity.class);
            startActivity(intent);
            finish();
        }
    }
    

    /* This method creates a pop-up alert dialog to confirm the user PIN.
    *
    *  It is called when the user presses button 'OK' to enter the PIN.
    */
    public void confirmPin(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeScreenActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Confirm PIN");
        builder.setMessage("Please re-enter your PIN to confirm.");

        final EditText pinConfirm = new EditText(this);
        pinConfirm.setHint("Enter PIN");

        builder.setView(pinConfirm);

        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                EditText editText = (EditText) findViewById(R.id.pinNumber);
                String pinValue = editText.getText().toString();
                if (pinValue.equals(pinConfirm.getText().toString())) {
                    ((SurgeryQuestApplication) getApplicationContext()).setPinNum(pinValue);
                    Intent intent = new Intent(HomeScreenActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    dialog.dismiss();
                    constructFailedConfirmationDialog();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // CLOSE pop-up alert and allow user to re-enter new PIN
                // dialog.cancel();
            }
        });
        builder.show();
    }

    private void constructFailedConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeScreenActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Incorrect PIN");
        builder.setMessage("Sorry, we couldn't confirm your PIN. Please try again.");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
