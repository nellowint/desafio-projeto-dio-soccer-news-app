package br.com.wellintonvieira.soccernews.data;

import androidx.room.Room;

import br.com.wellintonvieira.soccernews.AppContext;
import br.com.wellintonvieira.soccernews.data.local.DatabaseApi;
import br.com.wellintonvieira.soccernews.data.remote.RetrofitApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRepository {

    private static final String REMOTE_URL = "https://wellintonvieira.github.io/desafio-projeto-dio-soccer-news-api/";
    private static final String DATABASE_NAME = "soccer-news.db";

    private RetrofitApi retrofitApi;
    private DatabaseApi databaseApi;

    public DataRepository() {
        retrofitApi = new Retrofit.Builder()
                .baseUrl(REMOTE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitApi.class);

        databaseApi = Room.databaseBuilder(AppContext.getInstance(), DatabaseApi.class, DATABASE_NAME).build();
    }

    public RetrofitApi getRetrofitApi() {
        return retrofitApi;
    }

    public DatabaseApi getDatabaseApi() {
        return databaseApi;
    }

    public static DataRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final DataRepository INSTANCE = new DataRepository();
    }
}