package com.world.to_dolistapp;

public class TaskModel {
    private String taskName;
    private String dueDate;
    private String priority;

    public TaskModel(String taskName, String dueDate, String priority) {
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.priority = priority;
    }


    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
