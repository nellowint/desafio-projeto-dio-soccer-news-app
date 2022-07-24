package br.com.wellintonvieira.soccernews.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.wellintonvieira.soccernews.databinding.CardViewItemsBinding;
import br.com.wellintonvieira.soccernews.model.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<News> news;

    public NewsAdapter(List<News> news) {
        this.news = news;
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
        holder.binding.textViewTitle.setText(news.getTitle());
        holder.binding.textViewBody.setText(news.getBody());
        Picasso.get().load(news.getImage()).into(holder.binding.imageView);
        holder.binding.buttonOpenLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(news.getLink()));
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.binding.imageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO ...
            }
        });
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
}
