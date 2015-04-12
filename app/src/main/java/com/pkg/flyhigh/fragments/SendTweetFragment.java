package com.pkg.flyhigh.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pkg.flyhigh.R;

/**
 * Created by IBM_ADMIN on 4/12/2015.
 */
public class SendTweetFragment extends Fragment {
    private static final String KEY_CONTENT = "TestFragment:Content";

    public static SendTweetFragment newInstance(int imgid) {
        SendTweetFragment fragment = new SendTweetFragment();
        return fragment;
    }

    private int mImageId = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_send_tweet, container, false);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CONTENT, mImageId);
    }
}
