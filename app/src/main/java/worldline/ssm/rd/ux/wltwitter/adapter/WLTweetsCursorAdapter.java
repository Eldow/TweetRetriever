package worldline.ssm.rd.ux.wltwitter.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.squareup.picasso.Picasso;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseManager;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by franc on 23/10/2015.
 */
public class WLTweetsCursorAdapter extends CursorAdapter{
    public WLTweetsCursorAdapter(Context ctx, Cursor c, int flags){
        super(ctx,c,flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final View view = LayoutInflater.from(context).inflate(R.layout.custom_wltweet, null);
        final WLTViewHolder holder = new WLTViewHolder(view);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final WLTViewHolder holder = (WLTViewHolder) view.getTag();
        final Tweet tweet = WLTwitterDatabaseManager.tweetFromCursor(cursor);
        holder.name.setText(tweet.user.name);
        holder.alias.setText(tweet.user.screenName);
        holder.text.setText(tweet.text);
        Picasso.with(WLTwitterApplication.getContext()).load(tweet.user.profileImageUrl).into(holder.image);
    }
}
