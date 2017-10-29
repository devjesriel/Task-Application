package com.example.lenovo.task_application;

import android.app.Application;
import android.text.format.DateUtils;

import com.example.lenovo.task_application.di.component.BaseComponent;
import com.example.lenovo.task_application.di.component.DaggerBaseComponent;
import com.example.lenovo.task_application.di.module.ContextModule;
import com.example.lenovo.task_application.di.module.DateModule;

/**
 * Created by Lenovo on 29/10/2017.
 */

public class Base extends Application {

    private BaseComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerBaseComponent.builder()
                .contextModule(new ContextModule(this))
                .dateModule(new DateModule())
                .build();
    }

    public BaseComponent getComponent() {
        return component;
    }
}
