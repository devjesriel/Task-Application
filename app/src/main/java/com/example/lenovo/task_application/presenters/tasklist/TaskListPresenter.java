package com.example.lenovo.task_application.presenters.tasklist;

import android.content.Context;

import com.example.lenovo.task_application.data.Task;
import com.example.lenovo.task_application.presenters.tasklist.interactor.ITaskListInteractorResult;
import com.example.lenovo.task_application.presenters.tasklist.interactor.TaskListInteractor;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Lenovo on 28/7/2017.
 */

public class TaskListPresenter implements ITaskListPresenter, ITaskListInteractorResult {

    private ITaskListView iTaskListView;
    private TaskListInteractor taskListInteractor;

    @Inject
    public TaskListPresenter() {
        this.taskListInteractor = new TaskListInteractor(this);
    }

    public void setiTaskListView(ITaskListView iTaskListView) {
        this.iTaskListView = iTaskListView;
    }

    @Override
    public void onClickedAddEdit() {
        iTaskListView.showAddEditActivity();
    }

    @Override
    public void onClickAddEditWithData(String id) {
        iTaskListView.showAddEditActivityWithData(id);
    }

    @Override
    public void onAttachedToolbar() {
        iTaskListView.showToolbarTitle();
    }

    @Override
    public void onResumeLoadData(Context context) {
        taskListInteractor.loadAllTask(context);
    }

    @Override
    public void onLoadedNoData() {
        iTaskListView.showLodedTaskNoData();
    }

    @Override
    public void onLoadedData(List<Task> taskList) {
        iTaskListView.showLoadedWithData(taskList);
    }
}
