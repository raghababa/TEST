package com.example.myapplication

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.util.Log
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UploadJobService : JobService() {

    private val TAG = "TAG"

    override fun onStartJob(params: JobParameters?): Boolean = runBlocking {

        Log.d(TAG, "Job started")
        doBackgroundWork1(params)
        // Returning true means the job is still running asynchronously
        return@runBlocking true
    }


    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "Job canceled before completion")
        jobCanceled = true
        // Returning true means the job should be rescheduled if it didn't complete
        return true
    }

    private var jobCanceled : Boolean = false
    private suspend fun doBackgroundWork1(params: JobParameters?) = coroutineScope{
        launch {

                Log.d(TAG, "Logging a message!")

                if (jobCanceled) {
                    return@launch
                }

                delay(1000L)

                Log.d(TAG, "Job finish")
                jobFinished(params,false)
        }
    }

}


