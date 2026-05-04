package symptom_support;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import symptom_support.exceptions.DuplicateSymptomException;

import java.time.LocalDateTime;
import java.util.List;

public class InputValidatorTest
{
    @Test
    void validateReport_rejects_duplicate_symptoms()
    {
        Symptom s1 = new Symptom("Headache", 5, 1);
        Symptom s2 = new Symptom(" headache ", 6, 2);

        SymptomReport report = new SymptomReport("r1", LocalDateTime.now(),
                "user1", List.of(s1, s2));

        Assertions.assertThrows(DuplicateSymptomException.class,
                () -> InputValidator.validateReport(report));
    }

    @Test
    void validateReport_accepts_valid_report()
    {
        SymptomReport report = new SymptomReport("r1", LocalDateTime.now(),
                "user1", List.of(new Symptom("fever", 8, 2)));

        Assertions.assertDoesNotThrow(() -> InputValidator.validateReport(report));
    }
    @Test
    void symptom_rejects_invalid_severity()
    {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Symptom("fever", 11, 2));
    }

    @Test
    void symptom_rejects_blank_name()
    {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Symptom("   ", 5, 1));
    }

    @Test
    void symptom_rejects_negative_duration()
    {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Symptom("cough", 5, -1));
    }

}