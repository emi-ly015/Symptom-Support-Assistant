package symptom_support;

import java.util.ArrayList;
import java.util.List;

// User stores user information and history of symptom reports
public class User
{
    private final String userId;
    private final String name;
    private final List<SymptomReport> reportHistory;

    public User(String userId, String name)
    {
        if (userId == null || userId.trim().isEmpty()) throw new
                IllegalArgumentException("User ID cannot be null or empty.");
        if (name == null || name.trim().isEmpty()) throw new
                IllegalArgumentException("Name cannot be null or empty.");

        this.userId = userId;
        this.name = name;
        this.reportHistory = new ArrayList<>();
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }

    public void addReport(SymptomReport report)
    {
        if (report == null) throw new IllegalArgumentException("Report cannot be null.");
        reportHistory.add(report);
    }
    public List<SymptomReport> getReportHistory() { return new ArrayList<>(reportHistory); }
    public int getReportCount() { return reportHistory.size(); }

}
