package com.example.lenovo.task_application.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.task_application.Base;
import com.example.lenovo.task_application.R;
import com.example.lenovo.task_application.R2;
import com.example.lenovo.task_application.data.Task;
import com.example.lenovo.task_application.enums.TaskType;
import com.example.lenovo.task_application.presenters.taskaddedit.ITaskAddEditView;
import com.example.lenovo.task_application.presenters.taskaddedit.TaskAddEditPresenter;

import java.util.Calendar;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskAddEdit extends AppCompatActivity implements ITaskAddEditView {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    @BindView(R2.id.act_task_addedit_fabDone)
    FloatingActionButton fabButton;

    @BindView(R2.id.act_task_addedit_etTitle)
    EditText mTitle;

    @BindView(R2.id.act_task_addedit_etDesc)
    EditText mDescription;

    @Inject
    @Named("date_now")
    String dateNow;

    @Inject
    TaskAddEditPresenter presenter;

    private Task task;

    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add_edit);

        ((Base) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);

        presenter.setiTaskAddEditView(this);

        fabButton.setTag(R.drawable.action_done);

        presenter.onAttachedToolbar();

        if (getIntent().getExtras() != null) {
            String id = getIntent().getStringExtra("id");
            presenter.onExtrasNotNull(this, id);
        }

    }

    /**
     * Setting up
     * toolbar
     */
    @Override
    public void showToolbarTitle() {

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.toolbar_add);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


    }

    /**
     * Prompts user
     * when task is succesfully added
     */
    @Override
    public void onTaskSuccessfullyAdded() {

        Toast.makeText(this, R.string.act_task_addedit_success_added, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, TaskList.class));
    }

    /**
     * Enables user to edit
     * tasks
     * @param task - task to edit
     */
    @Override
    public void showTaskEditMode(Task task) {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.toolbar_edit);
        }

        fabButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.action_edit));
        fabButton.setTag(R.drawable.action_edit);

        //setup task
        this.task = task;

        mTitle.setText(task.getName());
        mDescription.setText(task.getDescription());
    }

    /**
     * Prompts if task
     * is equals to null
     */
    @Override
    public void showNullTask() {

        Toast.makeText(this, R.string.task_null, Toast.LENGTH_SHORT).show();
    }

    /**
     * Prompts user
     * when task has been
     * sucessfully edited
     */
    @Override
    public void showTaskSuccessfyllyEdited() {

        Toast.makeText(this, R.string.act_task_addedit_success_edit, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, TaskList.class));
    }

    /**
     * Prompts user when
     * task is sucessfully deleted
     */
    @Override
    public void showTaskSuccessfullyDeleted() {

        Toast.makeText(this, R.string.act_task_addedit_deleted, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, TaskList.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addedit, menu);
        menuItem = menu.findItem(R.id.action_delete);

        if (getIntent().getExtras() == null) {
            menuItem.setVisible(false);
        }

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /**
         * On click back button
         * in toolbar gets to
         * last activity
         */
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        /**
         * Attempt to delete a
         * task onClicked
         */
        if (item.getItemId() == R.id.action_delete) {
            presenter.onClickedDelete(this, task.getId());
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Check whether fab is to edit
     * or add
     */
    @OnClick(R2.id.act_task_addedit_fabDone)
    void onClickFab() {

        /**
         * When useer is to add a new Task
         */
        if (Integer.parseInt(fabButton.getTag().toString()) == R.drawable.action_done) {

            String title = mTitle.getText().toString();
            String description = mDescription.getText().toString();

            task = new Task(UUID.randomUUID().toString(), title, description, dateNow, dateNow);
            presenter.onClickFab(TaskType.NEW_TASK, this, task, task.getId());
        }

        /**
         * When user is to edit existing task
         */
        if (Integer.parseInt(fabButton.getTag().toString()) == R.drawable.action_edit) {

            String title = mTitle.getText().toString();
            String description = mDescription.getText().toString();
            String taskId = task.getId();

            task = new Task(UUID.randomUUID().toString(), title, description, dateNow, dateNow);
            presenter.onClickFab(TaskType.EXISTING_TASK, this, task, taskId);
        }

    }


}
