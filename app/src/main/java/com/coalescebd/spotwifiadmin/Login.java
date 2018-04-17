package com.coalescebd.spotwifiadmin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {


    private EditText email, password;
    FirebaseAuth mAuth;
    private Button loginButton,callButton;

    DatabaseReference databaseReference;
    private SalesPerson salesPerson;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
        if (isFirstRun) {
            //show sign up activity
            startActivity(new Intent(Login.this, Register.class));
        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();


        email = findViewById(R.id.txt_usernameDuringLogin);
        password = findViewById(R.id.txt_passwordDuringLogin);
        mAuth = FirebaseAuth.getInstance();
        loginButton = findViewById(R.id.btn_login);
        callButton = findViewById(R.id.btn_callAdmin);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01796588950"));
                startActivity(intent);
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Log.d(TAG,"db ref: "+databaseReference);
        //Spinner Drop down elements


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = email.getText().toString().trim();
                String pass  = password.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(em,pass)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()){
                                        Toast.makeText(Login.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }else {
                                        startActivity(new Intent(Login.this,Main.class));
                                        finish();
                                    }
                            }
                        });

            }
        });

    }
}
