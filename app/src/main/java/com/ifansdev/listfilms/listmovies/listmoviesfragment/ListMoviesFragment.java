package com.ifansdev.listfilms.listmovies.listmoviesfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifansdev.listfilms.R;
import com.ifansdev.listfilms.api.dataclass.Movie;
import com.ifansdev.listfilms.moviedescription.MovieDescriptionActivity;

import java.util.ArrayList;

public class ListMoviesFragment extends Fragment {

    private ListMoviesAdapter listMoviesAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Movie> movies;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_recycler_view, container, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        Bundle bundle = getArguments();
        movies = new ArrayList<>();

        if (bundle != null) {
            movies = (ArrayList<Movie>) bundle.getSerializable("movies");
        }

        listMoviesAdapter = new ListMoviesAdapter(movies);

        return recyclerView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setAdapter(listMoviesAdapter);
        listMoviesAdapter.SetOnItemClickListener((v, position) -> {

            Intent intent = new Intent(v.getContext(), MovieDescriptionActivity.class);
            intent.putExtra("movies", movies.get(position));
            v.getContext().startActivity(intent);
        });
    }
}
