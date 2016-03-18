package me.zaicheng.app.popularmovies.data.remote;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import java.io.IOException;

import me.zaicheng.app.popularmovies.BuildConfig;
import me.zaicheng.app.popularmovies.data.model.Movie;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by vmlinz on 3/18/16.
 */
public interface MovieService {
    String ENDPOINT = "http://api.themoviedb.org/3/";

    // get Popular movies
    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies();

    @GET("movie/popular")
    Observable<MoviesResponse> getPopularMoviesObservable();

    // get top rated movies
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies();

    @GET("movie/top_rated")
    Observable<MoviesResponse> getTopRatedMoviesObservable();

    // get movie details
    @GET("movie/{id}")
    Call<Movie> getMovieById(@Path("id") long id);

    @GET("movie/{id}")
    Observable<Movie> getMovieObservableById(@Path("id") long id);

    class Creator {
        public static MovieService newMovieService() {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            HttpUrl url = chain.request().url()
                                    .newBuilder()
                                    .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                                    .build();
                            Request request = chain.request().newBuilder().url(url).build();
                            return chain.proceed(request);
                        }
                    })
                    .build();
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .setExclusionStrategies(new ExclusionStrategy() {
                        @Override
                        public boolean shouldSkipField(FieldAttributes f) {
                            return f.getDeclaredClass().equals(ModelAdapter.class);
                        }

                        @Override
                        public boolean shouldSkipClass(Class<?> clazz) {
                            return false;
                        }
                    })
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(MovieService.class);
        }
    }
}
