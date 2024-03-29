package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.apps.realestate.R;
import com.example.adapter.PropertyAdapterFav;
import com.example.db.DatabaseHelper;
import com.example.item.ItemCowork;
import com.example.item.ItemProperty;
import com.example.util.ItemOffsetDecoration;

import java.util.ArrayList;

public class FavouriteFragment extends Fragment {

    ArrayList<ItemCowork> mListItem,mListItem2;
     public RecyclerView recyclerView;
    PropertyAdapterFav adapter;
    private ProgressBar progressBar;
    private LinearLayout lyt_not_found;
    DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.row_recyclerview_home, container, false);
        mListItem = new ArrayList<>();
        mListItem2=new ArrayList<>();
        databaseHelper = new DatabaseHelper(getActivity());
        lyt_not_found = rootView.findViewById(R.id.lyt_not_found);
        progressBar = rootView.findViewById(R.id.progressBar);
        recyclerView = rootView.findViewById(R.id.vertical_courses_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(requireActivity(), R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);

        return rootView;
    }


    private void displayData() {

        adapter = new PropertyAdapterFav(getActivity(), mListItem2);
        recyclerView.setAdapter(adapter);

        if (adapter.getItemCount() == 0) {
            lyt_not_found.setVisibility(View.VISIBLE);
        } else {
            lyt_not_found.setVisibility(View.GONE);
        }
    }


    private void showProgress(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            lyt_not_found.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mListItem = databaseHelper.getFavourite();
        mListItem2.clear();
        for(int i = 0; i < mListItem.size(); i++)
        {
            ItemCowork itemProperty=mListItem.get(i);
            ItemCowork objItem = new ItemCowork();
            objItem.setPId(itemProperty.getPId());
            objItem.setPropertyName(itemProperty.getPropertyName());
            objItem.setPropertyThumbnailB(itemProperty.getPropertyThumbnailB());
            objItem.setRateAvg(itemProperty.getRateAvg());
            objItem.setPropertyPrice(itemProperty.getPropertyPrice());
            objItem.setPropertyStartTime(itemProperty.getPropertyStartTime());
            objItem.setPropertyEndTime(itemProperty.getPropertyEndTime());
            objItem.setPropertyWeekStart(itemProperty.getPropertyWeekStart());
            objItem.setPropertyWeekEnd(itemProperty.getPropertyWeekEnd());
            objItem.setPropertyArea(itemProperty.getPropertyArea());
            objItem.setPropertyAddress(itemProperty.getPropertyAddress());
            objItem.setPropertyPurpose(itemProperty.getPropertyPurpose());
            objItem.setpropertyTotalRate(itemProperty.getpropertyTotalRate());
            if (i % 2 == 0) {
                objItem.setRight(true);
            }
            mListItem2.add(objItem);
        }
        displayData();
    }
}
