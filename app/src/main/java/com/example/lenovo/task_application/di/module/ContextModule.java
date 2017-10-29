package com.example.lenovo.task_application.di.module;

import android.content.Context;

import com.example.lenovo.task_application.di.scope.BaseScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lenovo on 29/10/2017.
 */

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @BaseScope
    Context providesContext(){
        return context;
    }
}
