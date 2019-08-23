package com.ifansdev.listfilms.listmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ifansdev.listfilms.commonfragments.CreateFragment;
import com.ifansdev.listfilms.R;
import com.ifansdev.listfilms.api.SearchRepository;
import com.ifansdev.listfilms.api.SearchRepositoryProvider;
import com.ifansdev.listfilms.api.dataclass.ListMovies;
import com.ifansdev.listfilms.commonfragments.ErrorsFragment;
import com.ifansdev.listfilms.commonfragments.LoadingFragment;
import com.ifansdev.listfilms.listmovies.listmoviesfragment.ListMoviesFragment;

import java.io.Serializable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ListMoviesActivity extends AppCompatActivity implements ErrorsFragment.HandlerRefresher {

    private SearchRepository searchRepository = SearchRepositoryProvider.INSTANCE.provideSearchRepository();

    private CreateFragment createFragment = CreateFragment.INSTANCE;

    private final String API_KEY = "befc7872520fd736c58948abb2f4a53c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movies);
        
        loadingListMovies();
    }

    private void loadingListMovies() {
        createFragment.createSimpleFragment(this, R.id.list_movies_activity_container, new LoadingFragment());

        searchRepository.searchListFilms(API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        this::createListViewForSourceOfQuotes,
                        this::handleError);
    }

    public void handleError(Throwable error) {
        createFragment.createErrorFragment(this, R.id.list_movies_activity_container, error);
    }

    private void createListViewForSourceOfQuotes(ListMovies listMovies) {
        ListMoviesFragment listMoviesFragment = new ListMoviesFragment();

        Bundle bundle = new Bundle();

        bundle.putSerializable("movies", (Serializable) listMovies.getResults());
        listMoviesFragment.setArguments(bundle);

        createFragment.createSimpleFragment(this, R.id.list_movies_activity_container, listMoviesFragment);
    }

    @Override
    public void refresh() {
        loadingListMovies();
    }
}
