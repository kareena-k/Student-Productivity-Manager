package service;

import java.util.*;

public class MotivationService {

    private String[] quotes = {
        "Focus on your journey, not others.",
        "Small progress is still progress.",
        "Discipline beats motivation.",
        "You are improving every day.",
        "Stay consistent!"
    };

    public String getMessage(String type) {

    switch (type) {

        case "comparison":
            return "⚠ Comparison detected!\nFocus on YOUR journey. You are not in competition with others.";

        case "negative":
            return "💙 It's okay to feel this way.\nTake a small step today. Progress matters, not perfection.";

        default:
            return "✅ Great mindset! Keep moving forward, you're doing well.";
    }
}

    public String randomMotivation() {
        Random rand = new Random();
        return quotes[rand.nextInt(quotes.length)];
    }
}