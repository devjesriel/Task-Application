package com.example.lenovo.task_application.presenters.taskaddedit;

import android.content.Context;

import com.example.lenovo.task_application.data.Task;
import com.example.lenovo.task_application.enums.TaskType;
import com.example.lenovo.task_application.presenters.taskaddedit.interactor.ITaskAddEditInteractorResult;
import com.example.lenovo.task_application.presenters.taskaddedit.interactor.TaskAddEditInteractor;

/**
 * Created by Lenovo on 29/7/2017.
 */

public class TaskAddEditPresenter implements ITaskAddEditPresenter, ITaskAddEditInteractorResult {

    private ITaskAddEditView iTaskAddEditView;
    private TaskAddEditInteractor taskAddEditInteractor;

    public TaskAddEditPresenter(ITaskAddEditView iTaskAddEditView) {
        this.iTaskAddEditView = iTaskAddEditView;
        this.taskAddEditInteractor = new TaskAddEditInteractor(this);
    }

    @Override
    public void onAttachedToolbar() {
        iTaskAddEditView.showToolbarTitle();
    }

    @Override
    public void onExtrasNotNull(Context context, String id) {
        taskAddEditInteractor.loadExistingTask(context, id);
    }

    @Override
    public void onClickFab(TaskType taskType, Context context, Task task, String taskId) {

        if (taskType == TaskType.NEW_TASK) {
            taskAddEditInteractor.addNewTask(context, task);
        } else {
            taskAddEditInteractor.editExistingTask(context, task, taskId);
        }

    }

    @Override
    public void onClickedDelete(Context context, String taskId) {

        taskAddEditInteractor.deleteExistingTask(context, taskId);
    }


    @Override
    public void onAddedTask() {
        iTaskAddEditView.onTaskSuccessfullyAdded();
    }

    @Override
    public void onLoadedSingleTask(Task task) {
        iTaskAddEditView.showTaskEditMode(task);
    }

    @Override
    public void onLoadedNullTask() {
        iTaskAddEditView.showNullTask();
    }

    @Override
    public void onEditedTask() {
        iTaskAddEditView.showTaskSuccessfyllyEdited();
    }

    @Override
    public void onDeletedTask() {
        iTaskAddEditView.showTaskSuccessfullyDeleted();
    }
}
