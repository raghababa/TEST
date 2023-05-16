package com.example.myapplication

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Context.JOB_SCHEDULER_SERVICE
import android.util.Log


class UploadFilePeriodic {

    companion object {
        private val TAG = "TAG"
        private const val JOB_ID = 123

        fun scheduleJob(context: Context) {

            val jobInfoBuilder =
                JobInfo.Builder(JOB_ID, ComponentName(context, UploadJobService::class.java))
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY) // Set network requirements if needed

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                jobInfoBuilder.setRequiresBatteryNotLow(true) // Add additional conditions for API 26 and above
            }

            val jobInfo = jobInfoBuilder
                .setPeriodic(15 * 60 * 1000) // Run every 15 minutes
                .setPersisted(true) // Allow the job to survive device reboots// Allow the job to survive device reboots
                .build()

            val jobScheduler = context.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            val result = jobScheduler.schedule(jobInfo)
            if (result == JobScheduler.RESULT_SUCCESS) {
                Log.d(TAG, "scheduleJob: Job scheduled successfully")
            }
        }

        fun cancelJob(context: Context) {
            val jobScheduler = context.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.cancel(JOB_ID)
            Log.d(TAG, "Job CANCELED")
        }

    }

}