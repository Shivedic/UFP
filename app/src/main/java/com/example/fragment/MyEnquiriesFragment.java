package com.example.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.apps.Volley.Volley_Request;
import com.apps.realestate.MyApplication;
import com.apps.realestate.R;
import com.example.adapter.EnquiriesAdapter;
import com.example.adapter.FilterAdapter;
import com.example.adapter.PropertyAdapterLatest;
import com.example.item.Enquiry;
import com.example.item.ItemCowork;
import com.example.item.ItemType;
import com.example.item.Review;
import com.example.util.Constant;
import com.example.util.ItemOffsetDecoration;
import com.example.util.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyEnquiriesFragment extends Fragment {

    ArrayList<ItemCowork> mListItem;
    public static RecyclerView recyclerView;
    static EnquiriesAdapter adapter;
    private ProgressBar progressBar;
    private static LinearLayout lyt_not_found;
    static ArrayList<Enquiry> mEnquiryList;
    ArrayList<String> mPropertyName;
    FilterAdapter filterAdapter;
    public static Context mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.row_recyclerview_home, container, false);
        mListItem = new ArrayList<>();
        mEnquiryList = new ArrayList<>();
        mPropertyName = new ArrayList<>();
        mActivity = getActivity().getBaseContext();
        lyt_not_found = rootView.findViewById(R.id.lyt_not_found);
        progressBar = rootView.findViewById(R.id.progressBar);
        recyclerView = rootView.findViewById(R.id.vertical_courses_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(requireActivity(), R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);
        String req = "{\"uid\":\"" + "3" + "\"}";

//        String req = "{\"uid\":\"" + MyApplication.getInstance().getUserId() + "\"}";
        Volley_Request postRequest = new Volley_Request();
        postRequest.createRequest(getActivity(), this.getResources().getString(R.string.mJSONURL_myenquiries), "POST", "myenquiries", req);

      //  if (JsonUtils.isNetworkAvailable(requireActivity())) {
       //     new AllPropertiesFragment.getLatest().execute(Constant.ALL_PROPERTIES_URL);
        //}
        setHasOptionsMenu(true);
        return rootView;
    }

    public static void myEnquiriesResponse(String response){
        try{
            JSONObject objectJson = new JSONObject(response);
            JSONArray enqArray = objectJson.getJSONArray("enquirydata");
            for(int i=0; i<enqArray.length(); i++){
                Enquiry enquiry = new Enquiry();
                enquiry.setUserName(enqArray.getJSONObject(i).getString("user_name"));
                enquiry.setDesc(enqArray.getJSONObject(i).getString("e_request"));
                enquiry.setPlace(enqArray.getJSONObject(i).getString("place_name"));
                enquiry.setResponse(enqArray.getJSONObject(i).getString("e_response"));
                mEnquiryList.add(enquiry);
            }
            displayData();
        } catch(JSONException e) {
            Log.d("myTag", "error", e);}
    }

    @SuppressLint("StaticFieldLeak")
    private class getLatest extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress(true);
        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            showProgress(false);
            if (null == result || result.length() == 0) {
                lyt_not_found.setVisibility(View.VISIBLE);
            } else {
                try {
                    JSONObject mainJson = new JSONObject(result);
                    JSONArray jsonArray = mainJson.getJSONArray(Constant.ARRAY_NAME);
                    JSONObject objJson;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objJson = jsonArray.getJSONObject(i);
                        ItemCowork objItem = new ItemCowork();
                        objItem.setPId(objJson.getString(Constant.PLACE_ID));
                        objItem.setPropertyName(objJson.getString(Constant.PLACE_TITLE));
                        objItem.setPropertyThumbnailB(objJson.getString(Constant.PLACE_IMAGE));
                        objItem.setRateAvg("4");
                        objItem.setPropertyPrice("1001");
                        //objItem.setPropertyBed(objJson.getString(Constant.PROPERTY_BED));
                        //objItem.setPropertyBath(objJson.getString(Constant.PROPERTY_BATH));
                        objItem.setPropertyStartTime(objJson.getString(Constant.PLACE_TIME_START));
                        objItem.setPropertyEndTime(objJson.getString(Constant.PLACE_TIME_END));
                        objItem.setPropertyWeekStart(objJson.getString(Constant.PLACE_WDSTART));
                        objItem.setPropertyWeekEnd(objJson.getString(Constant.PLACE_WDEND));
                        objItem.setPropertyArea("1000");
                        objItem.setPropertyAddress(objJson.getString(Constant.PLACE_ADDRESS));
                        objItem.setPropertyPurpose(objJson.getString(Constant.PLACE_PURPOSE));
                        objItem.setpropertyTotalRate("36");
                        if (i % 2 == 0) {
                            objItem.setRight(true);
                        }
                        mListItem.add(objItem);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                displayData();
            }
        }
    }

    private static void displayData() {
        adapter = new EnquiriesAdapter(mActivity, mEnquiryList);
        recyclerView.setAdapter(adapter);

        if (adapter.getItemCount() == 0) {
            lyt_not_found.setVisibility(View.VISIBLE);
        } else {
            lyt_not_found.setVisibility(View.GONE);
        }
       /*
        if (JsonUtils.isNetworkAvailable(requireActivity())) {
            new AllPropertiesFragment.getType().execute(Constant.PROPERTIES_TYPE);
        }*/
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
}