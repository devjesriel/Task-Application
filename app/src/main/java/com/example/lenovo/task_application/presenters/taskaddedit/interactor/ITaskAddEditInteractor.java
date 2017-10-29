package com.example.lenovo.task_application.presenters.taskaddedit.interactor;

import android.content.Context;

import com.example.lenovo.task_application.data.Task;

/**
 * Created by Lenovo on 29/7/2017.
 */

public interface ITaskAddEditInteractor {

    void addNewTask(Context context, Task task);
    void loadExistingTask(Context context, String id);
    void editExistingTask(Context context, Task task, String taskId);
    void deleteExistingTask(Context context, String taskId);

}
