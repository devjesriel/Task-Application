package com.example.lenovo.task_application.presenters.taskaddedit;

import com.example.lenovo.task_application.data.Task;

/**
 * Created by Lenovo on 29/7/2017.
 */

public interface ITaskAddEditView {

    void showToolbarTitle();
    void onTaskSuccessfullyAdded();
    void showTaskEditMode(Task task);
    void showNullTask();
    void showTaskSuccessfyllyEdited();
    void showTaskSuccessfullyDeleted();
}
