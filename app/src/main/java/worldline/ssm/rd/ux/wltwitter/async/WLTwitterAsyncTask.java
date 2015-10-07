package worldline.ssm.rd.ux.wltwitter.async;

import android.os.AsyncTask;
import android.util.Log;


import java.util.List;
import worldline.ssm.rd.ux.wltwitter.helpers.TwitterHelper;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by franc on 02/10/2015.
 */
public class WLTwitterAsyncTask extends AsyncTask<String, Integer, List<Tweet>> {

    private TweetListener tweetListener;
    public WLTwitterAsyncTask(TweetListener tL){
        tweetListener = tL;
    }
    @Override
    protected List<Tweet> doInBackground(String... params) {
        if(params[0]==null) {
            return null;
        }
        return TwitterHelper.getTweetsOfUser(params[0]);
    }
    protected void onPostExecute(List<Tweet> tweets){
        for (int i = 0; i < tweets.size(); i++) {
            System.out.println("[" + "WLTwitterApplication" + "]" + tweets.get(i).text);
            Log.d("TweetAsyncTask", tweets.get(i).text);
            tweetListener.onTweetsRetrieved(tweets);
        }
    }

    public interface TweetListener {
        void onTweetsRetrieved (List<Tweet> tweets);
    }
}
