package symptom_support;

import symptom_support.exceptions.InvalidSymptomReportException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class InputValidator
{
    private InputValidator() {}
    public static void validateReport(SymptomReport report)
            throws InvalidSymptomReportException
    {
        if (report == null) throw new InvalidSymptomReportException(
                "Report cannot be null.");

        List<Symptom> symptoms = report.getSymptoms();
        if (symptoms == null) throw new InvalidSymptomReportException(
                "Symptom list cannot be null.");
        if (symptoms.isEmpty()) throw new InvalidSymptomReportException(
                "Symptom list cannot be empty.");

        Set<String> seen = new HashSet<>();
        for (Symptom symptom : symptoms)
        {
            if (symptom == null) throw new InvalidSymptomReportException(
                    "Symptom list cannot be null.");
            if (!seen.add(symptom.getName())) throw new InvalidSymptomReportException(
                    "Duplicate symptom found: " + symptom.getName());
        }
    }
}
