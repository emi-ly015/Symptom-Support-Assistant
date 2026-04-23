package symptom_support;


/**
 * add desc
 * **/
public class Symptom
{
    private final String name;
    // severity is rated on a 1 - 10 scale
    private final int severity;
    // must be 0 or more
    private final int durationDays;

    public Symptom(String name, int severity, int durationDays)
    {
        if (name == null || name.isEmpty() ) throw new IllegalArgumentException(
                "Symptom name cannot be empty or null.");
        String normalizedName = normalize(name);

        if (severity < 1 || severity > 10) throw new IllegalArgumentException(
                "Severity must be within 1 and 10.");
        if (durationDays < 0 ) throw new IllegalArgumentException(
                "Duration cannot be negative. ");

        this.name = normalizedName;
        this.severity = severity;
        this.durationDays = durationDays;
    }

    public String getName() { return name; }
    public int getSeverity() { return severity; }
    public int getDurationDays() { return durationDays; }
    public boolean isSevere() { return severity >= 8; }

    private static String normalize(String s) {
        return s.trim().toLowerCase().replaceAll("\\s+", "-");
    }
}
