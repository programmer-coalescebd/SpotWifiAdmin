package com.coalescebd.spotwifiadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener,AdapterView.OnItemClickListener {

    private EditText salesPersonName,salesPersonPhone,salesPersonEmail,salesPersonPassword, salesPersonLocation;
    private Button registerButton;

    FirebaseAuth auth;
    private Spinner spinner;
    private  String orderDeliveryLocation;
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        registerButton = findViewById(R.id.btn_signupFromSignUpPage);
        salesPersonName = findViewById(R.id.txt_nameDuringRegistration);
        salesPersonPhone = findViewById(R.id.txt_phoneDuringRegistration);
        salesPersonEmail = findViewById(R.id.txt_emailDuringRegistration);
        salesPersonPassword = findViewById(R.id.txt_passwordDuringRegistration);


        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Select Your Sales Point");
        categories.add("Gulshan 2(MK electronics)");
        categories.add("Opposite of US embassy");
        categories.add("Gulshan 2(Four Seasons)");
        categories.add("Gulshan 1(Oppostie of Robi Office)");
        categories.add("Agora");
        categories.add("Gulshan 2 Bata");
        categories.add("Kakoli (opposite of bus counter)");
        categories.add("Shooting Club");

        //Creating adapter for spinner
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,categories);

        //Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

        //attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = salesPersonName.getText().toString().trim();
                final String phone = salesPersonPhone.getText().toString().trim();
                final String email = salesPersonEmail.getText().toString().trim();
                final String password = salesPersonPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter Your Email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter Your Password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter Your Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "Enter Your Phone!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.length() != 11) {
                    Toast.makeText(getApplicationContext(), "Invalid phone number, please insert a valid one!", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()){
                                        Toast.makeText(Register.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    }else {
                                        final DatabaseReference salesData;
                                        salesData = FirebaseDatabase.getInstance().getReference();
                                        startActivity(new Intent(Register.this, Main.class));

                                        salesData.child("salesTeam").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                                String totalSalesPerson = String.valueOf(dataSnapshot.getChildren());
                                                String id = String.valueOf(dataSnapshot.getChildrenCount());
                                                int salesPersonId = 1 + Integer.parseInt(id);
                                                Log.d(TAG,"count id: "+id);
//                                                Log.d(TAG,"current sales id: "+currentSalesId);
                                                SalesPerson salesPerson = new SalesPerson(name,phone,email,password,orderDeliveryLocation);
                                                salesData.child("salesTeam").child(String.valueOf(salesPersonId)).setValue(salesPerson);
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                        finish();
                                    }
                            }
                        });
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        orderDeliveryLocation=item;
        Log.d(TAG,"loc: "+orderDeliveryLocation);
        //Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
