package com.example.lenovo.task_application.presenters.tasklist.interactor;

import com.example.lenovo.task_application.data.Task;

import java.util.List;

/**
 * Created by Lenovo on 29/7/2017.
 */

public interface ITaskListInteractorResult {
    void onLoadedNoData();
    void onLoadedData(List<Task> taskList);
}
