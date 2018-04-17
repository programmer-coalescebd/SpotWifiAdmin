package com.coalescebd.spotwifiadmin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class OrderAdapter extends ArrayAdapter <ProductOrder> {

    private List<ProductOrder> productOrderList;
    private Context context;

   public OrderAdapter(List<ProductOrder> productOrderList, Context ctx){
       super(ctx,R.layout.row,productOrderList);
       this.productOrderList = productOrderList;
       this.context = ctx;
   }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.row,parent,false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.textView2);
        TextView phone = (TextView) convertView.findViewById(R.id.textView3);
        TextView product = (TextView) convertView.findViewById(R.id.textView4);
        TextView price = (TextView) convertView.findViewById(R.id.textView5);
        TextView location = (TextView) convertView.findViewById(R.id.textView6);
        final TextView status = (TextView) convertView.findViewById(R.id.textView7);

        ProductOrder po = productOrderList.get(position);
        name.setText("Customer's Name: "+po.getOrderName());
        phone.setText("Customer's Phone: "+po.getOrderPhone());
        product.setText(po.getOrderProduct());
        price.setText("Product price: "+po.getOrderPrice());
        location.setText("Pick-up Location: "+po.getOrderDeliveryLocation());
        status.setText("Delivery Status: "+po.getStatusOfDelivery());
        final int id = po.getCurrentBookingId();

        Button deliveredBtn = (Button)convertView.findViewById(R.id.btn_delivered);
        deliveredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status.setText("Delivery Status: "+"completed");

                DatabaseReference db = FirebaseDatabase.getInstance().getReference("orders");
                DatabaseReference db1 = db.child(String.valueOf(id));
                db1.child("statusOfDelivery").setValue(status.getText().toString());
            }
        });

        Button returnedBtn = (Button)convertView.findViewById(R.id.btn_returned);
        returnedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status.setText("Delivery Status: "+"cancelled");

                DatabaseReference db = FirebaseDatabase.getInstance().getReference("orders");
                DatabaseReference db1 = db.child(String.valueOf(id));
                db1.child("statusOfDelivery").setValue(status.getText().toString());
            }
        });

        return convertView;
    }
}
