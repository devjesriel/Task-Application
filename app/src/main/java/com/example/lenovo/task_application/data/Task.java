package com.example.lenovo.task_application.data;

import java.util.Date;

/**
 * Created by Lenovo on 28/7/2017.
 */

public class Task {

    private String id, name, description, dateCreated, dateUpdated;

    public Task(String id, String name, String description, String dateCreated, String dateUpdated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }
}
