package com.hackathon.jobshub.ui.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hackathon.jobshub.R;
import com.hackathon.jobshub.adapters.JobsAdapter;
import com.hackathon.jobshub.models.Job;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JobsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.jobs_list)
    RecyclerView jobsList;

    JobsAdapter jobsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        jobsList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setSmoothScrollbarEnabled(true);
        jobsList.setLayoutManager(llm);

        List<Job> jobList = new ArrayList<>();
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

        jobsAdapter = new JobsAdapter(jobList);
        jobsList.setAdapter(jobsAdapter);
    }

    @OnClick(R.id.fab)
    public void onFabClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
