package com.apps.realestate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.apps.Volley.Volley_Request;
import com.example.item.DailyPass;
import com.example.item.ItemType;
import com.example.util.Constant;
import com.example.util.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DpDashboardActivity extends AppCompatActivity {

    Toolbar toolbar;
    static ArrayList<DailyPass> mSilverDp,mGoldDp,mPlatinumDp;
    public static String silvercount,goldcount,platinumcount = "";
    static TextView dpText;
    public static Context mContext;
    public static Activity mActivity;
    public static android.support.v4.app.FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dp_dashboard);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = getApplicationContext();
        mActivity = this;
        fm = getSupportFragmentManager();
        mSilverDp = new ArrayList<>();
        mGoldDp = new ArrayList<>();
        mPlatinumDp = new ArrayList<>();
        dpText = (TextView) findViewById(R.id.my_dp_text);
        if(MyApplication.getInstance().getIsLogin()) {
            String req = "{\"uid\":\"" + MyApplication.getInstance().getUserId() + "\"}";
            Volley_Request postRequest = new Volley_Request();
            postRequest.createRequest(this, this.getResources().getString(R.string.mJSONURL_getuserdp), "POST", "GetUserDp", req);
        } else {
            dpText.setText("You have no daily passes in your account");
        }

        Volley_Request postRequest = new Volley_Request();
        postRequest.createRequest(this, this.getResources().getString(R.string.mJSONURL_dpdesc), "POST", "GetDpDesc", "");




    }

    public static void getUserDpResponse(String response){
        try{
            JSONObject jObj = new JSONObject(response);
            JSONArray appArr = jObj.getJSONArray(Constant.ARRAY_NAME); 
            JSONObject dpObj = appArr.getJSONObject(0);
            silvercount = dpObj.getString("silver_count");
            goldcount = dpObj.getString("gold_count");
            platinumcount = dpObj.getString("platinum_count");
            dpText.setText("You have \n" + "Silver Passes : " + silvercount + "\nGold Passes : " + goldcount + "\nPlatinum Passes : " + platinumcount );
        }catch(JSONException e){
            Log.d("myTag", "error : " , e);
        }

}

    public static void getDpDescResponse(String response){
        try{
            JSONObject jObj = new JSONObject(response);
            JSONArray appArr = jObj.getJSONArray("enquirydata");
            for(int i=0; i<appArr.length();i++){
            DailyPass dpd = new DailyPass();
                    dpd.setType(appArr.getJSONObject(i).getString("dp_class"));
                dpd.setPrice(appArr.getJSONObject(i).getString("dp_duration"));
                dpd.setDuration(appArr.getJSONObject(i).getString("dp_price_inr"));
                if(i<5){mSilverDp.add(dpd);}
                else if (i>= 5 && i<10) {mGoldDp.add(dpd);}
                else if (i>9) {mPlatinumDp.add(dpd);}
            }
            setresult();
        }catch(JSONException e){
            Log.d("myTag", "error : " , e);
        }
    }

    private static void setresult(){
        ViewPager viewPager = (ViewPager) mActivity.findViewById(R.id.viewPager);

        CardFragmentPagerAdapter pagerAdapter = new CardFragmentPagerAdapter(fm, dpToPixels(2, mContext), mPlatinumDp);
        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        viewPager.setOffscreenPageLimit(3);


        ViewPager viewPager2 = (ViewPager) mActivity.findViewById(R.id.viewPager2);

        CardFragmentPagerAdapter pagerAdapter2 = new CardFragmentPagerAdapter(fm, dpToPixels(2, mContext), mGoldDp);
        ShadowTransformer fragmentCardShadowTransformer2 = new ShadowTransformer(viewPager2, pagerAdapter2);
        fragmentCardShadowTransformer.enableScaling(true);

        viewPager2.setAdapter(pagerAdapter2);
        viewPager2.setPageTransformer(false, fragmentCardShadowTransformer2);
        viewPager2.setOffscreenPageLimit(3);

        ViewPager viewPager3 = (ViewPager) mActivity.findViewById(R.id.viewPager3);

        CardFragmentPagerAdapter pagerAdapter3 = new CardFragmentPagerAdapter(fm, dpToPixels(2, mContext), mSilverDp);
        ShadowTransformer fragmentCardShadowTransformer3 = new ShadowTransformer(viewPager3, pagerAdapter3);
        fragmentCardShadowTransformer.enableScaling(true);

        viewPager3.setAdapter(pagerAdapter3);
        viewPager3.setPageTransformer(false, fragmentCardShadowTransformer3);
        viewPager3.setOffscreenPageLimit(3);

    }
    /**
     * Change value in dp to pixels
     * @param dp
     * @param context
     * @return
     */
    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
}
