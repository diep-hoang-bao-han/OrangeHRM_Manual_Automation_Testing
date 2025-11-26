package due.giuaky221121514210.ui.Day3_Network.network;

import java.util.List;

import due.giuaky221121514210.ui.Day3_Network.model.Item;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API250_movies {
    // Các constant cơ bản
    String SERVER_URL = "https://api.themoviedb.org/3/";
    String API_KEY = "132478db40fc490ccb59c6712fae7e3f";
    String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    String DEFAULT_LANGUAGE = "en-US";
    int DEFAULT_PAGE = 1;

    // Retrofit instance
    API250_movies apiService = new Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API250_movies.class);

    /**
     * Lấy danh sách phim phổ biến
     */
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page,
            @Query("region") String region
    );

    /**
     * Phương thức mặc định cho BasicFragment
     */
    default Call<MovieResponse> getListData() {
        return getPopularMovies(API_KEY, DEFAULT_LANGUAGE, DEFAULT_PAGE, null);
    }

    /**
     * Model response cho API movie/popular
     */
    class MovieResponse {
        private int page;
        private List<Item> results; // Đổi từ MovieItem sang Item
        private int total_pages;
        private int total_results;

        public List<Item> getResults() {
            return results;
        }

        public int getPage() {
            return page;
        }

        public int getTotalPages() {
            return total_pages;
        }

        public int getTotalResults() {
            return total_results;
        }
    }

    /**
     * Hàm tiện ích để build URL ảnh
     */
    static String getImageUrl(String path, String size) {
        if (path == null || path.isEmpty()) {
            return ""; // Trả về chuỗi rỗng nếu path không hợp lệ
        }
        return IMAGE_BASE_URL.replace("w500", size) + path;
    }
}