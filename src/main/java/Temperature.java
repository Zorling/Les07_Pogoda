import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Temperature {

    @JsonProperty("Minimum")
    public Minimum minimum;

    @JsonProperty("Maximum")
    public Maximum maximum;

    @Override
    public String toString() {
        return "\\" + minimum;
    }

}