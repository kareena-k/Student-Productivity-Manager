package model;

public abstract class Goal implements Trackable {
    protected String title;
    protected boolean completed;

    public Goal(String title) {
        this.title = title;
        this.completed = false;
    }

    public String getTitle() { return title; }
    public boolean isCompleted() { return completed; }


    public abstract String getSummary();
}