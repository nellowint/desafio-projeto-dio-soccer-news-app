package br.com.wellintonvieira.soccernews.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import br.com.wellintonvieira.soccernews.model.News;

@Dao
public interface NewsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(News news);

    @Query("SELECT * FROM news WHERE favorite = 1")
    LiveData<List<News>> getFavorites();
}
