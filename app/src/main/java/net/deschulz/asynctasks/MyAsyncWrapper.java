package net.deschulz.asynctasks;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by schulz on 3/17/17.
 *
 * This demonstrates how to make and use an AsyncTask to communicate with an activity
 * The AsyncCommunkcatorInterface must be implemented by the Activity that you are
 * communicating with.
 *
 * You can also make this a private member class of the Activity and that simplifies
 * things, but I sort of like the idea of using an interface to communicate with whomever
 * spawns this task ...  it is almost as good as a callback.   And this illustrates
 * some powerful features of Java.
 *
 * This particular version gets a ProgressBar from the Activity and when it's done, it
 * returns a String to the Activity through the methos of the AsynchCommunicator interface
 */



public class MyAsyncWrapper extends AsyncTask<String,Integer,String> {
    private static final String TAG = "AsyncTask";

    private ProgressBar mPB;
    private int mProgress;
    AsyncCommunicatorInterface mAC;

    /* Add a constructor so that I can pass in a progress bar. This must
    *  only be accessed by onPreExcute and onProgressUpdate
    */
    public MyAsyncWrapper(AsyncCommunicatorInterface ac) {
        mAC = ac;
        mPB = (ProgressBar)ac.getObject();
    }

    /*
     * Invoked on the UI thread before the task is executed.
     */
    @Override
    protected void onPreExecute(){
        Log.i(TAG,"AsyncTask: onPreExecute()");
        mProgress = 0;
        mPB.setProgress(mProgress);
    }

    /* this is actually called in the main thread after updates triggered by
     * the background task.  So you can update your progress bar here.
     */
    @Override
    protected void onProgressUpdate(Integer... values){
        Log.i(TAG,"AsyncTask: onProgressUpdate() -> " + values[0]);
        mPB.setProgress(values[0]);
    }

    /* this is called after the background task (which is in a different thread)
     * finishes executing.   It can call a method in the interface to communicate back
     * to the main thread.
     */
    @Override
    protected void onPostExecute(String result) {
        Log.i(TAG,"AsyncTask: onPostExecute()");
        String x = "All done";
        mAC.taskFinishedCallback(x);
    }

    /*
     * Invoked after onPreExecute returns.   It returns the correct data type
     * and this is given directly to onPostExecute.   This just shows how we can
     * animate a ProgressBar
     *
     */
    @Override
    protected String doInBackground(String... params) {
        Log.i(TAG,"AsyncTask: doInBackground()");
        int i;
        for (i = 0; i < 100; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            publishProgress(i);

        }
        return "";
    }


}
