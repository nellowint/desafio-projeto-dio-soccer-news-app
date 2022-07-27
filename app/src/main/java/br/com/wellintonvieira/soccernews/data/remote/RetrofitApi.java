package br.com.wellintonvieira.soccernews.data.remote;

import java.util.List;

import br.com.wellintonvieira.soccernews.model.News;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {

    @GET("soccer-news.json")
    Call<List<News>> getNews();
}
