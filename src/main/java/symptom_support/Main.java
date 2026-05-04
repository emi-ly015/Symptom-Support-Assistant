package symptom_support;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 * Console entry point for the application. This class connects everything into one runnable pipeline.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // load condition data from JSON before creating the prediction engine
            MedicalDataRepo medicalDataRepo = new MedicalDataRepo(Path.of
                    ("src/main/resources/medical-data.json"));

            Map<String, List<String>> conditionData = medicalDataRepo.loadConditionSymptoms();

            AIService aiService = new FakeAIService();
            PredictionEngine predictionEngine = new PredictionEngine(conditionData, aiService);

            RecommendationEngine recommendationEngine = new RecommendationEngine();
            ReportRepo reportRepo = new ReportRepo(Path.of("reports"));
            System.out.println("Symptom Support Assistant");
            System.out.println("This tool provides guidance only and is not a medical diagnosis.");
            System.out.println();

            String userId = ConsoleInputHelper.readNonBlankString(scanner, "Enter user ID: ");
            String name = ConsoleInputHelper.readNonBlankString(scanner, "Enter your name: ");

            User user = new User(userId, name);
            List<Symptom> symptoms = new ArrayList<>();

            int count = ConsoleInputHelper.readPositiveInt(scanner,
                    "How many symptoms would you like to enter? ");


            for (int i = 0; i < count; i++) {
                System.out.println();
                System.out.println("Symptom " + (i + 1));

                String symptomName = ConsoleInputHelper.readNonBlankString(scanner, "Name: ");
                int severity = ConsoleInputHelper.readSeverity(scanner);
                int durationDays = ConsoleInputHelper.readNonNegativeInt(scanner,
                        "Duration in days: ");
                symptoms.add(new Symptom(symptomName, severity, durationDays));
            }

            String reportId = "report-" + System.currentTimeMillis();
            SymptomReport report = new SymptomReport(reportId, LocalDateTime.now(),
                    user.getUserId(), symptoms);

            InputValidator.validateReport(report);

            List<ConditionPrediction> predictions = predictionEngine.predict(report);
            report.setPredictions(predictions);

            List<Recommendation> recommendations = recommendationEngine
                    .generateRecommendations(report, predictions);

            report.setRecommendations(recommendations);
            report.setHighRiskDetected(recommendationEngine
                    .containsUrgentRecommendation(recommendations));

            user.addReport(report);
            // save the completed report after predictions and recommendations have been added
            reportRepo.saveReport(report);
            printSummary(report);
            System.out.println();
            System.out.println("Report saved successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void printSummary(SymptomReport report) {
        System.out.println();
        System.out.println("===== Symptom Report Summary =====");
        System.out.println("Report ID: " + report.getReportId());
        System.out.println("User ID: " + report.getUserId());
        System.out.println("Created At: " + report.getCreatedAt());
        System.out.println("High Risk Detected: " + report.isHighRiskDetected());

        System.out.println();
        System.out.println("Predictions:");
        for (ConditionPrediction prediction : report.getPredictions()) {
            System.out.println("- " + prediction.getConditionName() + " | Confidence: "
                    + prediction.getConfidenceScore());
            System.out.println("  " + prediction.getExplanation());
        }

        System.out.println();
        System.out.println("Recommendations:");
        for (Recommendation recommendation : report.getRecommendations()) {
            System.out.println("- [" + recommendation.getUrgencyLevel() + "] "
                    + recommendation.getMessage());
        }
    }
}