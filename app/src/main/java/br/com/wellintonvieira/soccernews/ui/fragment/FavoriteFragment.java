package br.com.wellintonvieira.soccernews.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import br.com.wellintonvieira.soccernews.adapter.NewsAdapter;
import br.com.wellintonvieira.soccernews.databinding.FragmentFavoritesBinding;
import br.com.wellintonvieira.soccernews.viewmodel.FavoriteViewModel;

public class FavoriteFragment extends Fragment {

    private FragmentFavoritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FavoriteViewModel favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        binding.recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapter(favoriteViewModel);
        return binding.getRoot();
    }

    private void setAdapter(FavoriteViewModel favoriteViewModel) {
        favoriteViewModel.getFavorites().observe(getViewLifecycleOwner(), news -> {
            NewsAdapter adapter = new NewsAdapter(news, favoriteViewModel::updateNews);
            binding.recyclerViewFavorites.setAdapter(adapter);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}