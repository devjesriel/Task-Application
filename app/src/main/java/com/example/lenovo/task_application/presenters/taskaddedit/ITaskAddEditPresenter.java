package com.example.lenovo.task_application.presenters.taskaddedit;

import android.content.Context;

import com.example.lenovo.task_application.data.Task;
import com.example.lenovo.task_application.enums.TaskType;

/**
 * Created by Lenovo on 29/7/2017.
 */

public interface ITaskAddEditPresenter {

    void onAttachedToolbar();
    void onExtrasNotNull(Context context, String id);
    void onClickFab(TaskType taskType, Context context, Task task, String taskId);
    void onClickedDelete(Context context, String taskId);
}
