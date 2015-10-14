package worldline.ssm.rd.ux.wltwitter.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import worldline.ssm.rd.ux.wltwitter.R;

/**
 * Created by franc on 09/10/2015.
 */
public class WLTViewHolder {
    public ImageView image;
    public TextView name;
    public TextView alias;
    public TextView text;
    public Button button;
    //hold components locations
    public WLTViewHolder(View view){
        image = (ImageView)view.findViewById(R.id.image_view_tweet);
        name = (TextView)view.findViewById(R.id.text_view_username);
        alias = (TextView)view.findViewById(R.id.text_view_alias);
        text = (TextView)view.findViewById(R.id.text_view_content);
        button = (Button)view.findViewById(R.id.button_rt_tweet);
    }
}
