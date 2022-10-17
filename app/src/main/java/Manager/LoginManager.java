package Manager;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.distributionmonitoring.Dashboard;
import com.example.distributionmonitoring.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

import HelperClass.LoginHelper;

public class LoginManager extends AppCompatActivity {

    Button btnregister;
    EditText logemail;
    EditText logpassword;
    Button btnlogin;
    TextView forpass;
    LayoutInflater inflater;
    AlertDialog.Builder reset_alert;
    ProgressDialog progressDialog;
    FirebaseAuth fAuth;
    FirebaseDatabase root;
    DatabaseReference ref;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_manager);
        getSupportActionBar().hide();
        reset_alert = new AlertDialog.Builder(this);
        inflater = this.getLayoutInflater();

        fAuth = FirebaseAuth.getInstance();

        logemail =  findViewById(R.id.emailE);
        logpassword = findViewById(R.id.passE);
        btnlogin = findViewById(R.id.login);
        btnregister = findViewById(R.id.Create_acc);
        forpass=findViewById(R.id.forgetpass);

       forpass.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               View view = inflater.inflate(R.layout.forgetpass,null);
               EditText email = view.findViewById(R.id.emailpop);

               reset_alert.setTitle("Reset Password ?")
                       .setMessage("Enter Your Email to get Password link")
                        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            if(email.getText().toString().isEmpty()){
                                email.setError("Required Filled");
                                return;
                            }else{
                                fAuth.sendPasswordResetEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        if(!email.equals(fAuth)){
                                            Toast.makeText(LoginManager.this,"Reset Password Link Sent",Toast.LENGTH_SHORT).show();
                                            return;
                                        }

                                    }

                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LoginManager.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            }
                        }).setNegativeButton("Cancel",null)
                       .setView(view)
                       .create().show();
           }
       });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(LoginManager.this);
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
                startActivity(new Intent(LoginManager.this,RegistrationManager.class));
            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if (logemail.getText().toString().isEmpty()){
                logemail.setError("Email missing");
                return;
            }

                if (logpassword.getText().toString().isEmpty()){
                    logpassword.setError("Password Missing");
                    return;
                }

                fAuth.signInWithEmailAndPassword(logemail.getText().toString(),logpassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if(logemail.equals(fAuth)){
                            return;
                        }
                        if(fAuth.getCurrentUser().isEmailVerified()){
                            root = FirebaseDatabase.getInstance();
                            ref = root.getReference("Log In");

                            String femail = logemail.getText().toString();
                            String fpass = logpassword.getText().toString();

                            LoginHelper loghelper = new LoginHelper(femail,fpass);
                            ref.child(femail.replace(".",",")).setValue(loghelper);
                            startActivity(new Intent(LoginManager.this, Dashboard.class));
                            finish();
                        }
                        else{
                            startActivity(new Intent(LoginManager.this,Dashboard.class));
                            finish();
                            return;
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginManager.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
   // @Override
   // protected  void onStart(){
    //    super.onStart();
     //   if(FirebaseAuth.getInstance().getCurrentUser()!= null){
      //      startActivity(new Intent(LoginManager.this,Dashboard.class));
     //   finish();
      //  }
   // }
}