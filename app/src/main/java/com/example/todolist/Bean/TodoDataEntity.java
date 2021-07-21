package com.example.todolist.Bean;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;


public class TodoDataEntity extends SugarRecord {
    int userId;
    String title;
    Boolean completed;

    public TodoDataEntity(){}

    public TodoDataEntity(int userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "TodoDataEntity{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
