package com.esgi.al.solistrophe.model;

public class Sinister {

    private String name;
    private String description;
    private int severity;
    private int state;
    private int id;
    private int accountId;

    public Sinister(String name, String description, int severity, int state, int id, int accountId) {
        this.name = name;
        this.description = description;
        this.severity = severity;
        this.state = state;
        this.id = id;
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
