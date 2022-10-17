package com.example.distributionmonitoring;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class sendsms extends AppCompatActivity {

   private EditText number,message;
   private Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendsms);

        number = findViewById(R.id.smsphoneNo);
        message = findViewById(R.id.smsmessage);
        send = findViewById(R.id.smsbutton);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        sendSMS();
                    } else {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                    }
                }
            }
        });
    }
    private void sendSMS(){
        String phoneNo = number.getText().toString().trim();
        String SMS = message.getText().toString().trim();
        try {
            SmsManager smsManager = SmsManager.getDefault();
           smsManager.sendTextMessage(phoneNo,null,SMS,null,null);
           Toast.makeText(this,"Message Sent",Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed To send Message",Toast.LENGTH_SHORT).show();
    }
    }
}