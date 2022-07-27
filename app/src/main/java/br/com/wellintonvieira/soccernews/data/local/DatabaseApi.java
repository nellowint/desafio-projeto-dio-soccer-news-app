package br.com.wellintonvieira.soccernews.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import br.com.wellintonvieira.soccernews.model.News;

@Database(entities = News.class, version = 1)
public abstract class DatabaseApi extends RoomDatabase {
    public abstract NewsDAO newsDAO();
}
