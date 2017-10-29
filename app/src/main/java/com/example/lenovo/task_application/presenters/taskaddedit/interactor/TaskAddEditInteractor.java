package com.example.lenovo.task_application.presenters.taskaddedit.interactor;

import android.content.Context;

import com.example.lenovo.task_application.data.Task;
import com.example.lenovo.task_application.data.local.source.ITaskLocalDataSource;
import com.example.lenovo.task_application.data.local.source.TaskLocalDataSource;

/**
 * Created by Lenovo on 29/7/2017.
 */

public class TaskAddEditInteractor implements ITaskAddEditInteractor, ITaskLocalDataSource.SingleTaskLoadedCallback{

    private ITaskAddEditInteractorResult result;

    public TaskAddEditInteractor(ITaskAddEditInteractorResult iTaskAddEditInteractorResult) {
        this.result = iTaskAddEditInteractorResult;
    }

    @Override
    public void addNewTask(Context context, Task task) {
        TaskLocalDataSource.getInstance(context).addTask(task);
        result.onAddedTask();
    }

    @Override
    public void onSingleTaskLoaded(Task task) {
        result.onLoadedSingleTask(task);
    }

    @Override
    public void onNullTaskLoaded() {
        result.onLoadedNullTask();
    }

    @Override
    public void loadExistingTask(Context context, String id) {
        TaskLocalDataSource.getInstance(context).getTask(id, this);
    }

    @Override
    public void editExistingTask(Context context, Task task, String taskId) {
        TaskLocalDataSource.getInstance(context).editTask(task, taskId);
        result.onEditedTask();
    }

    @Override
    public void deleteExistingTask(Context context, String taskId) {
        TaskLocalDataSource.getInstance(context).deleteTask(taskId);
        result.onDeletedTask();
    }


}
