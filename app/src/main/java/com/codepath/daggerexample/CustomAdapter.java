package com.codepath.daggerexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.daggerexample.models.Movie;

import java.util.List;

/**
 * Created by tuantx on 4/3/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomHolder> {

    public CustomAdapter(List<Movie> repositories) {
        mRepositories = repositories;
    }

    private List<Movie> mRepositories;

    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle, parent, false);
        return new CustomHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomHolder holder, int position) {
        holder.mTxtTitle.setText(mRepositories.get(position).getTitle());
        holder.mTxtOrgin.setText(mRepositories.get(position).getOriginalTitle());
        holder.mTxOverview.setText(mRepositories.get(position).getOverview());
    }

    @Override
    public int getItemCount() {
        return mRepositories.size();
    }

    public class CustomHolder extends RecyclerView.ViewHolder {
        TextView mTxtTitle, mTxtOrgin, mTxOverview;

        public CustomHolder(View itemView) {
            super(itemView);

            mTxtTitle = itemView.findViewById(R.id.title);
            mTxtOrgin = itemView.findViewById(R.id.original_title);
            mTxOverview = itemView.findViewById(R.id.overview);

        }
    }
}
