package com.hackathon.jobshub.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hackathon.jobshub.R;
import com.hackathon.jobshub.models.Job;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Nguyen on 11/21/2015.
 */
public class JobsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Job> data;

    public JobsAdapter(List<Job> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemReview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job, parent, false);
        return new ViewHolder(itemReview);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Job o = data.get(position);
        ViewHolder holderJob = (ViewHolder) holder;
        holderJob.tvTitle.setText(o.title);
        holderJob.tvCompany.setText(o.company);
        holderJob.tvLocation.setText(o.location);
        holderJob.tvContent.setText(o.content);
        holderJob.tvSource.setText(o.source);
        holderJob.tvDate.setText(o.date);
    }

    @Override
    public int getItemCount() {
        return (data == null) ? 0 : data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvTitle)
        TextView tvTitle;
        @Bind(R.id.tvCompany)
        TextView tvCompany;
        @Bind(R.id.tvLocation)
        TextView tvLocation;
        @Bind(R.id.tvContent)
        TextView tvContent;
        @Bind(R.id.tvSource)
        TextView tvSource;
        @Bind(R.id.tvDate)
        TextView tvDate;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
