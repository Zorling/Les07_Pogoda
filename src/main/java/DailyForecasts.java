import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class DailyForecasts {

    @JsonProperty("Date")
    public String date;

    @JsonProperty("EpochDate")
    public Integer epochDate;

    @JsonProperty("Temperature")
    public Temperature temperature;

    @JsonProperty("Day")
    public Day day;

    @JsonProperty("Night")
    public Night night;

    //@JsonProperty("Sources")
    //public String sources;

    @JsonProperty("MobileLink")
    public String mobileLink;

    @JsonProperty("Link")
    public String link;

    @Override
    public  String toString() {
        return "Прогноз на " + date + "\\" + day.toString() + "\\" + temperature.minimum.toString() + "\r\n";
    }
}