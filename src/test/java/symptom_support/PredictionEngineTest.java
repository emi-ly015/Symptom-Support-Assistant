package symptom_support;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class PredictionEngineTest
{
    @Test
    void predict_returns_flu_for_matching_symptoms()
    {
        Map<String, List<String>> data = Map.of(
                "flu", List.of("fever", "cough", "fatigue"),
                "migraine", List.of("headache", "nausea"));

        PredictionEngine engine = new PredictionEngine(data, new FakeAIService());

        SymptomReport report = new SymptomReport("r1", LocalDateTime.now(),
                "user1", List.of(
                        new Symptom("fever", 9, 2),
                        new Symptom("cough", 7, 1)));

        List<ConditionPrediction> predictions = engine.predict(report);

        Assertions.assertFalse(predictions.isEmpty());
        Assertions.assertEquals("flu", predictions.get(0).getConditionName());
        Assertions.assertTrue(predictions.get(0).getConfidenceScore() > 0);
        Assertions.assertFalse(predictions.get(0).getExplanation().isEmpty());
        Assertions.assertTrue(predictions.get(0).getExplanation().contains("Matched"));
    }
}