package worldline.ssm.rd.ux.wltwitter.frags;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.WLTwitterActivity;
import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WLTweetFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WLTweetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WLTweetFragment extends Fragment implements View.OnClickListener {

    //params
    private String name;
    private String alias;
    private String content;
    private String userImage;

    private OnFragmentInteractionListener mListener;

    public static WLTweetFragment newInstance(String name, String alias, String content, String userImage) {
        WLTweetFragment fragment = new WLTweetFragment();
        //Store args into bundle
        Bundle args = new Bundle();
        args.putString("NAME", name);
        args.putString("ALIAS", alias);
        args.putString("CONTENT",content);
        args.putString("USERIMAGE", userImage);
        fragment.setArguments(args);
        return fragment;
    }

    public WLTweetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //Retrieve args
            name = getArguments().getString("NAME");
            alias = getArguments().getString("ALIAS");
            content = getArguments().getString("CONTENT");
            userImage = getArguments().getString("USERIMAGE");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Close on click
        container.setOnClickListener(this);
        //Get the root View to retrieve components
        View rootView =  inflater.inflate(R.layout.fragment_wltweet, container, false);
        TextView usernameView = (TextView) rootView.findViewById(R.id.tweet_fragment_text_view_username);
        TextView aliasView = (TextView) rootView.findViewById(R.id.tweet_fragment_text_view_alias);
        TextView contentView = (TextView) rootView.findViewById(R.id.tweet_fragment_text_view_content);
        ImageView userImageView = (ImageView) rootView.findViewById(R.id.tweet_fragment_image_view);
        //Load user image into ImageView and set textViews
        Picasso.with(WLTwitterApplication.getContext()).load(userImage).into(userImageView);
        usernameView.setText(name);
        aliasView.setText(alias);
        contentView.setText(content);
        // Inflate the layout for this fragment
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            //mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        WLTwitterActivity activity = (WLTwitterActivity) getActivity();
        activity.onTweetFragmentClicked();
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
