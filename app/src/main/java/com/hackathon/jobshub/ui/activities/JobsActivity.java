package com.hackathon.jobshub.ui.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.hackathon.jobshub.R;
import com.hackathon.jobshub.adapters.JobsAdapter;
import com.hackathon.jobshub.models.Job;
import com.hackathon.jobshub.ui.custom.EndlessRecyclerOnScrollListener;
import com.hackathon.jobshub.ui.custom.RecyclerItemClickListener;
import com.hackathon.jobshub.ui.fragments.FilterDialogFragment;
import com.hackathon.jobshub.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JobsActivity extends AppCompatActivity {

    private static final String TAG = JobsActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.jobs_list)
    RecyclerView recyclerView;
    @Bind(R.id.swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;

    JobsAdapter jobsAdapter;
    EndlessRecyclerOnScrollListener recyclerOnScrollListener;
    List<Job> jobList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
            }
        });

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(llm);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Job job = jobList.get(position);
                        //Intent intent = new Intent(JobsActivity.this, JobDetailsActivity.class);
                        //intent.putExtra(?, ?);
                        //startActivity(intent);
                    }
                }));

        recyclerOnScrollListener = new EndlessRecyclerOnScrollListener(llm) {
            @Override
            public void onLoadMore(int current_page) {
                LogUtils.d(TAG, "onLoadMore", current_page + "");

                //Preferences prefs = new Preferences(mContext);
                //int outletId = prefs.getIntValue(Constants.OUTLET_ID, -1);

                //loadMore(outletId, current_page, Constants.PAGE_SIZE);
                jobList.add(new Job());
                jobList.add(new Job());
                jobList.add(new Job());
                jobList.add(new Job());

                jobsAdapter.notifyDataSetChanged();
            }
        };

        recyclerView.addOnScrollListener(recyclerOnScrollListener);

        jobsAdapter = new JobsAdapter(jobList);
        recyclerView.setAdapter(jobsAdapter);

        load();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void refreshList() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                load();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 5000);
    }

    private void load() {

        recyclerOnScrollListener.reset();

        jobList.clear();

        jobList.add(new Job());
        jobList.add(new Job());
        jobList.add(new Job());
        jobList.add(new Job());
        jobList.add(new Job());
        jobList.add(new Job());
        jobList.add(new Job());
        jobList.add(new Job());
        jobList.add(new Job());
        jobList.add(new Job());

        //jobsAdapter = new JobsAdapter(jobList);
        //recyclerView.setAdapter(jobsAdapter);
        jobsAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.fab)
    public void onFabClick(View view) {
        FilterDialogFragment dialog = FilterDialogFragment.newInstance();
        dialog.show(getSupportFragmentManager(), FilterDialogFragment.class.getSimpleName());
    }
}
