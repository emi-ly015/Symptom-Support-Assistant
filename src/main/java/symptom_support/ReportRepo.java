package symptom_support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Saves completed symptom reports as JSON files.
 */
public class ReportRepo
{
    private final Path outputDir;
    private final ObjectMapper mapper;

    public ReportRepo(Path outputDir)
    {
        if (outputDir == null) throw new IllegalArgumentException(
                "Output Directory cannot be null.");
        this.outputDir = outputDir;
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.mapper.findAndRegisterModules();
    }

    public void saveReport(SymptomReport report)
    {
        InputValidator.validateReport(report);
        try {
            // create the reports folder if it does not already exist
            Files.createDirectories(outputDir);
            Path reportPath = outputDir.resolve(report.getReportId() + ".json");
            mapper.writeValue(reportPath.toFile(), report);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not save report: "+ report.getReportId(), e);
        }
    }
}
