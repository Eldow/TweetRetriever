package worldline.ssm.rd.ux.wltwitter.service;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.async.WLTwitterAsyncTask;
import worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseManager;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;

/**
 * Created by franc on 23/10/2015.
 */
public class WLTweetService extends Service implements WLTwitterAsyncTask.TweetListener {
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Toast.makeText(WLTwitterApplication.getContext(), "service starting", Toast.LENGTH_LONG).show();
        String username = WLTwitterApplication.getContext().getSharedPreferences(Constants.Preferences.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE).getString("username", null);
        if(!TextUtils.isEmpty(username)){
            new WLTwitterAsyncTask(this).execute(username);
        }
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onTweetsRetrieved(List<Tweet> tweets) {
        int nbTweetsInserted = 0;
        for(int i = 0; i < tweets.size(); i++) {
            int check = WLTwitterDatabaseManager.insertTweet(tweets.get(i));
            if(check !=-1)nbTweetsInserted += 1;
        }
        if(nbTweetsInserted>0) {
            final Intent newTweetsIntent = new Intent(Constants.General.ACTION_NEW_TWEETS);
            final Bundle extras = new Bundle();
            extras.putInt(Constants.General.ACTION_NEW_TWEETS_EXTRA_NB_TWEETS, nbTweetsInserted);
            newTweetsIntent.putExtras(extras);
            sendBroadcast(newTweetsIntent);
        }
        Log.d(Constants.General.LOG_TAG, "Stop service");
        stopSelf();
    }
}
