package symptom_support;

import java.util.ArrayList;
import java.util.List;

/**
 * Detects high-risk symptom combinations using predefined rules.
 * Safety decisions stay separate from AI so urgent warnings are consistent
 */
public class RiskAssessment
{
    public List<Recommendation> assessRisk(SymptomReport report)
    {
        InputValidator.validateReport(report);
        List<Symptom> symptoms = report.getSymptoms();
        List<Recommendation> warnings = new ArrayList<>();

        // combination 1 : chest pain + shortness of breath. may indicate serious cardiopulmonary risk
        if (hasSymptom(symptoms, "chest-pain") && hasSymptom(symptoms,"shortness-of-breath"))
        {
            warnings.add(new Recommendation("Chest pain with shortness of breath may be an indicator " +
                    "of a serious condition. Seek medical attention immediately.",
                    RecommendationType.URGENT_WARNING,
                    UrgencyLevel.URGENT));
        }

        // combination 2 : high fever lasting multiple days. override normal self-care advice.
        for (Symptom s : symptoms)
        {
            if (s.getName().equals("fever") && s.getSeverity() >= 9 && s.getDurationDays() >= 3)
            {
                warnings.add(new Recommendation("High fever lasting multiple days. Seek medical attention.",
                        RecommendationType.SEEK_MEDICAL_ATTENTION,
                        UrgencyLevel.HIGH));
            }
        }

        // dizziness + fainting. a serious underlying issue.
        if (hasSymptom(symptoms, "dizziness") && hasSymptom(symptoms, "fainting"))
        {
            warnings.add(new Recommendation("Dizziness combined with fainting is a serious symptom. Seek urgent care.",
                    RecommendationType.URGENT_WARNING,
                    UrgencyLevel.URGENT));
        }
        return warnings;
    }

    private boolean hasSymptom(List<Symptom> symptoms, String name)
    {
        for (Symptom s : symptoms)
        {
            if (s.getName().equals(name)) return true;
        }
        return false;
    }



}


