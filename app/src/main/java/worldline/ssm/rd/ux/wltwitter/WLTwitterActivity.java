package worldline.ssm.rd.ux.wltwitter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import worldline.ssm.rd.ux.wltwitter.frags.WLTweetFragment;
import worldline.ssm.rd.ux.wltwitter.frags.WLTweetsFragment;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;


public class WLTwitterActivity extends Activity implements WLTweetsFragment.OnArticleSelectedListener{
    private Fragment tweetsFragment;
    private Fragment tweetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String username;
        if(getIntent().getExtras() != null) { //Sign in
            username = getIntent().getExtras().getString("username");
        } else { //Quick start
            username = WLTwitterApplication.getContext().getSharedPreferences(Constants.Preferences.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE).getString("username",null);
        }
        getActionBar().setSubtitle(username);
        tweetsFragment = new WLTweetsFragment();
        FragmentManager fragmentM = getFragmentManager();
        FragmentTransaction transaction = fragmentM.beginTransaction();
        transaction.add(R.id.rootLayout, tweetsFragment);
        transaction.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wltwitter_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        // On Logout
        if (id == R.id.actionLogout) {
            //clear username & password
            WLTwitterApplication.getContext().getSharedPreferences(Constants.Preferences.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE).edit().remove("username").commit();
            WLTwitterApplication.getContext().getSharedPreferences(Constants.Preferences.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE).edit().remove("password").commit();
            //finish this activity
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTweetClicked(Tweet tweet) {
        tweetFragment = WLTweetFragment.newInstance(tweet.user.name, tweet.user.screenName, tweet.text, tweet.user.profileImageUrl);
        FragmentManager fragmentM = getFragmentManager();
        FragmentTransaction transaction = fragmentM.beginTransaction();
        transaction.hide(tweetsFragment);
        transaction.add(R.id.rootLayout,tweetFragment);
        transaction.commit();
    }

    public void onTweetFragmentClicked(){
        FragmentManager fragmentM = getFragmentManager();
        FragmentTransaction transaction = fragmentM.beginTransaction();
        transaction.remove(tweetFragment);
        transaction.show(tweetsFragment);
        transaction.commit();
    }
}
