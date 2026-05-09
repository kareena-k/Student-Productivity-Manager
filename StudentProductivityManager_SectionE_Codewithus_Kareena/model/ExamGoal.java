package model;

public class ExamGoal extends Goal {
    private String subject;
    private int targetScore;
    private int currentScore;

    public ExamGoal(String subject, int targetScore) {
        super("Exam: " + subject);
        this.subject = subject;
        this.targetScore = targetScore;
        this.currentScore = 0;
    }

    @Override
    public void trackProgress(int score) {
        currentScore = score;
        if (currentScore >= targetScore) {
            completed = true;
        }
    }

    public String getSubject() { return subject; }
    public int getTargetScore() { return targetScore; }
    public int getCurrentScore() { return currentScore; }

   

@Override
public String getSummary() {
    int percent = (targetScore > 0) ? (currentScore * 100 / targetScore) : 0;
    String status = completed ? "[COMPLETED]" : "[IN PROGRESS]";
    return "Exam [" + subject + "] — " + currentScore + "/" + targetScore + " (" + percent + "%) " + status;
}


}