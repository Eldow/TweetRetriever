package worldline.ssm.rd.ux.wltwitter.frags;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import java.util.List;
import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.adapter.WLTweetsAdapter;
import worldline.ssm.rd.ux.wltwitter.async.WLTwitterAsyncTask;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;

public class WLTweetsFragment extends Fragment implements WLTwitterAsyncTask.TweetListener, AdapterView.OnItemClickListener {


    private ListView mListView;
    private OnArticleSelectedListener mListener;

    public WLTweetsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onTweetsRetrieved(List<Tweet> tweets) {
        //final ArrayAdapter<Tweet> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,tweets);
        final WLTweetsAdapter adapter = new WLTweetsAdapter(tweets);
        mListView.setAdapter(adapter);
    }

    public void onStart(){
        super.onStart();
        String username = WLTwitterApplication.getContext().getSharedPreferences(Constants.Preferences.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE).getString("username", null);
        if(!TextUtils.isEmpty(username)){
            new WLTwitterAsyncTask(this).execute(username);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_wltweets, container, false);
        mListView = (ListView) rootView.findViewById(R.id.tweetsListView);
        addProgressBar(rootView);
        mListView.setOnItemClickListener(this);
        return rootView;
    }

    private void addProgressBar(View rootView){
        final ProgressBar progressBar = new ProgressBar((getActivity()));
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100,100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        mListView.setEmptyView(progressBar);
        ((ViewGroup)rootView).addView(progressBar,params);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (OnArticleSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        final Tweet tweet = (Tweet)adapter.getItemAtPosition(position);
        mListener.onTweetClicked(tweet);
    }
    //interface for onTweetClicked
    public interface OnArticleSelectedListener {
        void onTweetClicked(Tweet tweet);
    }

}
