package com.rana.grocyapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    Button btn_pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_pay = findViewById(R.id.bt_pay);
        //Initailzesd amount
        String sAmount = "100";
        // convert and tound off
        int amount = Math.round(Float.parseFloat(sAmount)*100);
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Initalized razorpay checkout

                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_test_4hvMf8ebZnI7gX");
                checkout.setImage(R.drawable.razer);
                JSONObject object = new JSONObject();
                try {
                    //name
                        object.put("name","Android Coding");
                        // put description

                        object.put("description","Test Payment");
                        // put theme color

                    object.put("theme.colo","#0093DD");
                    object.put("amount",amount);
                    object.put("prefill.contact","9125883150");

                    // put email

                    object.put("prefill.email","digvijayrana0696@gmail.com");

                    checkout.open(MainActivity.this,object);

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        //Initialized  alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Payment ID");
        builder.setMessage(s);
        builder.show();

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}