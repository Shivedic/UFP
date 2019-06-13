package com.apps;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.apps.PaymentIntegrationMethods.OrderConfirmed;
import com.apps.Volley.Volley_Request;
import com.apps.realestate.MainActivity;
import com.apps.realestate.MyApplication;
import com.apps.realestate.R;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Config {
    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    // id to handle the notification in the notification tray
    public static final String SHARED_PREF = "ah_firebase";
    static ProgressDialog progressDialog;
    public static Context mContext;

    public static void moveTo(Context context, Class targetClass) {
        Intent intent = new Intent(context, targetClass);
        context.startActivity(intent);
    }
    public static boolean validateEmail(EditText editText, Context context) {
        String email = editText.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            //editText.setError(context.getString(R.string.err_msg_email));
            editText.requestFocus();
            return false;
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public static void showCustomAlertDialog(Context context, String title, String msg, int type) {
        SweetAlertDialog alertDialog = new SweetAlertDialog(context, type);
        alertDialog.setTitleText(title);

        if (msg.length() > 0)
            alertDialog.setContentText(msg);
        alertDialog.show();
        Button btn = (Button) alertDialog.findViewById(R.id.confirm_button);
        btn.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
    }

    public static void showLoginCustomAlertDialog(final Context context, String title, String msg, int type) {
        SweetAlertDialog alertDialog = new SweetAlertDialog(context, type);
        alertDialog.setTitleText(title);
        alertDialog.setCancelText("Login");
        alertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                //Config.moveTo(context, Login.class);

            }
        });
        alertDialog.setConfirmText("Signup");
        alertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                //Config.moveTo(context, SignUp.class);

            }
        });
        if (msg.length() > 0)
            alertDialog.setContentText(msg);
        alertDialog.show();
        Button btn = (Button) alertDialog.findViewById(R.id.confirm_button);
        btn.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        Button btn1 = (Button) alertDialog.findViewById(R.id.cancel_button);
        btn1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));

    }


    public static void showPincodeCustomAlertDialog(final Context context, String title, String msg, int type) {
        SweetAlertDialog alertDialog = new SweetAlertDialog(context, type);
        alertDialog.setTitleText(title);
        alertDialog.setCancelText("Login");
        alertDialog.setCancelClickListener( new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                //Config.moveTo(context, Login.class);

            }
        });
        alertDialog.setConfirmText("Signup");
        alertDialog.setConfirmClickListener( new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                //Config.moveTo(context, SignUp.class);

            }
        });
        if (msg.length() > 0)
            alertDialog.setContentText(msg);
        alertDialog.show();
        Button btn = (Button) alertDialog.findViewById(R.id.confirm_button);
        btn.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        Button btn1 = (Button) alertDialog.findViewById(R.id.cancel_button);
        btn1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));

    }

    public static void addOrder(final Context context, String transactionId, String duration,String amount) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        mContext = context;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd");
                String date = sdf.format(c.getTime());
        String req = "{\"userid\":\"" + MyApplication.getInstance().getUserId() + "\",\"duration\":\"" + duration + "\",\"price\":\"" + amount+ "\",\"date\":\"" + date + "\",\"payid\":\"" + transactionId + "\"}";
        Volley_Request postRequest = new Volley_Request();
        postRequest.createRequest(context, context.getResources().getString(R.string.mJSONURL_addflexidp), "POST", "AddFlexiOrder", req);

    }

    public static void addOrederResponse(String response){
        try {
            progressDialog.dismiss();
            Log.d("myTag", "recieved response : " + response);
            JSONObject jObj = new JSONObject(response);

            if(jObj.getString("success").equals("true")) {
                Intent intent = new Intent(mContext, OrderConfirmed.class);
                mContext.startActivity(intent);
                ((Activity) mContext).finishAffinity();
            }
        } catch (Exception e ) {
            Log.d("myTag", "error in add order : " , e);
        }
    }

    public static void addOrderPack(final Context context, String transactionId, String duration,String amount) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        mContext = context;
        String req = "{\"userid\":\"" + MyApplication.getInstance().getUserId() + "\",\"amount\":\"" + amount + "\",\"payid\":\"" + transactionId + "\"}";
        Volley_Request postRequest = new Volley_Request();
        postRequest.createRequest(context, context.getResources().getString(R.string.mJSONURL_bookpack), "POST", "AddPackOrder", req);
    }

    public static void addOrderPackResponse(String response){
        try {
            progressDialog.dismiss();
            Log.d("myTag", "recieved response : " + response);
            JSONObject jObj = new JSONObject(response);

            if(jObj.getString("success").equals("true")) {
                Intent intent = new Intent(mContext, OrderConfirmed.class);
                mContext.startActivity(intent);
                ((Activity) mContext).finishAffinity();
            }
        } catch (Exception e ) {
            Log.d("myTag", "error in add order : " , e);
        }
    }


    public static void showCartCustomAlertDialog(final Context context, String title, String msg, int type) {
        SweetAlertDialog alertDialog = new SweetAlertDialog(context, type);
        alertDialog.setTitleText(title);

        if (msg.length() > 0)
            alertDialog.setContentText(msg);
        alertDialog.show();
        Button btn = (Button) alertDialog.findViewById(R.id.confirm_button);
        btn.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });
    }
}