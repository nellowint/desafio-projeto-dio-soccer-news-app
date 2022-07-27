package br.com.wellintonvieira.soccernews.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import br.com.wellintonvieira.soccernews.R;
import br.com.wellintonvieira.soccernews.data.DataRepository;
import br.com.wellintonvieira.soccernews.databinding.ActivityDetailNewsBinding;
import br.com.wellintonvieira.soccernews.model.News;

public class DetailNews extends AppCompatActivity {

    public static final String NEWS = "news";
    private ActivityDetailNewsBinding binding;
    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailNewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        binding.toolbarLayout.setTitle(getTitle());
        configureFloatingButton();
        news = getIntent().getParcelableExtra(NEWS);
        configureFields(news);
    }

    private void configureFields(News news) {
        Picasso.get().load(news.getImage()).fit().into(binding.imageViewDetail);
        binding.layoutContentScrolling.textViewDetailTitle.setText(news.getTitle());
        binding.layoutContentScrolling.textViewDetailBody.setText(news.getBody());
        setColorFloatingButton();
    }

    private void setColorFloatingButton() {
        if (news.getFavorite() == 0) {
            binding.floatingButtonFavorite.setColorFilter(getResources().getColor(R.color.text));
        } else {
            binding.floatingButtonFavorite.setColorFilter(getResources().getColor(R.color.primary_700));
        }
    }

    private void configureFloatingButton() {
        binding.floatingButtonFavorite.setOnClickListener(view -> {
            news.setFavorite();
            setColorFloatingButton();
            AsyncTask.execute(() -> DataRepository.getInstance().getDatabaseApi().newsDAO().insert(news));
        });
    }
}