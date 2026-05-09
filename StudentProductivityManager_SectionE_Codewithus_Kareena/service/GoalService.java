package service;
import model.*;

public class GoalService {
    public void addGoal(User user, Goal goal) {
        user.addGoal(goal);   // was: user.getGoals().add(goal)
        System.out.println("Goal added successfully!");
    }
}