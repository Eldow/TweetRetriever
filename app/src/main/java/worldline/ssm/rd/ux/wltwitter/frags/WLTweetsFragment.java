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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.async.WLTwitterAsyncTask;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WLTweetsFragment.OnArticleSelectedListener} interface
 * to handle interaction events.
 * Use the {@link WLTweetsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WLTweetsFragment extends Fragment implements WLTwitterAsyncTask.TweetListener, AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView mListView;
    private OnArticleSelectedListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WLTweetsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WLTweetsFragment newInstance(String param1, String param2) {
        WLTweetsFragment fragment = new WLTweetsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public WLTweetsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onTweetsRetrieved(List<Tweet> tweets) {
        final ArrayAdapter<Tweet> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,tweets);
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


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
    // TODO: Rename method, update argument and hook method into UI event
    /*
    public void onButtonPressed(Tweet tweet) {

        if (mListener != null) {
            mListener.onTweetClicked(tweet);
        }
    } */

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnArticleSelectedListener {
        // TODO: Update argument type and name

        void onTweetClicked(Tweet tweet);
    }

}
