package com.group5.surgeryquest.detailtaskviews;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.group5.surgeryquest.HomeScreenActivity;
import com.group5.surgeryquest.R;
import com.group5.surgeryquest.SurgeryQuestApplication;
import com.group5.surgeryquest.WelcomeActivity;
import com.group5.surgeryquest.models.Task;

public class DetailedTaskActivity extends AppCompatActivity {

    private Task task;
    private ImageView imageView;
    private TextView descriptionTextView;
    private TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_task);

        task = getIntent().getParcelableExtra("com.surgeryquest.taskKey");
        imageView = findViewById(R.id.detailTaskImage);
        descriptionTextView = findViewById(R.id.detailTaskText);
        titleTextView = findViewById(R.id.detailTaskTitle);

        imageView.setImageResource(task.getImageResId());
        descriptionTextView.setText(task.getDescription());
        titleTextView.setText(task.getTaskName());
    }

    public void clickListener(View view) {
        final EditText pinEntry = new EditText(this);
        pinEntry.setHint("Parent PIN");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Enter Parent PIN");
        builder.setMessage("Your parent needs to enter their PIN to confirm.");
        builder.setView(pinEntry);

        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                String pinValue = pinEntry.getText().toString();
                String actualPin = ((SurgeryQuestApplication) getApplicationContext()).getPinNum();

                if (pinValue.equals(actualPin)) {
                    task.setCompleted(true);

                    // Pass the now completed task to the parent activity
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("com.surgeryquest.completedTask", task);
                    setResult(Activity.RESULT_OK, resultIntent);

                    // Navigate to the parent activity
                    NavUtils.navigateUpFromSameTask(DetailedTaskActivity.this);
                    dialog.dismiss();
                } else {
                    dialog.dismiss();
                    constructIncorrectPinDialog();
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

    private void constructIncorrectPinDialog() {
        final EditText pinEntry = new EditText(this);
        pinEntry.setHint("Parent PIN");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Incorrect PIN!");
        builder.setMessage("Sorry, that wasn't the parent PIN. Please try again!");
        builder.setView(pinEntry);

        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                String pinValue = pinEntry.getText().toString();
                String actualPin = ((SurgeryQuestApplication) getApplicationContext()).getPinNum();

                if (pinValue.equals(actualPin)) {
                    task.setCompleted(true);

                    // Pass the now completed task to the parent activity
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("com.surgeryquest.completedTask", task);
                    setResult(Activity.RESULT_OK, resultIntent);

                    // Navigate to the parent activity
                    NavUtils.navigateUpFromSameTask(DetailedTaskActivity.this);
                    dialog.dismiss();
                } else {
                    dialog.dismiss();
                    constructIncorrectPinDialog();
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
}
