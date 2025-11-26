package due.giuaky221121514210.ui.Day3_Network.network;

import due.giuaky221121514210.ui.Day3_Network.ForecastResponse;
import due.giuaky221121514210.ui.Day3_Network.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIWeather {
    String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    String API_KEY = "b58faeace9e3ae5a41fb7ade60f88e19";

    @GET("weather")
    Call<WeatherResponse> getWeatherData(
            @Query("q") String city,
            @Query("units") String units,
            @Query("appid") String appId
    );

    @GET("forecast")
    Call<ForecastResponse> getForecastData(
            @Query("q") String city,
            @Query("units") String units,
            @Query("appid") String appId
    );
}