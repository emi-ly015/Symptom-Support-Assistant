package symptom_support;

import symptom_support.exceptions.DuplicateSymptomException;
import symptom_support.exceptions.InvalidSymptomException;
import symptom_support.exceptions.InvalidSymptomReportException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Keeps invalid reports from reaching the engine.
 */
public class InputValidator
{
    private InputValidator() {}
    public static void validateReport(SymptomReport report)
    {
        if (report == null) throw new InvalidSymptomReportException(
                "Report cannot be null.");
        List<Symptom> symptoms = report.getSymptoms();

        if (symptoms == null) throw new InvalidSymptomReportException(
                "Symptom list cannot be null.");
        if (symptoms.isEmpty()) throw new InvalidSymptomReportException(
                "Symptom list cannot be empty.");

         // A set is used because symptom names must be unique within one report
        Set<String> seen = new HashSet<>();
        for (Symptom symptom : symptoms)
        {
            validateSymptom(symptom);

            if (!seen.add(symptom.getName())) throw new DuplicateSymptomException(symptom.getName());
        }
    }
    public static void validateSymptom(Symptom symptom)
    {
        if (symptom == null) throw new InvalidSymptomException("Symptom cannot be null.");
        if (symptom.getName() == null || symptom.getName().isEmpty()) throw new
                InvalidSymptomException("Symptom name cannot be null or empty.");
        if (symptom.getSeverity() < 1 || symptom.getSeverity() > 10) throw new
                InvalidSymptomException("Severity must be between 1 and 10.");
        if (symptom.getDurationDays() < 0) throw new InvalidSymptomException("Duration cannot be negative.");

    }
}
