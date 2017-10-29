package com.example.lenovo.task_application.presenters.tasklist.interactor;

import android.content.Context;

import com.example.lenovo.task_application.data.Task;
import com.example.lenovo.task_application.data.local.source.ITaskLocalDataSource;
import com.example.lenovo.task_application.data.local.source.TaskLocalDataSource;

import java.util.List;

/**
 * Created by Lenovo on 28/7/2017.
 */

public class TaskListInteractor implements ITaskListInteractor, ITaskLocalDataSource.TaskLoadedCallback {

    ITaskListInteractorResult iTaskListInteractorResult;

    public TaskListInteractor(ITaskListInteractorResult iTaskListInteractorResult) {
        this.iTaskListInteractorResult = iTaskListInteractorResult;
    }

    @Override
    public void loadAllTask(Context context) {
        TaskLocalDataSource.getInstance(context).getAllTask(this);
    }

    @Override
    public void onTaskLoaded(List<Task> taskList) {
        iTaskListInteractorResult.onLoadedData(taskList);
    }

    @Override
    public void onTaskLoadedButNoData() {
        iTaskListInteractorResult.onLoadedNoData();
    }
}
