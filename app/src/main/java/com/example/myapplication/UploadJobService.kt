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

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "@@@@@@@Job started")
        doBackgroundWork(params)
        // Returning true means the job is still running asynchronously
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "@@@@@@@@Job canceled before completion")
        jobCanceled = true
        // Returning true means the job should be rescheduled if it didn't complete
        return true
    }

    private var jobCanceled : Boolean = false
//    private suspend fun doBackgroundWork1(params: JobParameters?, context: Context) = coroutineScope{
//        launch {
//            kotlin.run {
//
//                Log.d(TAG, "run: $$$$$$")
//
//                if (jobCanceled) {
//                    return@run
//                }
//
//                delay(1000L)
//
//                Log.d(TAG, "Job finish")
//                jobFinished(params,false)
//            }
//        }
//    }
   private fun doBackgroundWork(params: JobParameters?){
       Thread(Runnable {
           kotlin.run {

                   Log.d(TAG, "run: $$$$$$")

                   if (jobCanceled) {
                       return@run
                   }

                   Thread.sleep(1000L)

               Log.d(TAG, "Job finish")
               jobFinished(params,false)
           }
       }).start()
   }
}


