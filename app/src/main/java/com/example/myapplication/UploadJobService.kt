package com.example.myapplication

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class UploadJobService : JobService() {

    private val TAG = "TAG"
    private lateinit var job: Job

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "@@@@@@@Job started")
        doBackgroundWork(params)
        // Returning true means the job is still running asynchronously
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "@@@@@@@@Job stopped")
        job.cancel()
        // Returning true means the job should be rescheduled if it didn't complete
        return true
    }

   private fun doBackgroundWork(params: JobParameters?){
       job = CoroutineScope(Dispatchers.IO).launch {

           Log.d(TAG, "#########doBackgroundWork: Upload your file here")
           delay(2000)
           jobFinished(params, true)
       }
   }
}


