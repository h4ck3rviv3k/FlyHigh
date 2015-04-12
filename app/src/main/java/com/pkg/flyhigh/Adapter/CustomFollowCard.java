package com.pkg.flyhigh.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pkg.flyhigh.R;
import com.pkg.flyhigh.drawable.CircleDrawable;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.base.CardViewWrapper;
import it.gmariotti.cardslib.library.view.component.CardThumbnailView;

/**
 * Created by IBM_ADMIN on 4/6/2015.
 */
public class CustomFollowCard extends Card {
    private Context context;

    public CustomFollowCard(Context context) {
        super(context, R.layout.layout_follow_people);
        this.context = context;
        CardThumbnailCircle thumb = new CardThumbnailCircle(context);
        addCardThumbnail(thumb);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        if (view != null) {

                /*Initialize Card Elements*/
            TextView user_name = (TextView) view.findViewById(R.id.text_people);
            CardViewWrapper cardView = getCardView();
            CardThumbnailView thumb = cardView.getInternalThumbnailLayout();
            if (thumb != null) {
                ViewGroup.LayoutParams lp = thumb.getLayoutParams();
                if (lp instanceof ViewGroup.MarginLayoutParams) {
                    ((ViewGroup.MarginLayoutParams) lp).setMargins(25, 0, 0, 5);
                }
            }

        }
    }

    public class CardThumbnailCircle extends CardThumbnail {

        public CardThumbnailCircle(Context context) {
            super(context);

            float density = getContext().getResources().getDisplayMetrics().density;
            int size = (int) (70 * density);
            setUrlResource("https://lh5.googleusercontent.com/-squZd7FxR8Q/UyN5UrsfkqI/AAAAAAAAbAo/VoDHSYAhC_E/s" + size + "/new%2520profile%2520%25282%2529.jpg");
            setErrorResource(R.mipmap.ic_action_reply);
        }

        @Override
        public boolean applyBitmap(View imageView, Bitmap bitmap) {

            CircleDrawable circle = new CircleDrawable(bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                imageView.setBackground(circle);
            else
                imageView.setBackgroundDrawable(circle);
            return true;

        }
    }
}
