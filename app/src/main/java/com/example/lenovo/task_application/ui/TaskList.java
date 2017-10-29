package com.example.lenovo.task_application.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.task_application.Base;
import com.example.lenovo.task_application.R;
import com.example.lenovo.task_application.R2;
import com.example.lenovo.task_application.data.Task;
import com.example.lenovo.task_application.presenters.tasklist.ITaskListView;
import com.example.lenovo.task_application.presenters.tasklist.TaskListPresenter;
import com.example.lenovo.task_application.presenters.tasklist.adapter.TaskListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskList extends AppCompatActivity implements ITaskListView,
        TaskListAdapter.OnCustomItemClickListener {

    @BindView(R2.id.act_task_list_fabAddEdit)
    FloatingActionButton fab;

    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    @BindView(R2.id.act_task_list_tvNoData)
    TextView noData;

    @BindView(R2.id.act_task_list_rvTaskList)
    RecyclerView mRecyclerView;

    @Inject
    TaskListPresenter taskListPresenter;

    private List<Task> taskList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        ((Base) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);

        taskListPresenter.setiTaskListView(this);

        taskListPresenter.onAttachedToolbar();

    }

    /**
     * Reloads task
     * on resume activity (refresh)
     */
    @Override
    protected void onResume() {
        super.onResume();
        taskListPresenter.onResumeLoadData(this);
    }

    /**
     * Pops the activity which
     * has the add / edit function
     */
    @Override
    public void showAddEditActivity() {
        Intent intent = new Intent(this, TaskAddEdit.class);
        startActivity(intent);
    }

    /**
     * Pops Add Edit activity which
     * passes the id to it so
     * it can enable edit mode
     * @param id - task's id
     */
    @Override
    public void showAddEditActivityWithData(String id) {
        Intent intent = new Intent(this, TaskAddEdit.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    /**
     * Setups the toolbar
     * title
     */
    @Override
    public void showToolbarTitle() {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("My Tasks");
        }

    }

    /**
     * Shows that there is
     * still no data
     */
    @Override
    public void showLodedTaskNoData() {
        noData.setVisibility(View.VISIBLE);
    }

    /**
     * Shows the list of
     * tasks to user
     * @param taskList - contains all the tasks of user (saved)
     */
    @Override
    public void showLoadedWithData(List<Task> taskList) {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        this.taskList = taskList;
        mRecyclerView.setAdapter(new TaskListAdapter(this.taskList, this));
    }

    /**
     * On clicked
     * the floating action button
     */
    @OnClick(R2.id.act_task_list_fabAddEdit)
    void onClickEdit() {

        taskListPresenter.onClickedAddEdit();
    }

    /**
     * On click one of the
     * task in the list
     * @param task - created task by user
     */
    @Override
    public void onItemClicked(Task task) {

        taskListPresenter.onClickAddEditWithData(task.getId());
    }
}
