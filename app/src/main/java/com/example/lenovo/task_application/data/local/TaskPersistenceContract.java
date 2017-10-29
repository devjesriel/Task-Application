package com.example.lenovo.task_application.data.local;

import android.provider.BaseColumns;

/**
 * Created by Lenovo on 28/7/2017.
 */

/**
 * The contract used for the db to save the tasks locally.
 */
public final class TaskPersistenceContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private TaskPersistenceContract() {}

    /* Inner class that defines the table contents */
    public static abstract class TaskEntry implements BaseColumns{
        public static final String TABLE_NAME = "tasks";
        public static final String COLUMN_NAME_TASK_ID = "taskId";
        public static final String COLUMN_NAME_TASK_NAME = "taskName";
        public static final String COLUMN_NAME_TASK_DESCRIPTION = "taskDescription";
        public static final String COLUMN_NAME_TASK_DATE_CREATED = "taskDateCreated";
        public static final String COLUMN_NAME_TASK_DATE_UPDATED= "taskDateUpdated";

    }

}
