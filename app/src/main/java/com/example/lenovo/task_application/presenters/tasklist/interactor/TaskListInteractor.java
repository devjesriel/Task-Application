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

    /**
     * Load all task from the task
     * data source
     * @param context
     */
    @Override
    public void loadAllTask(Context context) {
        TaskLocalDataSource.getInstance(context).getAllTask(this);
    }

    /**
     * After all data has been loaded pass it
     * to the presenter
     * @param taskList - Arraylist of tasks
     */
    @Override
    public void onTaskLoaded(List<Task> taskList) {
        iTaskListInteractorResult.onLoadedData(taskList);
    }

    /**
     * When loaded but
     * catches no data
     */
    @Override
    public void onTaskLoadedButNoData() {
        iTaskListInteractorResult.onLoadedNoData();
    }
}
