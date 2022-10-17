package com.example.distributionmonitoring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Admin extends AppCompatActivity {
    Button adminlogin;
    EditText adminemail;
    EditText adminpassword;
    FirebaseDatabase root;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        adminemail = findViewById(R.id.admin_email);
        adminpassword = findViewById(R.id.admin_password);
        adminlogin = findViewById(R.id.adminlogin);


        adminlogin.setOnClickListener(new View.OnClickListener() {
            String Adminemail = adminemail.getText().toString().trim();
            String Adminpass = adminpassword.getText().toString().trim();
            @Override
            public void onClick(View v) {
                if (adminemail.getText().toString().isEmpty()){
                    adminemail.setError("Email missing");
                    return;
                }

                if (adminpassword.getText().toString().isEmpty()){
                    adminpassword.setError("Password Missing");
                    return;
                }
                root = FirebaseDatabase.getInstance();
                ref = root.getReference("Admin");

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

    }


}