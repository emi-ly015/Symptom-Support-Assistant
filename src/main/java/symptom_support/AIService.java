package symptom_support;

/**
 * Defines the contract for AI-assisted explanation generation.
 * Using an interface allows me to add a real AI implementation later without changing PredictionEngine
 * since this is a project I am interested in continuing over the summer.
 */
public interface AIService
{
    String generateExplanation(String conditionName, String baseExplanation);
}