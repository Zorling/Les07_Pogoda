import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Headline {

    @JsonProperty("EffectiveDate")
    public String effectiveDate;

    @JsonProperty("EffectiveEpochDate")
    public String effectiveEpochDate;

    @JsonProperty("Severity")
    public Integer severity;

    @JsonProperty("Text")
    public String text;

    @JsonProperty("Category")
    public String category;

    @JsonProperty("EndDate")
    public String endDate;

    @JsonProperty("EndEpochDate")
    public Integer endEpochDate;

    @JsonProperty("MobileLink")
    public String mobileLink;

    @JsonProperty("Link")
    public String link;
}