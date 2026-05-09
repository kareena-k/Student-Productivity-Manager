package utils;
import model.*;
import java.io.*;
import java.util.*;

public class FileManager {
    private static final String FILE_NAME = "data.txt";

    public static void save(User user) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println(user.getName());
            for (Goal g : user.getGoals()) {
                if (g instanceof StudyGoal) {
                    StudyGoal sg = (StudyGoal) g;
                    writer.println("GOAL," + sg.getTitle() + "," + sg.getTargetHours() + "," + sg.getCompletedHours());
                } else if (g instanceof ExamGoal) {
                    ExamGoal eg = (ExamGoal) g;
                    writer.println("EXAMGOAL," + eg.getSubject() + "," + eg.getTargetScore() + "," + eg.getCurrentScore());
                }
            }
            for (Progress p : user.getProgressList()) {
                writer.println("PROGRESS," + p.getHoursStudied());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public static User load() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return null;

        try (Scanner sc = new Scanner(file)) {
            if (!sc.hasNextLine()) {
                System.out.println("Warning: save file is empty. Starting fresh.");
                return null;
            }
            String name = sc.nextLine();
            User user = new User(name);

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",");

                if (parts[0].equals("GOAL") && parts.length == 4) {
                    try {
                        StudyGoal sg = new StudyGoal(parts[1], Integer.parseInt(parts[2]));
                        sg.trackProgress(Integer.parseInt(parts[3]));
                        user.addGoal(sg);
                    } catch (NumberFormatException e) {
                        System.out.println("Warning: skipping corrupted goal entry: " + line);
                    }
                } else if (parts[0].equals("EXAMGOAL") && parts.length == 4) {
                    try {
                        ExamGoal eg = new ExamGoal(parts[1], Integer.parseInt(parts[2]));
                        eg.trackProgress(Integer.parseInt(parts[3]));
                        user.addGoal(eg);
                    } catch (NumberFormatException e) {
                        System.out.println("Warning: skipping corrupted exam goal entry: " + line);
                    }
                } else if (parts[0].equals("PROGRESS") && parts.length == 2) {
                    try {
                        user.addProgress(new Progress(Integer.parseInt(parts[1])));
                    } catch (NumberFormatException e) {
                        System.out.println("Warning: skipping corrupted progress entry: " + line);
                    }
                } else {
                    System.out.println("Warning: unrecognized line, skipping: " + line);
                }
            }
            return user;

        } catch (FileNotFoundException e) {
            System.out.println("Warning: could not open save file. Starting fresh.");
            return null;
        }
    }
}