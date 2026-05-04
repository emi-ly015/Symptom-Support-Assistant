package symptom_support;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

public class ReportRepoTest
{
    @TempDir
    Path tempDir;

    @Test
    void saveReport_creates_json_file()
    {
        ReportRepo repo = new ReportRepo(tempDir);

        SymptomReport report = new SymptomReport("r1", LocalDateTime.now(),
                "user1", List.of(new Symptom("fever", 8, 2)));
        repo.saveReport(report);

        Assertions.assertTrue(Files.exists(tempDir.resolve("r1.json")));
    }
}