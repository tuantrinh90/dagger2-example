package com.codepath.daggerexample;

import com.codepath.daggerexample.models.Movie;
import com.codepath.daggerexample.models.MovieRespone;
import com.codepath.daggerexample.network.interfaces.GitHubApiInterface;
import com.codepath.daggerexample.utils.SimpleDividerItemDecoration;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CustomAdapter mCustomAdapter;
    private List<Movie> mRepositoryList;

    @Inject
    GitHubApiInterface mGitHubApiInterface;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
        ((MyApp) getApplication()).getGitHubComponent().inject(this);
        initViews();
    }

    private void initViews() {
        mRepositoryList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recycle);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        loadData();
    }

    private void loadData() {
        Call<MovieRespone> call = mGitHubApiInterface.getTopRatedMovies("7e8f60e325cd06e164799af1e317d7a7");
        call.enqueue(new Callback<MovieRespone>() {
            @Override
            public void onResponse(Response<MovieRespone> response, Retrofit retrofit) {
                mRepositoryList.addAll(response.body().getResults());
                mCustomAdapter = new CustomAdapter(mRepositoryList);
                mRecyclerView.setAdapter(mCustomAdapter);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
