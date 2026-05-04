package symptom_support;

import java.util.ArrayList;
import java.util.List;

/**
 * Combines urgent warnings with general recommendations based on predictions.
 * Keeps user facing guidance separate from prediction scoring.
 */
public class RecommendationEngine
{
    private final RiskAssessment riskAssessment;
    public RecommendationEngine() { riskAssessment = new RiskAssessment(); }

    public List<Recommendation> generateRecommendations(SymptomReport report, List<ConditionPrediction> predictions)
    {
        InputValidator.validateReport(report);
        if (predictions == null) throw new IllegalArgumentException("Predictions list cannot be null.");

        List<Recommendation> recommendations = new ArrayList<>();
        List<Recommendation> urgentWarnings = riskAssessment.assessRisk(report);
        // urgent warnings are added first so safety guidance appears before general advice
        recommendations.addAll(urgentWarnings);

        for (ConditionPrediction prediction : predictions)
        {
            String condition = prediction.getConditionName();
            if (condition.equals("flu"))
            {
                recommendations.add(new Recommendation("Drink fluids and rest. Monitor your fever or worsening symptoms.",
                        RecommendationType.SELF_CARE,
                        UrgencyLevel.MODERATE));
            }
            else if (condition.equals("cold"))
            {
                recommendations.add(new Recommendation("Drink fluids and rest. Monitor your symptoms over the next few days.",
                        RecommendationType.SELF_CARE,
                        UrgencyLevel.LOW));
            }
            else if (condition.equals("migraine"))
            {
                recommendations.add(new Recommendation("Rest in a quiet room and monitor headache severity.",
                        RecommendationType.MONITOR,
                        UrgencyLevel.MODERATE));
            }
            else if (condition.equals("dehydration"))
            {
                recommendations.add(new Recommendation("Increase fluid intake and monitor dizziness and fatigue.",
                        RecommendationType.MONITOR,
                        UrgencyLevel.MODERATE));
            }
            else if (condition.equals("covid-19") || condition.equals("covid"))
            {
                recommendations.add(new Recommendation(
                        "Isolation is necessary. Rest and monitor breathing symptoms or fever.",
                        RecommendationType.MONITOR,
                        UrgencyLevel.HIGH));
            }
            else if (condition.equals("food-poisoning"))
            {
                recommendations.add(new Recommendation(
                        "Stay hydrated and rest. Seek medical attention if symptoms persist.",
                        RecommendationType.SEEK_MEDICAL_ATTENTION,
                        UrgencyLevel.MODERATE));
            }
            else if (condition.equals("allergies"))
            {
                recommendations.add(new Recommendation(
                        "Avoid known triggers and use over the counter medicine.",
                        RecommendationType.SELF_CARE,
                        UrgencyLevel.LOW));
            }
            else if (condition.equals("bronchitis"))
            {
                recommendations.add(new Recommendation(
                        "Rest and stay hydrated. Monitor breathing or coughing symptoms.",
                        RecommendationType.MONITOR,
                        UrgencyLevel.MODERATE));
            }
            else if (condition.equals("strep-throat"))
            {
                recommendations.add(new Recommendation(
                        "Seek medical evaluation for possible antibiotics if symptoms are severe.",
                        RecommendationType.SEEK_MEDICAL_ATTENTION,
                        UrgencyLevel.HIGH));
            }
            else if (condition.equals("urinary-tract-infection"))
            {
                recommendations.add(new Recommendation(
                        "Increase fluid intake and consult a doctor if symptoms persist.",
                        RecommendationType.SEEK_MEDICAL_ATTENTION,
                        UrgencyLevel.MODERATE));
            }
            else if (condition.equals("heat-exhaustion"))
            {
                recommendations.add(new Recommendation(
                        "Cool down and hydrate. Rest in a shaded or air-conditioned area.",
                        RecommendationType.SELF_CARE,
                        UrgencyLevel.HIGH));
            }
            else if (condition.equals("appendicitis"))
            {
                recommendations.add(new Recommendation(
                        "Seek immediate medical attention for possible appendicitis.",
                        RecommendationType.URGENT_WARNING,
                        UrgencyLevel.URGENT));
            }
            else if (condition.equals("hypertension"))
            {
                recommendations.add(new Recommendation(
                        "Monitor blood pressure regularly and consult a healthcare provider.",
                        RecommendationType.MONITOR,
                        UrgencyLevel.MODERATE));
            }
            else if (condition.equals("asthma"))
            {
                recommendations.add(new Recommendation(
                        "Use prescribed inhalers and monitor breathing closely.",
                        RecommendationType.MONITOR,
                        UrgencyLevel.HIGH));
            }
        }
        return recommendations;
    }

    public boolean containsUrgentRecommendation(List<Recommendation> recommendations)
    {
        if (recommendations == null) throw new IllegalArgumentException("Recommendations list cannot be null.");
        for (Recommendation recommendation : recommendations)
        {
            if (recommendation.isUrgent()) return true;
        }
        return false;
    }
}
