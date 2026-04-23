package symptom_support;


/**
 * recommendation : stores guidance such as self-care advice or escalation steps.
 * **/
public class Recommendation
{
    private final String message;
    private final RecommendationType type;
    private final UrgencyLevel urgencyLevel;

    public Recommendation(String message, RecommendationType type,
                          UrgencyLevel urgencyLevel)
    {
        if (message == null || message.isEmpty()) throw new IllegalArgumentException(
                "Message cannot be null or empty.");
        if (type == null) throw new IllegalArgumentException("Recommendation type cannot be null");
        if (urgencyLevel == null) throw new IllegalArgumentException("Urgency level type cannot be null");

        this.message = message;
        this.type = type;
        this.urgencyLevel = urgencyLevel;
    }

    public String getMessage() { return message; }
    public RecommendationType getType() { return type; }
    public UrgencyLevel getUrgencyLevel() { return urgencyLevel; }
    public boolean isUrgent() { return urgencyLevel == UrgencyLevel.URGENT; }
}
