package com.pkg.flyhigh;

import android.graphics.Color;
import android.os.Bundle;

import com.pkg.flyhigh.fragments.DisplayMapFragment;
import com.pkg.flyhigh.fragments.FollowPeopleFragment;
import com.pkg.flyhigh.fragments.TestFragment;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;


public class MainActivity extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle savedInstanceState) {


        /*Setup Account*/
        setDrawerHeaderImage(R.mipmap.mat2);
        setUsername("Vivek Singh");
        setUserEmail("vivek.singh@gmail.com");
        setFirstAccountPhoto(getResources().getDrawable(R.mipmap.ic_launcher));

        allowArrowAnimation();
        this.disableLearningPattern();
        setBackPattern(MaterialNavigationDrawer.BACKPATTERN_BACK_TO_FIRST);
        // create sections
        this.addSection(newSection("Live Tweets", R.mipmap.ic_action_user, new FollowPeopleFragment()).setSectionColor(Color.parseColor("#FE5740")));
        this.addSection(newSection("Locate ISS", R.mipmap.ic_action_camera, new DisplayMapFragment()).setSectionColor(Color.parseColor("#FE5740")));

        // create bottom section
        this.addBottomSection(newSection("Logout", R.mipmap.ic_action_reply, new TestFragment()));

    }
}
