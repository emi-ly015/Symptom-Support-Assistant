package symptom_support;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * SymptomReport : represents one full user session and everything submitted into one report.
 *
 *
 * **/
public class SymptomReport
{
    private final String reportId;
    private final LocalDateTime createdAt;
    private final String userId;
    private  final List<Symptom> symptoms;

    private boolean highRiskDetected;
    private List<ConditionPrediction> predictions;
    private List<Recommendation> recommendations;

    public SymptomReport(String reportId, LocalDateTime createdAt, String userId,
                         List<Symptom> symptoms)
    {

        if (reportId == null || reportId.isEmpty()) throw new IllegalArgumentException(
                "Report ID cannot be null or empty.");
        if (createdAt == null) throw new IllegalArgumentException("Created time cannot be null.");
        if (userId == null || userId.isEmpty()) throw new IllegalArgumentException(
                "User ID cannot be null or empty.");
        if (symptoms == null) throw new IllegalArgumentException("Symptoms cannot be null.");
        if (symptoms.isEmpty()) throw new IllegalArgumentException("Symptoms cannot be empty.");

        this.reportId = reportId;
        this.createdAt = createdAt;
        this.userId = userId;
        this.symptoms = new ArrayList<>(symptoms);
        this.highRiskDetected = false;
        this.predictions = new ArrayList<>();
        this.recommendations = new ArrayList<>();

    }

    public String getReportId() { return reportId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getUserId() { return userId; }
    public List<Symptom> getSymptoms() { return new ArrayList<>(symptoms); }
    public List<ConditionPrediction> getPredictions() { return new ArrayList<>(predictions); }
    public List<Recommendation> getRecommendations() { return new ArrayList<>(recommendations); }
    public boolean isHighRiskDetected() { return highRiskDetected; }

    public void setHighRiskDetected(boolean highRiskDetected) { this.highRiskDetected = highRiskDetected; }

    public void setPredictions(List<ConditionPrediction> predictions)
    {
        if (predictions == null) throw new IllegalArgumentException("Predictions list cannot be null");
        this.predictions = new ArrayList<>(predictions);
    }
    public void setRecommendations(List<Recommendation> recommendations)
    {
        if (recommendations == null) throw new IllegalArgumentException("Recommendations list cannot be null");
        this.recommendations = new ArrayList<>(recommendations);
    }
    public int getSymptomCount() { return symptoms.size(); }


}
