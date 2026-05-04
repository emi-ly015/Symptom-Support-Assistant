package symptom_support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Generates ranked condition predictions using symptom-condition mappings.
 * AI is only used to enhance explanations.
 */
public class PredictionEngine
{
    private final Map<String, List<String>> conditionSymptoms;
    private final AIService aiService;

    public PredictionEngine(Map<String, List<String>> conditionSymptoms, AIService aiService)
    {
        if (conditionSymptoms == null || conditionSymptoms.isEmpty())
            throw new IllegalArgumentException("Condition symptoms cannot be null or empty.");
        if (aiService == null)
            throw new IllegalArgumentException("AI service cannot be null.");
        this.conditionSymptoms = conditionSymptoms;
        this.aiService = aiService;
    }

    public List<ConditionPrediction> predict(SymptomReport report)
    {
        InputValidator.validateReport(report);

        List<Symptom> userSymptoms = report.getSymptoms();
        List<ConditionPrediction> predictions = new ArrayList<>();

        for (String conditionName : conditionSymptoms.keySet())
        {
            List<String> expectedSymptoms = conditionSymptoms.get(conditionName);
            List<Symptom> matchedSymptoms = findMatchedSymptoms(userSymptoms, expectedSymptoms);

            if (!matchedSymptoms.isEmpty())
            {
                double score = calculateScore(matchedSymptoms);
                double confidence= calculateConfidence(score, expectedSymptoms);
                String baseExplanation = createExplanation(conditionName, matchedSymptoms);
                String explanation = aiService.generateExplanation(conditionName, baseExplanation);
                predictions.add(new ConditionPrediction(conditionName, confidence, matchedSymptoms, explanation));
            }
        }
        // sort highest confidence first so the most likely conditions appear at the top
        predictions.sort((a,b) -> Double.compare(b.getConfidenceScore(), a.getConfidenceScore()));
        // return the top 3 predictions only
        if (predictions.size() > 3) return new ArrayList<>(predictions.subList(0,3));

        return predictions;
    }

    private double calculateScore(List<Symptom> matchedSymptoms)
    {
        double score = 0.0;
        for (Symptom symptom : matchedSymptoms) score += symptom.getSeverity();
        return score;
    }

    // confidence score must be from 0.0 to 1.0.
    private double calculateConfidence(double score, List<String> expectedSymptoms)
    {
        double maxScore = expectedSymptoms.size() * 10.0;
        return score / maxScore;
    }

    private List<Symptom> findMatchedSymptoms(List<Symptom> userSymptoms, List<String> expectedSymptoms)
    {
        List<Symptom> matches = new ArrayList<>();
        for (Symptom symptom : userSymptoms)
        {
            if (expectedSymptoms.contains(symptom.getName())) matches.add(symptom);
        }
        return matches;
    }

    private String createExplanation (String conditionName, List<Symptom> matchedSymptoms)
    {
        return "Matched " + matchedSymptoms.size() + " symptom(s) for " + conditionName + ".";
    }

}
