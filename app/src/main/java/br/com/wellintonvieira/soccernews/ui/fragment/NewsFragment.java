package br.com.wellintonvieira.soccernews.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import br.com.wellintonvieira.soccernews.R;
import br.com.wellintonvieira.soccernews.adapter.NewsAdapter;
import br.com.wellintonvieira.soccernews.databinding.FragmentNewsBinding;
import br.com.wellintonvieira.soccernews.viewmodel.NewsViewModel;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private NewsViewModel newsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        binding.recyclerViewNews.setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapter();
        setSwipeRefreshLayout();
        return binding.getRoot();
    }

    private void setAdapter() {
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
            NewsAdapter adapter = new NewsAdapter(news, newsViewModel::insertNews);
            binding.recyclerViewNews.setAdapter(adapter);
        });
    }

    private void setSwipeRefreshLayout() {
        newsViewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state) {
                case DOING:
                    binding.swipeRefreshLayout.setRefreshing(true);
                    break;
                case DONE:
                    binding.swipeRefreshLayout.setRefreshing(false);
                    break;
                case ERROR:
                    binding.swipeRefreshLayout.setRefreshing(false);
                    Snackbar.make(binding.getRoot(), R.string.text_view_network_error, Snackbar.LENGTH_LONG).show();
                    break;
            }
        });

        binding.swipeRefreshLayout.setOnRefreshListener(newsViewModel::getNewsInApiClient);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}