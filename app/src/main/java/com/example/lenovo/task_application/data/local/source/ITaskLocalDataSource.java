package com.example.lenovo.task_application.data.local.source;

import com.example.lenovo.task_application.data.Task;

import java.util.List;

/**
 * Created by Lenovo on 28/7/2017.
 */

public interface ITaskLocalDataSource {

    interface TaskLoadedCallback{

        void onTaskLoaded(List<Task> taskList);
        void onTaskLoadedButNoData();
    }

    interface SingleTaskLoadedCallback{

        void onSingleTaskLoaded(Task task);
        void onNullTaskLoaded();
    }

    void getAllTask(TaskLoadedCallback taskLoadedCallback);
    void addTask(Task task);
    void editTask(Task task, String taskId);
    void deleteTask(String taskId);
    void getTask(String taskId, SingleTaskLoadedCallback singleTaskLoadedCallback);

}
