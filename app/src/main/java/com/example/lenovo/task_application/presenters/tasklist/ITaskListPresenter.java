package com.example.lenovo.task_application.presenters.tasklist;

import android.content.Context;

/**
 * Created by Lenovo on 28/7/2017.
 */

public interface ITaskListPresenter {

    void onClickedAddEdit();
    void onClickAddEditWithData(String id);
    void onAttachedToolbar();
    void onResumeLoadData(Context context);
}
