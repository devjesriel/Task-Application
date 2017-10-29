package com.example.lenovo.task_application.presenters.taskaddedit.interactor;

import com.example.lenovo.task_application.data.Task;

/**
 * Created by Lenovo on 29/7/2017.
 */

public interface ITaskAddEditInteractorResult {
    void onAddedTask();
    void onLoadedSingleTask(Task task);
    void onLoadedNullTask();
    void onEditedTask();
    void onDeletedTask();
}
