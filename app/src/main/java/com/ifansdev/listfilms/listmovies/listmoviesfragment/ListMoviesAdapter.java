package com.ifansdev.listfilms.listmovies.listmoviesfragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifansdev.listfilms.R;
import com.ifansdev.listfilms.api.dataclass.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListMoviesAdapter extends RecyclerView.Adapter<ListMoviesAdapter.ListMoviesHolder> {

    private OnItemClickListener itemClickListener;

    private ArrayList<Movie> movies;

    public ListMoviesAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public class ListMoviesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private ImageView poster;

        public ListMoviesHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.text);
            this.poster = itemView.findViewById(R.id.poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ListMoviesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_list_item, viewGroup, false);
        return new ListMoviesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMoviesHolder listMoviesHolder, int i) {
        listMoviesHolder.title.setText(movies.get(i).getTitle());

        String path = "https://image.tmdb.org/t/p/w185_and_h278_bestv2/" + movies.get(i).getPoster();

        Picasso.get().load(path)
                .fit()
                .into(listMoviesHolder.poster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
