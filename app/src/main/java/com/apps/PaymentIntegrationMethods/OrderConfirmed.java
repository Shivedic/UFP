package com.apps.PaymentIntegrationMethods;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.apps.realestate.MainActivity;
import com.apps.realestate.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderConfirmed extends AppCompatActivity {

   // @BindView(R.id.continueShopping)
    Button continueShopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmed);
        continueShopping = (Button) findViewById(R.id.continueShopping);
        //    ButterKnife.bind(this);
        continueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderConfirmed.this, MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }

    /*
    @OnClick(R.id.continueShopping)
    public void onClick(View view) {
        Intent intent = new Intent(OrderConfirmed.this, MainActivity.class);
        startActivity(intent);
        finishAffinity();
    }*/

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrderConfirmed.this, MainActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}
