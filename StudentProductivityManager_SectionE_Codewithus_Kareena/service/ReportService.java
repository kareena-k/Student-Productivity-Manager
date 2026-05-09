package service;
import model.*;

public class ReportService {
    public void generateReport(User user) {
        int totalHours = 0;
        int totalScore = 0;
        int completedGoals = 0;

        for (Progress p : user.getProgressList()) {
            totalHours += p.getHoursStudied();
            totalScore += p.calculateScore();
        }

        for (Goal g : user.getGoals()) {
            if (g.isCompleted()) completedGoals++;
        }

        System.out.println("\n===== REPORT =====");
        System.out.println("User        : " + user.getName());
        System.out.println("Total Hours : " + totalHours);
        System.out.println("Productivity: " + totalScore);
        System.out.println("Goals done  : " + completedGoals + "/" + user.getGoals().size());
        System.out.println("\nGoal Details:");

        for (Goal g : user.getGoals()) {
            // g.getSummary() — called on Goal type, resolved at runtime
            // StudyGoal prints hours, ExamGoal prints scores — same call, different output
            System.out.println("  " + g.getSummary());
        }
    }
}