package symptom_support;

import java.util.ArrayList;
import java.util.List;

/**
 * ConditionPrediction : represents a predicted condition with a confidence score.
 * Three likely conditions will be presented and each gets a score.
 * **/
public class ConditionPrediction
{
    private final String conditionName;
    private final double confidenceScore;
    private final List<Symptom> matchedSymptoms;
    private final String explanation;

    public ConditionPrediction(String conditionName, double confidenceScore,
                               List<Symptom> matchedSymptoms, String explanation)
    {
        if (conditionName == null || conditionName.isEmpty()) throw new IllegalArgumentException(
                "Condition cannot be null or empty.");
        if (confidenceScore < 0.0 || confidenceScore > 1.0) throw new IllegalArgumentException(
                "Confidence score must be between 0.0 and 1.0.");
        if (matchedSymptoms == null) throw new IllegalArgumentException(
                "Matched symptoms cannot be null.");
        if (explanation == null || explanation.isEmpty()) throw new IllegalArgumentException(
                "Explanation cannot be null or empty.");

        this.conditionName = normalize(conditionName);
        this.confidenceScore = confidenceScore;
        this.matchedSymptoms = new ArrayList<>(matchedSymptoms);
        this.explanation = explanation;
    }

    public String getConditionName() { return conditionName; }
    public double getConfidenceScore() { return confidenceScore; }
    public List<Symptom> getMatchedSymptoms() { return new ArrayList<>(matchedSymptoms); }
    public String getExplanation() { return explanation; }

    private static String normalize(String s) {
        return s.trim().toLowerCase().replaceAll("\\s+", "-");
    }
}
