package net.deschulz.asynctasks;

/**
 * Created by schulz on 3/17/17.
 *
 * I just used Object so this is generic.   This must be implemented in whomever
 * sends it to the AsyncTask.  The AsyncTask should provide a constructor so
 * the caller can pass this interface to it.  In the AsyncTask, getObject()
 * will be called in OnPreExecute() and taskFinishedCallback will be implemented
 * in onPostExecute().
 */

public interface AsyncCommunicatorInterface {
    Object getObject();
    void taskFinishedCallback(Object o);
}