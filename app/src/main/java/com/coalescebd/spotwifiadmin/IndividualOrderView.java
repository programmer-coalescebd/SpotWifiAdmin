package com.coalescebd.spotwifiadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class IndividualOrderView extends AppCompatActivity {

    private static final String TAG = "IndividualOrderView";

    DatabaseReference ref;
    TextView cBI,oDL,oN,oP,oPr,oPro,sOD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_individual_order_view);
        String id = getIntent().getExtras().getString("currentBookingId");
        Log.d(TAG,"id: "+id);
        cBI = findViewById(R.id.txt_customerBookingId);
        oN = findViewById(R.id.txt_customerName);
        oP = findViewById(R.id.txt_customerPhone);
        oDL = findViewById(R.id.txt_customerLocation);
        oPro = findViewById(R.id.txt_customerProduct);
        oPr = findViewById(R.id.txt_customerPrice);
        sOD = findViewById(R.id.txt_customerDeliveryStatus);

        ref = FirebaseDatabase.getInstance().getReference("orders").child(id);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ProductOrder po = dataSnapshot.getValue(ProductOrder.class);
                if (po != null) {
                    cBI.setText(String.valueOf(po.getCurrentBookingId()));
                    oDL.setText(po.getOrderDeliveryLocation());
                    oN.setText(po.getOrderName());
                    oP.setText(po.getOrderPhone());
                    oPr.setText(po.getOrderPrice());
                    oPro.setText(po.getOrderProduct());
                    sOD.setText(po.getStatusOfDelivery());

                    Log.d(TAG,"product name: "+oPro);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
