
package com.example.lenovo.task_application.data.local.source;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.lenovo.task_application.data.Task;
import com.example.lenovo.task_application.data.local.TaskDbHelper;
import com.example.lenovo.task_application.data.local.TaskPersistenceContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 28/7/2017.
 */

public class TaskLocalDataSource implements ITaskLocalDataSource {

    private static TaskLocalDataSource INSTANCE;

    private TaskDbHelper mTaskDbHelper;

    private static final String LIKE_STRING = " LIKE?";

    public TaskLocalDataSource(@NonNull Context context) {
        mTaskDbHelper = new TaskDbHelper(context);
    }

    public static TaskLocalDataSource getInstance(@NonNull Context context) {

        if (INSTANCE == null) {
            INSTANCE = new TaskLocalDataSource(context);
        }

        return INSTANCE;
    }

    @Override
    public void getAllTask(@NonNull TaskLoadedCallback taskLoadedCallback) {

        List<Task> taskList = new ArrayList<>();
        SQLiteDatabase mSqliteDatabase = mTaskDbHelper.getReadableDatabase();

        String [] projection = {
                TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_ID,
                TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_NAME,
                TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DESCRIPTION,
                TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DATE_CREATED,
                TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DATE_UPDATED,
        };

        Cursor mCursor = mSqliteDatabase.query(
                TaskPersistenceContract.TaskEntry.TABLE_NAME, projection, null, null, null, null, null);

        if(mCursor != null && mCursor.getCount() > 0){

            while (mCursor.moveToNext()){
                String taskId = mCursor.getString(mCursor.getColumnIndexOrThrow(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_ID));
                String taskName = mCursor.getString(mCursor.getColumnIndexOrThrow(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_NAME));
                String taskDesc = mCursor.getString(mCursor.getColumnIndexOrThrow(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DESCRIPTION));
                String taskCreated = mCursor.getString(mCursor.getColumnIndexOrThrow(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DATE_CREATED));
                String taskUpdated = mCursor.getString(mCursor.getColumnIndexOrThrow(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DATE_UPDATED));

                Task mTask = new Task(taskId, taskName, taskDesc, taskCreated, taskUpdated);
                taskList.add(mTask);
            }
        }

        if(mCursor != null){
            mCursor.close();
        }

        mSqliteDatabase.close();

        if(taskList.isEmpty()){
            taskLoadedCallback.onTaskLoadedButNoData();
        }else {
            taskLoadedCallback.onTaskLoaded(taskList);
        }

    }

    @Override
    public void addTask(@NonNull Task task) {

        SQLiteDatabase mSqliteDatabase = mTaskDbHelper.getWritableDatabase();

        ContentValues mContentValues = new ContentValues();
        mContentValues.put(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_ID, task.getId());
        mContentValues.put(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_NAME, task.getName());
        mContentValues.put(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DESCRIPTION, task.getDescription());
        mContentValues.put(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DATE_CREATED, task.getDateCreated());
        mContentValues.put(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DATE_UPDATED, task.getDateUpdated());

        mSqliteDatabase.insert(TaskPersistenceContract.TaskEntry.TABLE_NAME, null, mContentValues);
        mSqliteDatabase.close();

    }

    @Override
    public void editTask(@NonNull Task task, @NonNull String taskId) {

        SQLiteDatabase mSqliteDatabase = mTaskDbHelper.getWritableDatabase();

        ContentValues mContentValues = new ContentValues();
        mContentValues.put(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_ID, task.getId());
        mContentValues.put(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_NAME, task.getName());
        mContentValues.put(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DESCRIPTION, task.getDescription());
        mContentValues.put(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DATE_UPDATED, task.getDateUpdated());

        String selection = TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_ID + LIKE_STRING;
        String [] selectionArgs = {taskId};

        mSqliteDatabase.update(TaskPersistenceContract.TaskEntry.TABLE_NAME, mContentValues, selection, selectionArgs);

        mSqliteDatabase.close();

    }

    @Override
    public void deleteTask(@NonNull String taskId) {

        SQLiteDatabase mSqliteDatabase = mTaskDbHelper.getWritableDatabase();

        String selection = TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_ID + LIKE_STRING;
        String [] selectionArgs = {taskId};

        mSqliteDatabase.delete(TaskPersistenceContract.TaskEntry.TABLE_NAME, selection, selectionArgs);

        mSqliteDatabase.close();

    }

    @Override
    public void getTask(String task_id, SingleTaskLoadedCallback singleTaskLoadedCallback) {

        Task mTask = null;
        SQLiteDatabase mSqliteDatabase = mTaskDbHelper.getReadableDatabase();

        String [] projection = {
                TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_ID,
                TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_NAME,
                TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DESCRIPTION,
                TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DATE_CREATED,
                TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DATE_UPDATED,
        };

        String selection = TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_ID + LIKE_STRING;
        String [] selectionArgs = {task_id};

        Cursor mCursor = mSqliteDatabase.query(
                TaskPersistenceContract.TaskEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        if(mCursor != null && mCursor.getCount() > 0){

            while (mCursor.moveToNext()){
                String taskId = mCursor.getString(mCursor.getColumnIndexOrThrow(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_ID));
                String taskName = mCursor.getString(mCursor.getColumnIndexOrThrow(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_NAME));
                String taskDesc = mCursor.getString(mCursor.getColumnIndexOrThrow(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DESCRIPTION));
                String taskCreated = mCursor.getString(mCursor.getColumnIndexOrThrow(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DATE_CREATED));
                String taskUpdated = mCursor.getString(mCursor.getColumnIndexOrThrow(TaskPersistenceContract.TaskEntry.COLUMN_NAME_TASK_DATE_UPDATED));

                mTask = new Task(taskId, taskName, taskDesc, taskCreated, taskUpdated);
            }
        }

        if(mCursor != null){
            mCursor.close();
        }

        mSqliteDatabase.close();


        if(mTask != null){
            singleTaskLoadedCallback.onSingleTaskLoaded(mTask);
        }else {
            singleTaskLoadedCallback.onNullTaskLoaded();
        }



    }
}
