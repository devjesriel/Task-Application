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

import com.example.lenovo.task_application.R;
import com.example.lenovo.task_application.data.Task;
import com.example.lenovo.task_application.presenters.tasklist.ITaskListView;
import com.example.lenovo.task_application.presenters.tasklist.TaskListPresenter;
import com.example.lenovo.task_application.presenters.tasklist.adapter.TaskListAdapter;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends AppCompatActivity implements ITaskListView,
        View.OnClickListener,
        TaskListAdapter.OnCustomItemClickListener{

    private FloatingActionButton fab;
    private Toolbar toolbar;
    private TextView noData;
    private RecyclerView mRecyclerView;
    private List<Task> taskList = new ArrayList<>();
    private TaskListPresenter taskListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        taskListPresenter = new TaskListPresenter(this);

        fab = (FloatingActionButton) findViewById(R.id.act_task_list_fabAddEdit);
        fab.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        noData = (TextView) findViewById(R.id.act_task_list_tvNoData);

        mRecyclerView = (RecyclerView) findViewById(R.id.act_task_list_rvTaskList);


        taskListPresenter.onAttachedToolbar();

    }

    @Override
    protected void onResume() {
        super.onResume();
        taskListPresenter.onResumeLoadData(this);
    }

    @Override
    public void showAddEditActivity() {
        Intent intent = new Intent(this, TaskAddEdit.class);
        startActivity(intent);
    }

    @Override
    public void showAddEditActivityWithData(String id) {
        Intent intent = new Intent(this, TaskAddEdit.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void showToolbarTitle() {
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("My Tasks");
        }

    }

    @Override
    public void showLodedTaskNoData() {
        noData.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadedWithData(List<Task> taskList) {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        this.taskList = taskList;
        mRecyclerView.setAdapter(new TaskListAdapter(this.taskList, this));
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.act_task_list_fabAddEdit) {
            taskListPresenter.onClickedAddEdit();
        }

    }


    @Override
    public void onItemClicked(Task task) {

        taskListPresenter.onClickAddEditWithData(task.getId());
    }
}
