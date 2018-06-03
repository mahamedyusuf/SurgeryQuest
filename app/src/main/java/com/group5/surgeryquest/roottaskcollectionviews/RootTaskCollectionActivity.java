package com.group5.surgeryquest.roottaskcollectionviews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.group5.surgeryquest.CongratsActivity;
import com.group5.surgeryquest.R;
import com.group5.surgeryquest.models.RootTaskCollection;
import com.group5.surgeryquest.models.TaskCollection;

/**
 * Created by aryik.bhattacharya on 3/30/18.
 */

// RootTaskCollection page's activity. Creates and configures the RecyclerView, creates the
// RootTaskCollection that the rest of the app will use.
public class RootTaskCollectionActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RootTaskCollection rootTaskCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootTaskCollection = new RootTaskCollection(getResources());

        setContentView(R.layout.root_task_collection_screen);
        mRecyclerView = findViewById(R.id.root_task_collection_recycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a grid layout manager
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Create a RecyclerCollectionAdapter and associate it with the cardView
        mAdapter = new RootTaskAdapter(rootTaskCollection, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    // Process the completed activity result from TaskCollectionActivity
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RootTaskAdapter.COMPLETE_TASK_COLL_REQUEST){
            if (resultCode == RESULT_OK) {
                // Get the taskCollection from the intent
                TaskCollection resultTaskColl = data.getParcelableExtra("com.surgeryquest.completedTaskColl");

                // Find taskCollection in rootTaskCollection and replace with result taskColl
                for(int i = 0; i < rootTaskCollection.size(); ++i) {
                    if (rootTaskCollection.get(i).getCollectionName().equals(resultTaskColl.getCollectionName())){
                        rootTaskCollection.set(i, resultTaskColl);
                        // Tell the adapter to reload the data
                        mAdapter.notifyDataSetChanged();
                    }
                }
                rootTaskCollection.unlockNextTaskCollection();
                // TODO: if everything is completed, transition to congrats screen
                if (rootTaskCollection.isCompleted()) {
                    // Go to congrats screen
                    Intent intent = new Intent(this, CongratsActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }

}
