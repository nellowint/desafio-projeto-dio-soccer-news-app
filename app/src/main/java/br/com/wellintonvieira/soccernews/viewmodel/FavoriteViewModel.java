package br.com.wellintonvieira.soccernews.viewmodel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.wellintonvieira.soccernews.data.DataRepository;
import br.com.wellintonvieira.soccernews.model.News;

public class FavoriteViewModel extends ViewModel {

    public LiveData<List<News>> getFavorites() {
        return DataRepository.getInstance().getDatabaseApi().newsDAO().getFavorites();
    }

    public void updateNews(News favorites) {
        AsyncTask.execute(() -> DataRepository.getInstance().getDatabaseApi().newsDAO().insert(favorites));
    }
}
