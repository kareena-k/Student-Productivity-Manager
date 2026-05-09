package service;
import model.*;

public class ProgressService {
    public void addProgress(User user, Progress progress) {
        user.addProgress(progress);   // was: user.getProgressList().add(progress)
        for (Goal g : user.getGoals()) {
            g.trackProgress(progress.getHoursStudied());
        }
        System.out.println("Progress added!");
    }
}