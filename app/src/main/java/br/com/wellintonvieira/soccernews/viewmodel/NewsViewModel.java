package br.com.wellintonvieira.soccernews.viewmodel;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.wellintonvieira.soccernews.data.DataRepository;
import br.com.wellintonvieira.soccernews.model.News;
import br.com.wellintonvieira.soccernews.model.State;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> mNews = new MutableLiveData<>();
    private final MutableLiveData<State> state = new MutableLiveData<>();

    public NewsViewModel() {
        state.setValue(State.DOING);
        getNewsInApiClient();
    }

    public void getNewsInApiClient() {
        DataRepository.getInstance().getRetrofitApi().getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                if (response.isSuccessful()) {
                    mNews.setValue(response.body());
                    state.setValue(State.DONE);
                } else {
                    state.setValue(State.ERROR);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable error) {
                error.printStackTrace();
                state.setValue(State.ERROR);
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return this.mNews;
    }

    public LiveData<State> getState() {
        return this.state;
    }

    public void insertNews(News favorites) {
        AsyncTask.execute(() -> DataRepository.getInstance().getDatabaseApi().newsDAO().insert(favorites));
    }
}