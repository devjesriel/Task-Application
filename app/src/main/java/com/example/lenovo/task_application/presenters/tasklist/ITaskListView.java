package com.example.lenovo.task_application.presenters.tasklist;

import com.example.lenovo.task_application.data.Task;

import java.util.List;

/**
 * Created by Lenovo on 28/7/2017.
 */

public interface ITaskListView {
    void showAddEditActivity();
    void showAddEditActivityWithData(String id);
    void showToolbarTitle();
    void showLodedTaskNoData();
    void showLoadedWithData(List<Task> taskList);
}
