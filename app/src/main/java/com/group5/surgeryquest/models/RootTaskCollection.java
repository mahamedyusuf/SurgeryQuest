package com.group5.surgeryquest.models;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.group5.surgeryquest.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by aryik.bhattacharya on 3/29/18.
 */

public class RootTaskCollection {

    private ArrayList<TaskCollection> taskCollections;

    public RootTaskCollection(Resources resources) {
        this.taskCollections = configureTasks(resources);
    }

    @NonNull
    // Configure the tasks and TaskCollections based on hardcoded strings and images.
    private ArrayList<TaskCollection> configureTasks(Resources resources) {
        ArrayList<TaskCollection> taskCollections = new ArrayList<>(4);
        // 4 task collections:
        // - takeoff
        // - asteroid field
        // - black hole
        // - spaceship

        taskCollections.add(createFirstTaskCollection(resources));
        taskCollections.add(createSecondTaskCollection(resources));
        taskCollections.add(createThirdTaskCollection(resources));
        taskCollections.add(createFourthTaskCollection(resources));

        // Unlock the first collection
        taskCollections.get(0).setUnlocked(true);

        return taskCollections;
    }


    private TaskCollection createFirstTaskCollection(Resources resources) {
        ArrayList<Task> taskList = new ArrayList<>(2);

        taskList.add(new Task(resources.getString(R.string.check_in_title),
                resources.getString(R.string.check_in),
                R.drawable.checkin));

        taskList.add(new Task(resources.getString(R.string.waiting_room_title),
                resources.getString(R.string.waiting_room),
                R.drawable.waitroom));

        return new TaskCollection(resources.getString(R.string.first_collection_title),
                R.drawable.collection_one, taskList);
    }

    private TaskCollection createSecondTaskCollection(Resources resources) {
        ArrayList<Task> taskList = new ArrayList<>(6);

        taskList.add(new Task(resources.getString(R.string.measure_weight_and_height_title),
                resources.getString(R.string.measure_weight_and_height),
                R.drawable.heightweight));

        taskList.add(new Task(resources.getString(R.string.wristband_title),
                resources.getString(R.string.wristband),
                R.drawable.wristband));

        taskList.add(new Task(resources.getString(R.string.scrubs_title),
                resources.getString(R.string.scrubs),
                R.drawable.scrubs));

        taskList.add(new Task(resources.getString(R.string.heart_rate_title),
                resources.getString(R.string.heart_rate),
                R.drawable.heartrate));

        taskList.add(new Task(resources.getString(R.string.blood_pressure_cuff_title),
                resources.getString(R.string.blood_pressure_cuff),
                R.drawable.bloodpressure));

        return new TaskCollection(resources.getString(R.string.second_collection_title),
                R.drawable.collection_two, taskList);
    }

    private TaskCollection createThirdTaskCollection(Resources resources) {
        ArrayList<Task> taskList = new ArrayList<>(4);

        taskList.add(new Task(resources.getString(R.string.stethoscope_title),
                resources.getString(R.string.stethoscope),
                R.drawable.stethoscope));

        taskList.add(new Task(resources.getString(R.string.scent_title),
                resources.getString(R.string.scent),
                R.drawable.anesthesia));

        taskList.add(new Task(resources.getString(R.string.otoscope_title),
                resources.getString(R.string.otoscope),
                R.drawable.otoscope));

        taskList.add(new Task(resources.getString(R.string.meet_surgeon_title),
                resources.getString(R.string.meet_surgeon),
                R.drawable.surgeon));

        return new TaskCollection(resources.getString(R.string.third_collection_title),
                R.drawable.collection_three, taskList);
    }

    private TaskCollection createFourthTaskCollection(Resources resources) {
        ArrayList<Task> taskList = new ArrayList<>(4);

        taskList.add(new Task(resources.getString(R.string.wake_up_title),
                resources.getString(R.string.wake_up),
                R.drawable.wakeup));

        taskList.add(new Task(resources.getString(R.string.eat_title),
                resources.getString(R.string.eat),
                R.drawable.eat));

        taskList.add(new Task(resources.getString(R.string.watch_cartoon_title),
                resources.getString(R.string.watch_cartoon),
                R.drawable.cartoon));

        taskList.add(new Task(resources.getString(R.string.get_balloon_title),
                resources.getString(R.string.get_balloon),
                R.drawable.balloon));

        return new TaskCollection(resources.getString(R.string.fourth_collection_title),
                R.drawable.collection_four, taskList);
    }

    public ArrayList<TaskCollection> getTaskCollections() {
        return taskCollections;
    }

    public void setTaskCollections(ArrayList<TaskCollection> taskCollections) {
        this.taskCollections = taskCollections;
    }

    public int size() {
        return taskCollections.size();
    }

    public TaskCollection get(int i) {
        return taskCollections.get(i);
    }

    public void set(int i, TaskCollection coll) { taskCollections.set(i, coll); }

    public void unlockNextTaskCollection() {
        for(int i = 0; i < taskCollections.size() - 1; ++i) {
            if (taskCollections.get(i).isCompleted()) {
                taskCollections.get(i + 1).setUnlocked(true);
            }
        }
    }

    public boolean isCompleted() {
        for (TaskCollection taskCollection: taskCollections) {
            if (!taskCollection.isCompleted()) return false;
        }
        return true;
    }
}
