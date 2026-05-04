package symptom_support;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class RecommendationEngineTest
{
    @Test
    void generateRecommendations_adds_urgent_warning()
    {
        SymptomReport report = new SymptomReport("r1", LocalDateTime.now(),
                "user1", List.of(
                        new Symptom("chest pain", 9, 1),
                        new Symptom("shortness of breath", 8, 1)));

        List<ConditionPrediction> predictions = List.of(
                new ConditionPrediction("anxiety", 0.5, report.getSymptoms(),
                        "Matched symptoms."));

        RecommendationEngine engine = new RecommendationEngine();
        List<Recommendation> recommendations = engine.generateRecommendations(report, predictions);
        Assertions.assertTrue(engine.containsUrgentRecommendation(recommendations));
    }
}