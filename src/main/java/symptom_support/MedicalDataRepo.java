package symptom_support;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * Loads symptom-condition mappings from JSON.
 * Keeping this data outside PredictionEngine makes the prediction logic easier to update.
 */
public class MedicalDataRepo
{
    private final Path filePath;

    public MedicalDataRepo(Path filePath)
    {
        if (filePath == null) throw new IllegalArgumentException(
                "Medical data file path cannot be null.");
        this.filePath = filePath;
    }

    public Map<String, List<String>> loadConditionSymptoms()
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            Map<String, List<String>> data = mapper.readValue(filePath.toFile(),
                    new TypeReference<Map<String, List<String>>>() {} );
            // validate external JSON before the prediction engine relies on it
            validateMedicalData(data);
            return data;
        } catch (IOException e)
        {
            throw new IllegalArgumentException("Could not load medical data from file: " + filePath, e);
        }
    }

    private void validateMedicalData(Map<String, List<String>> data)
    {
        if (data == null || data.isEmpty()) throw new IllegalArgumentException(
                "Medical data cannot be null or empty.");
        for (String condition : data.keySet())
        {
            if (condition == null || condition.isEmpty()) throw new IllegalArgumentException(
                    "Condition name cannot be null or empty.");
            List<String> symptoms = data.get(condition);

            if (symptoms == null || symptoms.isEmpty()) throw new IllegalArgumentException(
                    "Condition must have at least one symptom: " + condition);

            for (String symptom : symptoms)  if (symptom == null || symptom.isEmpty())
                throw new IllegalArgumentException("Symptom cannot be null or empty for condition: " + condition);

        }

    }
}
