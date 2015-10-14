package worldline.ssm.rd.ux.wltwitter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by franc on 09/10/2015.
 */
public class WLTweetsAdapter extends BaseAdapter implements View.OnClickListener{

    private List<Tweet> tweetList;
    public WLTweetsAdapter(List<Tweet> tweets){
        tweetList = tweets;
    }
    @Override
    public int getCount() {
        return tweetList.size();
    }

    @Override
    public Object getItem(int position) {
        return tweetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WLTViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(WLTwitterApplication.getContext());
        if(null==convertView){
            convertView = inflater.inflate(R.layout.custom_wltweet,null);
            holder = new WLTViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (WLTViewHolder)convertView.getTag();
        }
        final Tweet tweet = (Tweet)getItem(position);
        //Retrieve components with holder and set their value
        holder.name.setText(tweet.user.name);
        holder.alias.setText(tweet.user.screenName);
        holder.text.setText(tweet.text);
        holder.button.setOnClickListener(this);
        Picasso.with(WLTwitterApplication.getContext()).load(tweet.user.profileImageUrl).into(holder.image);
        return convertView;
    }
    @Override
    public void onClick(View v) {
        Toast.makeText(WLTwitterApplication.getContext(),"RT pressed", Toast.LENGTH_LONG).show();
    }
}
