package com.apps.realestate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.item.DailyPass;


public class CardFragment extends Fragment {

    private CardView cardView;

    public static Fragment getInstance(int position) {
        CardFragment f = new CardFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);
        return f;
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_viewpager, container, false);
        Bundle args = getArguments();
        final String strtext=args.getString("price");
        final String price =args.getString("dur");
        cardView = (CardView) view.findViewById(R.id.cardView);
        cardView.setMaxCardElevation(cardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);

        TextView title = (TextView) view.findViewById(R.id.title);
        Button button = (Button)view.findViewById(R.id.button);
        TextView validity = (TextView) view.findViewById(R.id.validity_txt);
        TextView visits = (TextView) view.findViewById(R.id.price_text);
        //title.setText(String.format("Card %d", getArguments().getInt("position")));
        title.setText(strtext + " Days");
        validity.setText("Validity : Lifetime");
        visits.setText("Visits : " + strtext);
        button.setText(price + "/-");
       // button.setPaintFlags(button.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(), "Button in Card " + getArguments().getInt("position")
                  //      + "Clicked!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), CheckOutActivity.class);
                Log.d("myTag", "dur is strtxt : " + strtext);
                intent.putExtra("dur", strtext);
                intent.putExtra("price", price);
                intent.putExtra("type", "dpbuy");
                startActivity(intent);

            }
        });

        return view;
    }

    public CardView getCardView() {
        return cardView;
    }
}
