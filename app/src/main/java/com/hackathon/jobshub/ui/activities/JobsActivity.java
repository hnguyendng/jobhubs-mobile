package com.hackathon.jobshub.ui.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.hackathon.jobshub.Constants;
import com.hackathon.jobshub.R;
import com.hackathon.jobshub.adapters.JobsAdapter;
import com.hackathon.jobshub.apis.IResponse;
import com.hackathon.jobshub.apis.JobsHubClient;
import com.hackathon.jobshub.models.Job;
import com.hackathon.jobshub.models.SearchResponse;
import com.hackathon.jobshub.ui.custom.EndlessRecyclerOnScrollListener;
import com.hackathon.jobshub.ui.custom.LinearLayoutManagerWrapper;
import com.hackathon.jobshub.ui.fragments.FilterDialogFragment;
import com.hackathon.jobshub.utils.LogUtils;
import com.pixplicity.easyprefs.library.Prefs;

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
    private boolean lastPage;

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

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        LinearLayoutManagerWrapper llm = new LinearLayoutManagerWrapper(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(llm);

  /*      recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Job job = jobList.get(position);
                        //Intent intent = new Intent(JobsActivity.this, JobDetailsActivity.class);
                        //intent.putExtra(?, ?);
                        //startActivity(intent);
                    }
                }));*/

        recyclerOnScrollListener = new EndlessRecyclerOnScrollListener(llm) {
            @Override
            public void onLoadMore(int next_page) {
                LogUtils.d(TAG, "onLoadMore -> next page", next_page + "");

                if (lastPage == true) {
                    return;
                }

                //if (currentPage != next_page) {
                load(currentPage + 1);
                //}
            }
        };

        recyclerView.addOnScrollListener(recyclerOnScrollListener);

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
        jobsAdapter = new JobsAdapter(jobList);
        recyclerView.setAdapter(jobsAdapter);
        jobList.clear();
        load(null);
    }

    int currentPage = 1;

    private void load(final Integer page) {
        String searchTerm = Prefs.getString(Constants.SEARCH_TERM, null);
        String city = Prefs.getString(Constants.CITY, null);

        JobsHubClient.getJobs(this, searchTerm, city, null, page, null, new IResponse<SearchResponse>() {
            @Override
            public void onResponse(SearchResponse response) {
                currentPage = page == null ? 1 : page;
                if (response != null) {
                    lastPage = response.last;
                    if (response.content != null && response.content.size() != 0)
                        try {
                            jobList.addAll(response.content);
                            jobsAdapter.notifyDataSetChanged();
                        } catch (Exception ex) {
                            LogUtils.e(TAG, "load", ex);
                        }
                }
            }

            @Override
            public void onFailure() {
            }
        });
    }

    @OnClick(R.id.fab)
    public void onFabClick(View view) {
        FilterDialogFragment dialog = FilterDialogFragment.newInstance();
        dialog.show(getSupportFragmentManager(), FilterDialogFragment.class.getSimpleName());
    }
}
