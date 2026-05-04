package symptom_support;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents one prediction result. The matched symptoms are stored so the user can see why a condition was ranked.
 */
public class ConditionPrediction
{
    private final String conditionName;
    private final double confidenceScore;
    private final List<Symptom> matchedSymptoms;
    private final String explanation;

    public ConditionPrediction(String conditionName, double confidenceScore,
                               List<Symptom> matchedSymptoms, String explanation)
    {
        if (conditionName == null) throw new IllegalArgumentException(
                "Condition cannot be null or empty.");

        String normalizedCondition = normalize(conditionName);

        if (normalizedCondition.isEmpty()) throw new IllegalArgumentException(
                "Condition cannot be null or empty.");
        if (confidenceScore < 0.0 || confidenceScore > 1.0) throw new IllegalArgumentException
                ("Confidence score must be between 0.0 and 1.0.");
        if (matchedSymptoms == null) throw new IllegalArgumentException(
                "Matched symptoms cannot be null.");
        if (explanation == null || explanation.trim().isEmpty())
            throw new IllegalArgumentException(
                "Explanation cannot be null or empty.");

        this.conditionName =normalizedCondition;
        this.confidenceScore = confidenceScore;
        // copy the list so outside code cannot change this prediction after creation
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
