package com.example.lenovo.task_application.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.task_application.R;
import com.example.lenovo.task_application.data.Task;
import com.example.lenovo.task_application.enums.TaskType;
import com.example.lenovo.task_application.presenters.taskaddedit.ITaskAddEditView;
import com.example.lenovo.task_application.presenters.taskaddedit.TaskAddEditPresenter;

import java.util.Calendar;
import java.util.UUID;

public class TaskAddEdit extends AppCompatActivity implements ITaskAddEditView,
        View.OnClickListener {

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private EditText mTitle, mDescription;
    private TaskAddEditPresenter presenter;
    private Task task;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add_edit);

        presenter = new TaskAddEditPresenter(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.act_task_addedit_fabDone);
        floatingActionButton.setOnClickListener(this);
        floatingActionButton.setTag(R.drawable.action_done);

        mTitle = (EditText) findViewById(R.id.act_task_addedit_etTitle);
        mDescription = (EditText) findViewById(R.id.act_task_addedit_etDesc);

        presenter.onAttachedToolbar();

        if(getIntent().getExtras() != null){
            String id = getIntent().getStringExtra("id");
            presenter.onExtrasNotNull(this, id);
        }

    }

    @Override
    public void showToolbarTitle() {

        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.toolbar_add);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


    }

    @Override
    public void onTaskSuccessfullyAdded() {

        Toast.makeText(this, R.string.act_task_addedit_success_added, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, TaskList.class));
    }

    @Override
    public void showTaskEditMode(Task task) {

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.toolbar_edit);
        }

        floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.action_edit));
        floatingActionButton.setTag(R.drawable.action_edit);

        //setup task
        this.task = task;

        mTitle.setText(task.getName());
        mDescription.setText(task.getDescription());
    }

    @Override
    public void showNullTask() {

        Toast.makeText(this, R.string.task_null, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTaskSuccessfyllyEdited() {

        Toast.makeText(this, R.string.act_task_addedit_success_edit, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, TaskList.class));
    }

    @Override
    public void showTaskSuccessfullyDeleted() {

        Toast.makeText(this, R.string.act_task_addedit_deleted, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, TaskList.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addedit, menu);
        menuItem = menu.findItem(R.id.action_delete);

        if(getIntent().getExtras() == null){
            menuItem.setVisible(false);
        }

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        if(item.getItemId() == R.id.action_delete){
            presenter.onClickedDelete(this, task.getId());
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.act_task_addedit_fabDone && view.getTag().toString().equals(String.valueOf(R.drawable.action_done))) {

            String title = mTitle.getText().toString();
            String description = mDescription.getText().toString();

            task = new Task(UUID.randomUUID().toString(), title, description, getDateNow(), getDateNow());
            presenter.onClickFab(TaskType.NEW_TASK, this, task, task.getId());
        }

        if (view.getId() == R.id.act_task_addedit_fabDone && view.getTag().toString().equals(String.valueOf(R.drawable.action_edit))) {

            String title = mTitle.getText().toString();
            String description = mDescription.getText().toString();
            String taskId = task.getId();

            task = new Task(UUID.randomUUID().toString(), title, description, getDateNow(), getDateNow());
            presenter.onClickFab(TaskType.EXISTING_TASK, this, task, taskId);
        }
    }


    private String getDateNow() {
        return java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
    }


}
