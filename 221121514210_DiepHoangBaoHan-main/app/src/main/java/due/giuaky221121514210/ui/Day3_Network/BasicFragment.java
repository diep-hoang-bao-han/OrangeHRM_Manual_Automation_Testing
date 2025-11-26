package due.giuaky221121514210.ui.Day3_Network;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

import due.giuaky221121514210.R;
import due.giuaky221121514210.databinding.FragmentDay3BasicBinding;
import due.giuaky221121514210.ui.Day3_Network.model.Item;
import due.giuaky221121514210.ui.Day3_Network.network.API250_movies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasicFragment extends Fragment {
    private FragmentDay3BasicBinding binding;
    private TextView tvDate, tvTitle, tvContent;
    private ImageView ivCover;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BasicFragment", "onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDay3BasicBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Log.d("BasicFragment", "onCreateView");

        initView();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("BasicFragment", "onViewCreated");
        getData();
    }

    private void initView() {
        tvTitle = binding.tvTitle;
        tvContent = binding.tvContent;
        tvDate = binding.tvDate;
        ivCover = binding.ivCover;

        // Set giá trị mặc định
        tvTitle.setText("Loading movies...");
        tvContent.setText("Please wait while we load the latest movies");
        tvDate.setText("");
        ivCover.setImageResource(R.drawable.sondong);
    }

    private void getData() {
        Log.d("BasicFragment", "Starting API call");

        if (!isAdded() || getContext() == null) {
            Log.e("BasicFragment", "Fragment not attached to context");
            return;
        }

        API250_movies.apiService.getListData().enqueue(new Callback<API250_movies.MovieResponse>() {
            @Override
            public void onResponse(Call<API250_movies.MovieResponse> call,
                                   Response<API250_movies.MovieResponse> response) {
                if (!isAdded()) {
                    Log.w("BasicFragment", "Fragment not attached when response received");
                    return;
                }

                if (!response.isSuccessful()) {
                    String errorMsg = "API error: " + response.code();
                    Log.e("BasicFragment", errorMsg);
                    showError(errorMsg);
                    showDefaultData();
                    return;
                }

                if (response.body() == null) {
                    Log.e("BasicFragment", "Response body is null");
                    showError("No data received");
                    showDefaultData();
                    return;
                }

                List<Item> movies = response.body().getResults();
                if (movies == null || movies.isEmpty()) {
                    Log.e("BasicFragment", "Empty results list");
                    showError("No movies found");
                    showDefaultData();
                    return;
                }

                try {
                    updateUI(movies.get(0)); // Hiển thị phim đầu tiên
                } catch (Exception e) {
                    Log.e("BasicFragment", "Error displaying data", e);
                    showError("Error displaying movie");
                    showDefaultData();
                }
            }

            @Override
            public void onFailure(Call<API250_movies.MovieResponse> call, Throwable t) {
                if (!isAdded()) return;

                String errorMsg = "Network error: " + t.getMessage();
                Log.e("BasicFragment", errorMsg, t);
                showError(errorMsg);
                showDefaultData();
            }
        });
    }

    private void updateUI(Item movie) {
        if (!isAdded()) return;

        requireActivity().runOnUiThread(() -> {
            try {
                // Set thông tin phim
                tvTitle.setText(movie.getTitle() != null ? movie.getTitle() : "No title available");
                tvDate.setText(movie.getDate() != null ? movie.getDate() : "Unknown release date");
                tvContent.setText(movie.getDescription() != null ?
                        movie.getDescription() : "No description available");

                // Load ảnh
                if (movie.getImage() != null && !movie.getImage().isEmpty()) {
                    String imageUrl = API250_movies.IMAGE_BASE_URL + movie.getImage();
                    Glide.with(BasicFragment.this)
                            .load(imageUrl)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .placeholder(R.drawable.error)
                            .error(R.drawable.error)
                            .into(ivCover);
                } else {
                    ivCover.setImageResource(R.drawable.error);
                }
            } catch (Exception e) {
                Log.e("BasicFragment", "UI update error", e);
                showDefaultData();
            }
        });
    }

    private void showDefaultData() {
        if (!isAdded()) return;

        requireActivity().runOnUiThread(() -> {
            tvTitle.setText("Popular Movies");
            tvContent.setText("Could not load movie details. Please try again later.");
            tvDate.setText("");
            ivCover.setImageResource(R.drawable.error);
        });
    }

    private void showError(String message) {
        if (isAdded() && getContext() != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        Log.d("BasicFragment", "onDestroyView");
    }
}