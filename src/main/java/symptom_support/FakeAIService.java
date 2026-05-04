package symptom_support;

/**
 * Mimics AI-generated explanations without calling an external API.
 */
public class FakeAIService implements  AIService
{
    @Override
    public String generateExplanation(String conditionName, String baseExplanation)
    {
        if (conditionName == null || conditionName.isEmpty())
            throw new IllegalArgumentException("Condition name cannot be null or empty.");
        if (baseExplanation == null || baseExplanation.isEmpty())
            throw new IllegalArgumentException("Base explanation cannot be null or empty.");

        return baseExplanation + " Based on the reported symptoms, this condition may be relevant " +
                "but this result is not a medical diagnosis.";
    }

}
