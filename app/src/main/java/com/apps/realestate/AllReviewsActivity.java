package com.apps.realestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.adapter.ReviewsAdapter;
import com.example.item.Review;
import com.example.util.ItemOffsetDecoration;

import java.util.ArrayList;

import static com.apps.realestate.PropertyView.mAllReviewList;
import static com.apps.realestate.PropertyView.mReviewList;
import static com.apps.realestate.PropertyView.toolbar;


public class AllReviewsActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    ReviewsAdapter mAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reviews);
        toolbar = findViewById(R.id.toolbar);
        setTitle(getString(R.string.view_all_reviews));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        recyclerView = findViewById(R.id.vertical_review_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);

        mAdapter = new ReviewsAdapter(this, mAllReviewList);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onBackPressed() {
        // set the flag to true so the next activity won't start up
        //mIsBackButtonPressed = true;
        super.onBackPressed();

    }
}
