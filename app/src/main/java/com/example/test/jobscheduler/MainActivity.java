package com.example.test.jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";

    static final int JOB_SCHEDULE_ID = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void scheduleJob(View v){
        ComponentName componentName = new ComponentName(this,ServiceEnvoi.class);
        JobInfo info = new JobInfo.Builder(JOB_SCHEDULE_ID, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                //reste meme apres un reboot du phone
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if ( resultCode==JobScheduler.RESULT_SUCCESS){
            Log.d(TAG,"Job scheduled");
        }else{
            Log.d(TAG,"Job scheduling failed");
        }
    }

    public void cancelJob(View v){

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(JOB_SCHEDULE_ID);
        Log.d(TAG, "Job Cancelled");

    }

}
