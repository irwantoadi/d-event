package com.irwantostudio.devent.model;

public class Data {
    private String id_task, task, note, status, date;

    public Data() {
    }

    public Data(String id_task, String task, String note, String status, String date) {
        this.id_task = id_task;
        this.task = task;
        this.note = note;
        this.status = status;
        this.date = date;
    }

    public String getIdTask() {
        return id_task;
    }

    public void setIdTask(String id_task) {
        this.id_task = id_task;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
