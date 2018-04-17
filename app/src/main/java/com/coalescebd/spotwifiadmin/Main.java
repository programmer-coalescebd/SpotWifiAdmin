package com.coalescebd.spotwifiadmin;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends AppCompatActivity {

    String currentBookingId;

    private TextView textView;
    private ListView listView;
    Context context;

    private static final String TAG = "RegisterActivity";

    private DatabaseReference databaseReference;

    ArrayList<ProductOrder> productList;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_product_orders);
        textView = findViewById(R.id.text_ViewMainScreen);
        productList = new ArrayList<ProductOrder>();
        context = getApplicationContext();

        databaseReference = FirebaseDatabase.getInstance().getReference("orders");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    ProductOrder ptbd = postSnapshot.getValue(ProductOrder.class);
                    if (ptbd != null){
                        productList.add(ptbd);
                        currentBookingId = String.valueOf(ptbd.getCurrentBookingId());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // this code will be executed after 2 seconds
                final OrderAdapter arrayAdapter = new OrderAdapter(productList,context);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(arrayAdapter);
                    }
                });
            }
        }, 1000);
    }
}
