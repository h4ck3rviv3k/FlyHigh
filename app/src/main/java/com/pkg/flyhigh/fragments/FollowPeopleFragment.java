package com.pkg.flyhigh.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getbase.floatingactionbutton.AddFloatingActionButton;
import com.pkg.flyhigh.Adapter.CustomFollowCard;
import com.pkg.flyhigh.R;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by IBM_ADMIN on 4/6/2015.
 */
public class FollowPeopleFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Toolbar toolbar;
    private TextView title;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private CardListView listView;
    private AddFloatingActionButton floatButton;
    private String mtweet;

    // TODO: Rename and change types and number of parameters
    public static FollowPeopleFragment newInstance(String param1) {
        FollowPeopleFragment fragment = new FollowPeopleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public FollowPeopleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_add_peoples, container, false);

        floatButton = (AddFloatingActionButton) view.findViewById(R.id.btn_add_question);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                if (oldFragment != null && oldFragment != fragment)
//                    ft.remove((android.support.v4.app.Fragment) oldFragment);
                SendTweetFragment fragment = new SendTweetFragment();

                ft.replace(it.neokree.materialnavigationdrawer.R.id.frame_container, (android.support.v4.app.Fragment) fragment).commit();
            }
        });
        listView = (CardListView) view.findViewById(R.id.list);
        initCards();
        return view;
    }

    private void initCards() {
        //Init an array of Cards
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < 11; i++) {
            mtweet = "Hi #samantha how you doing there?";
            if (i == 1)
                mtweet = "Good night from #space. Buona notte dallo spazio";
            if (i == 2)
                mtweet = "Some more #ColorsOfTheEarth";
            if (i == 3)
                mtweet = "Moon Light over the #Mediterranean";
            if (i == 4)
                mtweet = "Take a close look at the dessert near #El Khagra";
            if (i == 5)
                mtweet = "Just a few weeks until #Expo2015Milano... are you ready to meet the world in Milan?";
            if (i == 6)
                mtweet = "Tropical Cyclone #Jaolane appearing at the horizon ,(IT) II cyclone tropicale joalane appare all' orizzonte ";
            if (i == 7)
                mtweet = "Good night from #space. Buona notte dallo spazio";
            if (i == 8)
                mtweet = "Looking towards central Europe on this feb. Pass... I Think I recognize a couple of rivers and a big lake";
            if (i == 9)
                mtweet = "One of the Most Distinct sight on the earth : The golden snake of the nile meandering through the dessert to Cairo";
            if (i == 10)
                mtweet = "Both the city of #Rhodes, #Greece and the ruins of Xanthos and Letoon, #Turkey are #WorlHeritage sites. #UNESCO";


            Card card = init_card("Header " + i, i, mtweet);
            cards.add(card);
        }

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);
        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * This method builds a standard header with a custom expand/collpase
     */
    private Card init_card(String titleHeader, int i, String twee) {
        CustomFollowCard card = new CustomFollowCard(getActivity(), twee);
        card.setCardElevation(getResources().getDimension(R.dimen.carddemo_shadow_elevation));
        card.setShadow(false);
        card.setBackgroundColorResourceId(R.color.follow_back_color);

        return card;
    }


}