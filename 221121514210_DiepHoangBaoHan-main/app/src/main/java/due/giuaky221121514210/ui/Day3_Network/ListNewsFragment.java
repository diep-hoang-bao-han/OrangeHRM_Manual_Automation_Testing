package due.giuaky221121514210.ui.Day3_Network;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import due.giuaky221121514210.databinding.FragmentDay3ListNewsBinding;
import due.giuaky221121514210.ui.Day3_Network.adapter.NewsAdapter;
import due.giuaky221121514210.ui.Day3_Network.model.Item;
import due.giuaky221121514210.ui.Day3_Network.network.API250_movies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListNewsFragment extends Fragment {
    private FragmentDay3ListNewsBinding binding;
    private List<Item> listData = new ArrayList<>();
    private NewsAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDay3ListNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setupRecyclerView();
        getListData();

        return root;
    }

    private void setupRecyclerView() {
        adapter = new NewsAdapter(requireActivity(), listData);
        binding.rvListNews.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvListNews.setAdapter(adapter);
    }

    private void getListData() {
        // Sử dụng getListData() thay vì getPopularMovies() để tránh lỗi tham số
        API250_movies.apiService.getListData().enqueue(new Callback<API250_movies.MovieResponse>() {
            @Override
            public void onResponse(Call<API250_movies.MovieResponse> call,
                                   Response<API250_movies.MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listData.clear();
                    listData.addAll(response.body().getResults());
                    adapter.notifyDataSetChanged();
                } else {
                    showError("Failed to load data: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<API250_movies.MovieResponse> call, Throwable t) {
                showError("Error: " + t.getMessage());
                Log.e("ListNewsFragment", "API call failed", t);
            }
        });
    }

    private void showError(String message) {
        if (isAdded() && getContext() != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}