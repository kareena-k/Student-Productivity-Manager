package model;

public class StudyGoal extends Goal {
    private int targetHours;
    private int completedHours;

    public StudyGoal(String title, int targetHours) {
        super(title);
        this.targetHours = targetHours;
        this.completedHours = 0;
    }

    @Override
    public void trackProgress(int hours) {
        completedHours += hours;
        if (completedHours >= targetHours) {
            completed = true;
        }
    }

    @Override
public String getSummary() {
    int percent = (targetHours > 0) ? (completedHours * 100 / targetHours) : 0;
    String status = completed ? "[COMPLETED]" : "[IN PROGRESS]";
    return "Study [" + title + "] — " + completedHours + "/" + targetHours + "h (" + percent + "%) " + status;
}



    public int getTargetHours() { return targetHours; }
    public int getCompletedHours() { return completedHours; }
}