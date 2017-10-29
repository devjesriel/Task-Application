package com.example.lenovo.task_application.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lenovo on 28/7/2017.
 */

public class TaskDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "task.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String COMMA_SEPERATOR = ",";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TaskPersistenceContract.TaskEntry.TABLE_NAME + " (" +
                    TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_ID + TEXT_TYPE + " PRIMARY KEY," +
                    TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_NAME + TEXT_TYPE + COMMA_SEPERATOR +
                    TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DESCRIPTION + TEXT_TYPE + COMMA_SEPERATOR +
                    TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DATE_CREATED + TEXT_TYPE + COMMA_SEPERATOR +
                    TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DATE_UPDATED + TEXT_TYPE +
                    " )";


    public TaskDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
