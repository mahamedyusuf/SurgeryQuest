package com.group5.surgeryquest.roottaskcollectionviews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group5.surgeryquest.R;
import com.group5.surgeryquest.taskcollectionviews.TaskCollectionActivity;
import com.group5.surgeryquest.models.RootTaskCollection;
import com.group5.surgeryquest.models.TaskCollection;

/**
 * Created by aryik.bhattacharya on 3/30/18.
 */

// This class is used internally by RecyclerView and LayoutManager to construct the grid view of
// cards. It basically associates data with a particular card in the grid of cards .

public class RootTaskAdapter extends RecyclerView.Adapter<RootTaskAdapter.ViewHolder> {

    static final int COMPLETE_TASK_COLL_REQUEST = 1;

    private RootTaskCollection rootTaskCollection;
    private Context context;
    private Drawable checkmarkDrawable;

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView cardTitle;
        TaskCollection taskCollection;
        FrameLayoutHolder frameLayoutHolder;

        public ViewHolder(View v) {
            super(v);
            frameLayoutHolder = new FrameLayoutHolder(v);
            cardTitle = v.findViewById(R.id.cardTitle);
        }

        public void setImageAndTitle(TaskCollection t) {
            frameLayoutHolder.setImageResource(t.getImageResId());
            cardTitle.setText(t.getCollectionName());
            taskCollection = t;
            if (!t.isUnlocked()) {
                frameLayoutHolder.lock();
            } else if (t.isCompleted()) {
                frameLayoutHolder.check();
            } else {
                frameLayoutHolder.unlock();
            }
        }
    }

    // Need to pass in the Context so we can call startActivityForResult in onBindViewHolder
    public RootTaskAdapter(RootTaskCollection collection, Context context) {
        this.rootTaskCollection = collection;
        this.context = context;
        checkmarkDrawable = context.getDrawable(R.drawable.checkmark);
    }

    @Override
    // Creates a template for a particular data point based on collection_card_view.xml
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_card_view,
                parent, false);
        return new ViewHolder(mView);
    }

    @Override
    // Associates the appropriate image and title with a ViewHolder based on the position in the
    // list.
    public void onBindViewHolder(final RootTaskAdapter.ViewHolder holder, final int position) {
        holder.setImageAndTitle(rootTaskCollection.get(position));
        if (holder.taskCollection.isUnlocked()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), TaskCollectionActivity.class);
                    intent.putExtra("com.surgeryquest.taskCollKey", holder.taskCollection);
                    // Use startActivityForResult so the activity is listening for the result intent
                    ((Activity) context).startActivityForResult(intent, COMPLETE_TASK_COLL_REQUEST);
                }
            });
        }
    }


    @Override
    // Tells recyclerView how many items will be in the list.
    public int getItemCount() {
        return rootTaskCollection.size();
    }
}
