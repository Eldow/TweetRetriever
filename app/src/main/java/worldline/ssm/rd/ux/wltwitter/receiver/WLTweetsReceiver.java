package worldline.ssm.rd.ux.wltwitter.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import worldline.ssm.rd.ux.wltwitter.WLTwitterActivity;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;


/**
 * Created by franc on 23/10/2015.
 */
public class WLTweetsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        WLTwitterActivity.displayNewTweetsNotification(intent.getExtras().getInt(Constants.General.ACTION_NEW_TWEETS_EXTRA_NB_TWEETS),true, true);
    }
}
