package com.example.distributionmonitoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import AdminlistClass.Freelist;
import AdminlistClass.Saplist;
import AdminlistClass.pwdlist;
import AdminlistClass.seniorlist;
import AdminlistClass.soloparentlist;
import AdminlistClass.vaccinelist;

public class AdminDashboard extends AppCompatActivity {

    Button btnpwd,btnsap,btnsenior,btnsolo,btnvac,btnfree,btnsms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        btnpwd = findViewById(R.id.btn_pwd);
        btnsap = findViewById(R.id.btn_sap);
        btnsenior = findViewById(R.id.btn_senior);
        btnsolo = findViewById(R.id.btn_solo);
        btnvac = findViewById(R.id.btn_vaccine);
        btnfree = findViewById(R.id.btn_free);
        btnsms = findViewById(R.id.btn_sms);
        btnpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, pwdlist.class));
            }
        });
        btnsap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, Saplist.class));
            }
        });
        btnsenior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, seniorlist.class));
            }
        });
        btnsolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, soloparentlist.class));
            }
        });
        btnvac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, vaccinelist.class));
            }
        });
        btnfree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, Freelist.class));
            }
        });
        btnsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, sendsms.class));
            }
        });

}}