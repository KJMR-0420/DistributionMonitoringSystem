package com.example.distributionmonitoring;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import Manager.LoginManager;

public class MainActivity extends AppCompatActivity {
    Button adminbtn,userbtn;
    ProgressDialog progressDialog;
    AlertDialog.Builder reset_alert;
    LayoutInflater inflater;
    TextView txtadmin;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adminbtn = findViewById(R.id.btn_admin);
        userbtn = findViewById(R.id.btn_user);
        txtadmin= findViewById(R.id.txt_admin);
        inflater = this.getLayoutInflater();
        reset_alert = new AlertDialog.Builder(this);
        adminbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = inflater.inflate(R.layout.unauthorize,null);

                reset_alert.setTitle("Unauthorized Access")
                        .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(MainActivity.this, AdminDashboard.class));
                    finish();
                    return;
                    }
                }).setNegativeButton("Cancel",null).setView(view).create().show();

            }
        });
        userbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progresslayout);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                },2000);
                Intent intent = new Intent(MainActivity.this, LoginManager.class);
                startActivity(intent);
            }
        });
    }
}