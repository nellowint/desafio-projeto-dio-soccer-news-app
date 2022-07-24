package br.com.wellintonvieira.soccernews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.wellintonvieira.soccernews.model.News;
import br.com.wellintonvieira.soccernews.retrofit.RetrofitApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final RetrofitApi retrofitApi;

    public NewsViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wellintonvieira.github.io/desafio-projeto-dio-soccer-news-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitApi = retrofit.create(RetrofitApi.class);
        getNewsInApiClient();
    }

    public void getNewsInApiClient() {
        retrofitApi.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    news.setValue(response.body());
                } else {
                    //TODO ...
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                //TODO ...
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }
}