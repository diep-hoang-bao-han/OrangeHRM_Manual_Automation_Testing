package due.giuaky221121514210.ui.Day3_Network;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import due.giuaky221121514210.databinding.FragmentDay3WeatherBinding;
import due.giuaky221121514210.ui.Day3_Network.adapter.HourAdapter;
import due.giuaky221121514210.ui.Day3_Network.network.APIWeather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherFragment extends Fragment {
    private FragmentDay3WeatherBinding binding;
    private RecyclerView rvHour;
    private TextView tvTem, tvStatus;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDay3WeatherBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tvTem = binding.tvTem;
        tvStatus = binding.tvStatus;

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false);
        rvHour = binding.rvHour;
        rvHour.setLayoutManager(layoutManager);

        fetchWeatherData();
        return root;
    }

    private void fetchWeatherData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIWeather.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIWeather service = retrofit.create(APIWeather.class);

        // Gọi API thời tiết hiện tại
        service.getWeatherData("Hanoi", "metric", APIWeather.API_KEY)
                .enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            WeatherResponse weather = response.body();
                            tvTem.setText(String.format("%.0f°C", weather.getMain().getTemp()));
                            if (weather.getWeather() != null && weather.getWeather().length > 0) {
                                tvStatus.setText(weather.getWeather()[0].getDescription());
                            }
                        } else {
                            showError("Không thể lấy dữ liệu thời tiết");
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {
                        showError("Lỗi kết nối: " + t.getMessage());
                    }
                });

        // Gọi API dự báo
        service.getForecastData("Hanoi", "metric", APIWeather.API_KEY)
                .enqueue(new Callback<ForecastResponse>() {
                    @Override
                    public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<ForecastResponse.ForecastItem> forecastList = response.body().getList();
                            if (forecastList != null && forecastList.size() >= 8) {
                                HourAdapter adapter = new HourAdapter(requireContext(), forecastList.subList(0, 8));
                                rvHour.setAdapter(adapter);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastResponse> call, Throwable t) {
                        showError("Không thể lấy dữ liệu dự báo");
                    }
                });
    }

    private void showError(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
    }
}