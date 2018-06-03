package com.group5.surgeryquest.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.group5.surgeryquest.R;

/**
 * Copyright Aryik Bhattacharya 3/26/18. All rights reserved.
 *
 * A model representing a single task/objective in SurgeryQuest (i.e. stethoscope/anesthesiologist)
 */

public class Task implements Parcelable {

    private String taskName;

    // This could be switched to some sort of attributed string with markdown in the future
    private String description;

    // Resource ID of the image
    private Integer imageResId;

    private boolean isCompleted;

    public Task(String taskName, String description, Integer imageResId) {
        this.taskName = taskName;
        this.description = description;
        this.imageResId = imageResId;
    }

    // Getters & Setters

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageResId(Integer imageResId) {
        this.imageResId = imageResId;
    }

    public int getImageResId() {
        return this.imageResId;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    protected Task(Parcel in) {
        taskName = in.readString();
        description = in.readString();
        imageResId = in.readByte() == 0x00 ? null : in.readInt();
        isCompleted = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(taskName);
        dest.writeString(description);
        if (imageResId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(imageResId);
        }
        dest.writeByte((byte) (isCompleted ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}
