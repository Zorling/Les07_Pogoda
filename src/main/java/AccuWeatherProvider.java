import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.*;
import java.util.List;
import java.util.Objects;

public class AccuWeatherProvider implements WeatherProvider {
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECAST = "forecasts";
    private static final String FORECAST_TYPE = "daily";
    private static final String FORECAST_PERIOD = "5day";
    private static final String CURRENT_CONDITIONS_ENDPOINT = "currentconditions";
    private static final String API_VERSION = "v1";
    private static final String API_KEY = ApplicationGlobalState.getInstance().getApiKey();

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void getWeather(Periods periods) throws IOException {
        String cityKey = detectCityKey();
        if (periods.equals(Periods.FIVE_DAYS)) {
            HttpUrl urlFiveDays = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment(FORECAST)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(FORECAST_TYPE)
                    .addPathSegment(FORECAST_PERIOD)
                    .addPathSegment(cityKey)
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("language", "ru-ru")
                    .addQueryParameter("metric", "true")
                    .build();
            Request requestFiveDays = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(urlFiveDays)
                    .build();
            Response responseFiveDays = client.newCall(requestFiveDays).execute();
            String response5 = Objects.requireNonNull(responseFiveDays.body()).string();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ResponseFiveDays respFiveDays = objectMapper.readValue(response5, new TypeReference<ResponseFiveDays>() {
            });
            System.out.println(respFiveDays);
        }
    }

    public String detectCityKey() throws IOException {
        String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();

        HttpUrl detectCity = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment("locations")
                .addPathSegment(API_VERSION)
                .addPathSegment("cities")
                .addPathSegment("autocomplete")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", selectedCity)
                .build();

        Request requestCity = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(detectCity)
                .build();

        Response responseCity = client.newCall(requestCity).execute();

        if (!responseCity.isSuccessful()) {
            throw new IOException("Невозможно прочесть информацию о городе. " +
                    "Код ответа сервера = " + responseCity.code() + " тело ответа = " + responseCity.body().string());
        }
        String jsonResponseCity = responseCity.body().string();
        System.out.println("Произвожу поиск города " + selectedCity);
//        System.out.println(jsonResponseCity);
        if (objectMapper.readTree(jsonResponseCity).size() > 0) {
            String cityName = objectMapper.readTree(jsonResponseCity).get(0).at("/LocalizedName").asText();
            String countryName = objectMapper.readTree(jsonResponseCity).get(0).at("/Country/LocalizedName").asText();
            System.out.println("Найден город " + cityName + " в стране " + countryName);
        } else throw new IOException("Server returns 0 cities");

        return objectMapper.readTree(jsonResponseCity).get(0).at("/Key").asText();
    }
}