import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ResponseFiveDays {

    @JsonProperty("Headline")
    public Headline headline;

    @JsonProperty("DailyForecasts")
    public List<DailyForecasts> dailyForecasts = null;

    @Override
    public String toString() {return dailyForecasts.toString() + "\r\n";}

}