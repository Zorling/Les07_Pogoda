import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Night {

    @JsonProperty("Icon")
    public int icon;

    @JsonProperty("IconPhrase")
    public String iconPhrase;

    @JsonProperty("HasPrecipitation")
    public boolean hasPrecipitation;

    @JsonProperty("PrecipitationType")
    public String precipitationType;

    @JsonProperty("PrecipitationIntensity")
    public String precipitationIntensity;
}