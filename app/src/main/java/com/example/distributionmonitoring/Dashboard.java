package com.example.distributionmonitoring;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

import ButtonClass.Sapbutton;
import ButtonClass.flubutton;
import ButtonClass.freebutton;
import ButtonClass.pwdbutton;
import ButtonClass.seniorbutton;
import ButtonClass.solobutton;
import Manager.LoginManager;
import Manager.ResetPasswordManager;

public class Dashboard extends AppCompatActivity {
    CardView logout,sap,senior,vaccine,pwd,free,transaction,solo;
    TextView verifymsg;
    Button verifybtn;
    LinearLayout linearL;
    FirebaseAuth fAuth;
    ProgressDialog progressDialog;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        fAuth =FirebaseAuth.getInstance();
        verifymsg=findViewById(R.id.vermsg);
        verifybtn=findViewById(R.id.verbtn);
        pwd = findViewById(R.id.pwd_C);
        sap = findViewById(R.id.sap_C);
        senior = findViewById(R.id.senior_C);
        solo= findViewById(R.id.solo_C);
        free = findViewById(R.id.free_C);
        vaccine=findViewById(R.id.vaccine_C);
        transaction=findViewById(R.id.transact_C);
        logout = findViewById(R.id.Logout);
        linearL= findViewById(R.id.linearL);

        if(!fAuth.getCurrentUser().isEmailVerified()){
            verifymsg.setVisibility(View.VISIBLE);
            verifybtn.setVisibility(View.VISIBLE);
            linearL.setVisibility(View.VISIBLE);
        }

        verifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Dashboard.this,"Email Verification sent",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Dashboard.this, LoginManager.class));

                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                verifymsg.setVisibility(View.GONE);
                                verifybtn.setVisibility(View.GONE);
                                linearL.setVisibility(View.GONE);
                            }
                        },5000);
                finish();

                    }
                });
            }
        });

        pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, pwdbutton.class));
                finish();
            }
        });
        sap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Sapbutton.class));
                finish();
            }
        });
        senior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, seniorbutton.class));
                finish();
            }
        });
        solo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, solobutton.class));
                finish();
            }
        });
        free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, freebutton.class));
                finish();
            }
        });
        vaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, flubutton.class));
                finish();
            }
        });
        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,transaction.class));
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Dashboard.this,LoginManager.class));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.resetuserpass){
            startActivity(new Intent(Dashboard.this, ResetPasswordManager.class));
        }
        return super.onOptionsItemSelected(item);
    }
}