package com.group5.surgeryquest.taskcollectionviews;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.group5.surgeryquest.R;
import com.group5.surgeryquest.models.Task;
import com.group5.surgeryquest.models.TaskCollection;

public class TaskCollectionActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TaskAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TaskCollection taskCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task_collection);

        taskCollection = getIntent().getParcelableExtra("com.surgeryquest.taskCollKey");

        mRecyclerView = findViewById(R.id.task_collection_recycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a grid layout manager
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Create a RootTaskAdapter and associate it with the cardView
        mAdapter = new TaskAdapter(taskCollection, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    // Process the completed activity result from DetailTaskActivity.
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TaskAdapter.COMPLETE_TASKS_REQUEST) {
            if (resultCode == RESULT_OK) {
                // Get the task from the intent
                Task resultTask = data.getParcelableExtra("com.surgeryquest.completedTask");

                // Find the task in the taskCollection and replace it with the result task.
                for(int i = 0; i < taskCollection.size(); ++i) {
                    if (taskCollection.get(i).getTaskName().equals(resultTask.getTaskName())) {
                        taskCollection.set(i, resultTask);
                        // Tell the adapter to reload the data.
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }

    @Override
    /* Invoked when user clicks back button from TaskCollectionActivity screen.
     *
     * This method checks to see if all Tasks in the Task Collection are complete. If so, sets the
     * specific Task Collection to be complete and sends new completed TaskCollection to parent
     * activity.
     */
    public void onBackPressed(){
        passTaskCollectionToParent();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            passTaskCollectionToParent();
        }
        return super.onOptionsItemSelected(item);
    }

    private void passTaskCollectionToParent() {
        // Call isCompleted to front-load iteration through the TaskList.
        taskCollection.isCompleted();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("com.surgeryquest.completedTaskColl", taskCollection);
        setResult(Activity.RESULT_OK, resultIntent);
    }
}
