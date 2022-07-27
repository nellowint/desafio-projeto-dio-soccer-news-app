package br.com.wellintonvieira.soccernews.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.wellintonvieira.soccernews.R;
import br.com.wellintonvieira.soccernews.databinding.CardViewItemsBinding;
import br.com.wellintonvieira.soccernews.model.News;
import br.com.wellintonvieira.soccernews.ui.activity.DetailNews;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<News> news;
    private final FavoriteListener favorites;

    public NewsAdapter(List<News> news, FavoriteListener favorites) {
        this.news = news;
        this.favorites = favorites;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardViewItemsBinding binding = CardViewItemsBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = this.news.get(position);
        Context context = holder.itemView.getContext();
        holder.binding.textViewTitle.setText(news.getTitle());
        holder.binding.textViewBody.setText(news.getBody());
        Picasso.get().load(news.getImage()).into(holder.binding.imageView);

        holder.binding.cardViewNews.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailNews.class);
            intent.putExtra(DetailNews.NEWS, news);
            holder.itemView.getContext().startActivity(intent);
        });

        holder.binding.buttonOpenLink.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(news.getLink()));
            context.startActivity(intent);
        });

        holder.binding.imageViewShare.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String message = news.getTitle() + "\n\n" + news.getBody() + "\n\n" + news.getLink();
            intent.putExtra(Intent.EXTRA_SUBJECT, news.getTitle());
            intent.putExtra(Intent.EXTRA_TEXT, message);
            context.startActivity(Intent.createChooser(intent, "Share"));
        });

        holder.binding.imageViewFavorite.setOnClickListener(view -> {
            news.setFavorite();
            this.favorites.onFavorite(news);
            notifyItemChanged(position);
        });

        if (news.getFavorite() == 0) {
            holder.binding.imageViewFavorite.setColorFilter(context.getResources().getColor(R.color.text));
        } else {
            holder.binding.imageViewFavorite.setColorFilter(context.getResources().getColor(R.color.primary_700));
        }
    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final CardViewItemsBinding binding;

        public ViewHolder(@NonNull CardViewItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface FavoriteListener {
        void onFavorite(News news);
    }
}
