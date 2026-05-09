package model;

public class Progress {
    private int hoursStudied;

    public Progress(int hoursStudied) {
        this.hoursStudied = hoursStudied;
    }

    public int getHoursStudied() {
        return hoursStudied;
    }

    public int calculateScore() {
        return hoursStudied * 10;
    }
}