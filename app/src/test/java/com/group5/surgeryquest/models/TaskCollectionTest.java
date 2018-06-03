package com.group5.surgeryquest.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Copyright Aryik Bhattacharya 3/26/18. All rights reserved.
 */

public class TaskCollectionTest {
    TaskCollection taskCollection;

    @Before
    public void setUp() throws Exception {
        taskCollection = new TaskCollection("testCollection");
    }

    @After
    public void tearDown() throws Exception {
        taskCollection = null;
    }

    @Test
    public void updateProgressNoneCompleted() throws Exception {
        // given
        Task t1 = new Task("test");
        Task t2 = new Task("test");
        Task t3 = new Task("test");
        Task t4 = new Task("test");
        Task t5 = new Task("test");

        taskCollection.addTask(t1);
        taskCollection.addTask(t2);
        taskCollection.addTask(t3);
        taskCollection.addTask(t4);
        taskCollection.addTask(t5);

        // When
        double progress = taskCollection.updateProgress();

        // then
        assertEquals(progress, 0.0, 0.005);
    }

    @Test
    public void updateProgress60Percent() throws Exception {
        // given
        Task t1 = new Task("test");
        Task t2 = new Task("test");
        Task t3 = new Task("test");
        Task t4 = new Task("test");
        Task t5 = new Task("test");

        taskCollection.addTask(t1);
        taskCollection.addTask(t2);
        taskCollection.addTask(t3);
        taskCollection.addTask(t4);
        taskCollection.addTask(t5);

        t1.setCompleted(true);
        t2.setCompleted(true);
        t3.setCompleted(true);

        // When
        double progress = taskCollection.updateProgress();

        // then
        assertEquals(progress, 0.6, 0.005);
    }

    @Test
    public void updateProgressAllCompleted() throws Exception {
        // given
        Task t1 = new Task("test");
        Task t2 = new Task("test");
        Task t3 = new Task("test");
        Task t4 = new Task("test");
        Task t5 = new Task("test");

        taskCollection.addTask(t1);
        taskCollection.addTask(t2);
        taskCollection.addTask(t3);
        taskCollection.addTask(t4);
        taskCollection.addTask(t5);

        t1.setCompleted(true);
        t2.setCompleted(true);
        t3.setCompleted(true);
        t4.setCompleted(true);
        t5.setCompleted(true);

        // When
        double progress = taskCollection.updateProgress();

        // then
        assertEquals(progress, 1.0, 0.005);
    }

}