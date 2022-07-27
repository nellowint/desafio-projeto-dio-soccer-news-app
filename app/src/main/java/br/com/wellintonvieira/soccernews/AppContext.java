package br.com.wellintonvieira.soccernews;

import android.app.Application;

public class AppContext extends Application {

    private static AppContext instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static AppContext getInstance() {
        return instance;
    }
}
