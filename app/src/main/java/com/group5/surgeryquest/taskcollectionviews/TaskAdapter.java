package com.group5.surgeryquest.taskcollectionviews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group5.surgeryquest.R;
import com.group5.surgeryquest.detailtaskviews.DetailedTaskActivity;
import com.group5.surgeryquest.models.Task;
import com.group5.surgeryquest.models.TaskCollection;
import com.group5.surgeryquest.roottaskcollectionviews.FrameLayoutHolder;

/**
 * Created by aryik.bhattacharya on 4/6/18.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    static final int COMPLETE_TASKS_REQUEST = 1;

    // TODO: Progress bars for rootTaskCollection
    // TODO: Fix all the layouts, make them pretty

    private TaskCollection taskCollection;
    private Context context;

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        FrameLayoutHolder frameLayoutHolder;
        TextView cardTitle;
        Task task;

        public ViewHolder(View v) {
            super(v);
            frameLayoutHolder = new FrameLayoutHolder(v);
            cardTitle = v.findViewById(R.id.cardTitle);
        }

        public void setImageAndTitle(Task t) {
            System.out.println("DEBUG: SETTING " + t.getTaskName());
            frameLayoutHolder.setImageResource(t.getImageResId());
            cardTitle.setText(t.getTaskName());
            task = t;
            if (t.isCompleted()) {
                frameLayoutHolder.check();
            }
        }
    }

    // We need to pass in the Context so we can call startActivityForResult in onBindViewHolder
    public TaskAdapter(TaskCollection collection, Context context) {
        this.taskCollection = collection;
        this.context = context;
    }

    @Override
    // Creates a template for a particular data point based on collection_card_view.xml
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_card_view,
                parent, false);
        return new TaskAdapter.ViewHolder(mView);
    }

    @Override
    // Associates the appropriate image and title with a ViewHolder based on the position in the
    // list.
    public void onBindViewHolder(final TaskAdapter.ViewHolder holder, final int position) {
        holder.setImageAndTitle(taskCollection.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailedTaskActivity.class);
                intent.putExtra("com.surgeryquest.taskKey", holder.task);
                // Use startActivityForResult so the activity is listening for the result intent.
                ((Activity) context).startActivityForResult(intent, COMPLETE_TASKS_REQUEST);
            }
        });
    }

    @Override
    // Tells recyclerView how many items will be in the list.
    public int getItemCount() {
        return taskCollection.size();
    }
}
