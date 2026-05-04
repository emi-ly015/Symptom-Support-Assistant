package symptom_support.exceptions;

public class DuplicateSymptomException extends RuntimeException {
    public DuplicateSymptomException(String symptomName) {
        super("Duplicate symptom found: " + symptomName);
    }
}
