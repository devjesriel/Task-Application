package com.example.lenovo.task_application.di.component;

import com.example.lenovo.task_application.di.module.ContextModule;
import com.example.lenovo.task_application.di.module.DateModule;
import com.example.lenovo.task_application.di.scope.BaseScope;
import com.example.lenovo.task_application.ui.TaskAddEdit;
import com.example.lenovo.task_application.ui.TaskList;

import dagger.Component;

/**
 * Created by Lenovo on 29/10/2017.
 */

@BaseScope
@Component(modules = {ContextModule.class, DateModule.class})
public interface BaseComponent {

    void inject(TaskAddEdit activity);
    void inject(TaskList activity);

}
