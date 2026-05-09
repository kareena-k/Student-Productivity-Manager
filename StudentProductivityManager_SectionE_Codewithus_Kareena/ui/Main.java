package ui;
import java.util.*;
import model.*;
import service.*;
import utils.*;
import exceptions.*;

public class Main {

    private static int readInt(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a number: ");
            }
        }
    }

    private static int readPositiveInt(Scanner sc, String fieldName) {
        while (true) {
            int val = readInt(sc);
            if (val > 0) return val;
            System.out.print(fieldName + " must be greater than 0. Try again: ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GoalService goalService = new GoalService();
        ProgressService progressService = new ProgressService();
        ReportService reportService = new ReportService();
        MotivationService motivationService = new MotivationService();
        ComparisonDetector detector = new ComparisonDetector();

        User user = FileManager.load();
        if (user == null) {
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            user = new User(name);
        }

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add Study Goal");
            System.out.println("2. Add Study Progress");
            System.out.println("3. View Report");
            System.out.println("4. Enter Thought");
            System.out.println("5. Motivation");
            System.out.println("6. Add Exam Goal");   // swapped
            System.out.println("7. Exit");             // swapped
            System.out.print("Choice: ");

            int choice = readInt(sc);

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Goal title: ");
                        String title = sc.nextLine();
                        System.out.print("Target hours: ");
                        int h = readPositiveInt(sc, "Target hours");
                        goalService.addGoal(user, new StudyGoal(title, h));
                        break;
                    case 2:
                        System.out.print("Hours studied: ");
                        int hours = readPositiveInt(sc, "Hours studied");
                        progressService.addProgress(user, new Progress(hours));
                        break;
                    case 3:
                        reportService.generateReport(user);
                        break;
                    case 4:
                        System.out.print("Enter thought: ");
                        String thought = sc.nextLine();
                        String type = detector.analyzeThought(thought);
                        System.out.println(motivationService.getMessage(type));
                        break;
                    case 5:
                        System.out.println(motivationService.randomMotivation());
                        break;
                    case 6:                             // was 7 — now Exam Goal
                        System.out.print("Subject name: ");
                        String subject = sc.nextLine();
                        System.out.print("Target score: ");
                        int target = readPositiveInt(sc, "Target score");
                        goalService.addGoal(user, new ExamGoal(subject, target));
                        System.out.print("Enter current score (or 0 to set later): ");
                        int current = readInt(sc);
                        if (current > 0) {
                            List<Goal> goals = user.getGoals();
                            goals.get(goals.size() - 1).trackProgress(current);
                        }
                        break;
                    case 7:                             // was 6 — now Exit
                        FileManager.save(user);
                        System.out.println("Saved. Goodbye!");
                        return;
                    default:
                        throw new InvalidInputException("Invalid choice! Enter 1-7.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}