package com.example.lenovo.task_application.di.module;

import android.content.Context;

import com.example.lenovo.task_application.di.scope.BaseScope;

import java.text.DateFormat;
import java.util.Calendar;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lenovo on 29/10/2017.
 */

@Module
public class DateModule {

    @Provides
    @Named ("date_now")
    String providesDateNow(){
        return DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
    }
}
