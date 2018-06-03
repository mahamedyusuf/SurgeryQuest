package com.group5.surgeryquest.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright Aryik Bhattacharya 3/26/18. All rights reserved.
 *
 * A model representing a logical grouping of tasks.
 */

public class TaskCollection implements Parcelable {

    private String collectionName;

    // The resource ID of the collection's image. This may need to change to a URL or something in
    // the future.
    private Integer imageResId;

    // Progress from 0 to 100. Need calculation/update method.
    private double progress;

    // The list of tasks
    private List<Task> taskList;

    // Has the user unlocked this collection?
    private boolean isUnlocked;

    // If the entire collection of tasks is complete
    private boolean isComplete;

    public TaskCollection(String collectionName, Integer imageResId, List<Task> taskList) {
        this.collectionName = collectionName;
        this.imageResId = imageResId;
        this.taskList = taskList;
        this.progress = 0.0;
        this.isUnlocked = false;
        this.isComplete = false;
    }

    public TaskCollection(String collectionName, Integer imageResId) {
        this(collectionName, imageResId, new ArrayList<Task>());
    }

    public TaskCollection(String collectionName) {
        this(collectionName, null);
    }

    // To check if entire Task Collection is fully complete
    public boolean isCompleted() {
        if (!isComplete) {
            for (Task task : taskList) {
                if (!task.isCompleted()) { return false; }
            }
            isComplete = true;
            return isComplete;
        } else {
            return true;
        }
    }
    // Calculate the progress based on the state of the tasks in taskList. Update the progress
    // property with the new value and return it.
    public double updateProgress() {
        double completed = 0.0;
        double total = taskList.size();

        for (Task task: taskList) {
            if (task.isCompleted()) completed += 1;
        }

        double progress = total != 0 ? completed / total : 0.0;

        this.setProgress(progress);
        return progress;
    }

    public boolean addTask(Task t){
        return this.taskList.add(t);
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public void set(int i, Task task) {
        taskList.set(i, task);
    }

    public int size() {
        return taskList.size();
    }

    // Getters & Setters

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(Integer imageResId) {
        this.imageResId = imageResId;
    }

    public double getProgress() {
        return updateProgress();
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }

    protected TaskCollection(Parcel in) {
        collectionName = in.readString();
        imageResId = in.readByte() == 0x00 ? null : in.readInt();
        progress = in.readDouble();
        if (in.readByte() == 0x01) {
            taskList = new ArrayList<Task>();
            in.readList(taskList, Task.class.getClassLoader());
        } else {
            taskList = null;
        }
        isUnlocked = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(collectionName);
        if (imageResId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(imageResId);
        }
        dest.writeDouble(progress);
        if (taskList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(taskList);
        }
        dest.writeByte((byte) (isUnlocked ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TaskCollection> CREATOR = new Parcelable.Creator<TaskCollection>() {
        @Override
        public TaskCollection createFromParcel(Parcel in) {
            return new TaskCollection(in);
        }

        @Override
        public TaskCollection[] newArray(int size) {
            return new TaskCollection[size];
        }
    };
}
