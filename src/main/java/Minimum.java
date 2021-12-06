import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Minimum {

    @JsonProperty("Value")
    public double value;

    @JsonProperty("Unit")
    public String unit;

    @JsonProperty("UnitType")
    public String unitType;

    @Override
    public  String toString() {
        return " Минимальная температура: " + value;
    }

}