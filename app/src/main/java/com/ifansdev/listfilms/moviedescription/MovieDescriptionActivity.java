package com.ifansdev.listfilms.moviedescription;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifansdev.listfilms.R;
import com.ifansdev.listfilms.api.dataclass.Movie;
import com.squareup.picasso.Picasso;

public class MovieDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);

        Movie movie = getIntent().getParcelableExtra("movies");

        TextView title = findViewById(R.id.text);
        ImageView poster = findViewById(R.id.poster);
        TextView overview = findViewById(R.id.overview);

        title.setText(movie.getTitle());

        String path = "https://image.tmdb.org/t/p/w185_and_h278_bestv2/" + movie.getPoster();
        Picasso.get().load(path).into(poster);

        overview.setText(movie.getOverview());
    }
}
