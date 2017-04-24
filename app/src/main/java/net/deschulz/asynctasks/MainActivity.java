package net.deschulz.asynctasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
    implements AsyncCommunicatorInterface {

    public static final String TAG = "DesLog";
    private ProgressBar mProgress;
    private Object genericObj;
    private String retString;
    private Button mStartButton;
    MyAsyncWrapper maw;

    /******   AsynchCommunicatorInterface implementation ****/
    @Override
    public Object getObject() {
        return mProgress;
    }

    @Override
    public void taskFinishedCallback(Object o) {
        retString = (String)o;
        TextView tv = (TextView) findViewById(R.id.result);
        tv.setText(retString);
        mStartButton.setEnabled(true);
        mProgress.setProgress(0);

    }
    /************* end of AsynchCommunicatorInterface implementation */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartButton = (Button) findViewById(R.id.startbutton);

        mProgress = (ProgressBar) findViewById(R.id.progressBar);


    }

    public void clicked(View v) {
        Log.i(TAG,"AsyncTask: Clicked()");

        // This instantiates a new MyAsyncWrapper and passes a reference
        // of the caller (me) to the MyAsyncWrapper constructor

        maw = new MyAsyncWrapper(MainActivity.this);
        maw.execute();
        TextView tv = (TextView) findViewById(R.id.result);
        tv.setText("crunching the numbers");
        mStartButton.setEnabled(false);

    }
}
