package utils;

public class ComparisonDetector {

    public String analyzeThought(String input) {
        input = input.toLowerCase();

        if (input.contains("better") ||
            input.contains("others") ||
            input.contains("friends") ||
            input.contains("marks") ||
            input.contains("compare")) {

            return "comparison";
        }

        if (input.contains("alone") ||
            input.contains("sad") ||
            input.contains("tired") ||
            input.contains("depressed") ||
            input.contains("stress")) {

            return "negative";
        }

        return "positive";
    }
}