package model;
import java.util.*;

public class User {
    private String name;
    private List<Goal> goals;
    private List<Progress> progressList;

    public User(String name) {
        this.name = name;
        goals = new ArrayList<>();
        progressList = new ArrayList<>();
    }

    public String getName() { return name; }

    // Returns a copy — outside code cannot mutate the internal list
    public List<Goal> getGoals() {
        return new ArrayList<>(goals);
    }

    public List<Progress> getProgressList() {
        return new ArrayList<>(progressList);
    }

    // Controlled add methods — only way to add from outside
    public void addGoal(Goal g) {
        goals.add(g);
    }

    public void addProgress(Progress p) {
        progressList.add(p);
    }
}