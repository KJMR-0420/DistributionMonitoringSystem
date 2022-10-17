package Manager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.distributionmonitoring.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class RegistrationManager extends AppCompatActivity {

TextInputEditText Name ,Address, Email, Contact, Password,Confirmpassword;
Button Register,cancel, Accept;
TextView TermsH, TermsC;
Timer timer;
AlertDialog.Builder dialogbuilder;
AlertDialog dialog;
FirebaseAuth fAuth;
    FirebaseDatabase root;
    DatabaseReference ref;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_manager);
        getSupportActionBar().hide();


        Name= findViewById(R.id.name);
        Address = findViewById(R.id.address);
        Email =  findViewById(R.id.email);
        Contact =  findViewById(R.id.contactNo);
        Password =  findViewById(R.id.password);
        Confirmpassword =  findViewById(R.id.conpassword);
        Register = findViewById(R.id.btnregister);



        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                root = FirebaseDatabase.getInstance();
//                ref = root.getReference("Admin");

                String fname = Name.getText().toString();
                String faddress = Address.getText().toString();
                String femail = Email.getText().toString();
                String fcontact = Contact.getText().toString();
                String fpassword = Password.getText().toString();
                String fconfirmpassword = Confirmpassword.getText().toString();


                if (fname.isEmpty()){
                    Name.setError("Need to fill up");
                    return;
                }
                if (faddress.isEmpty()){
                    Address.setError("Need to fill up");
                    return;
                }
                if (femail.isEmpty()){
                    Email.setError("Need to fill up");
                    return;
                }
                if (fcontact.isEmpty()){
                    Contact.setError("Need to fill up");
                    return;
                }
                if (fpassword.isEmpty()){
                    Password.setError("Need to fill up");
                    return;
                }
                if (fconfirmpassword.isEmpty()){
                    Confirmpassword.setError("Need to fill up");
                    return;
                }
                if (!fpassword.equals(fconfirmpassword)){
                    Confirmpassword.setError("Password Do not Match");
                    return;
                }
                TermsandConditionDialog();


            }

        });
    }

    public void TermsandConditionDialog(){
        fAuth = FirebaseAuth.getInstance();

        dialogbuilder = new AlertDialog.Builder(this);
         View termspop = getLayoutInflater().inflate(R.layout.termsandcondition,null);
        TermsH = termspop.findViewById(R.id.terms_header);
        TermsC = termspop.findViewById(R.id.terms_content);
        cancel = termspop.findViewById(R.id.cancel);
        Accept = termspop.findViewById(R.id.accept);

        String femail = Email.getText().toString();
        String fpassword = Password.getText().toString();

        dialogbuilder.setView(termspop);
        dialog = dialogbuilder.create();
        dialog.show();

        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(RegistrationManager.this, "Registration Success",Toast.LENGTH_SHORT).show();

                fAuth.createUserWithEmailAndPassword(femail,fpassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // send User to LoginManager
                        startActivity( new Intent(RegistrationManager.this, LoginManager.class));
                        finish();
                        return;

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistrationManager.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
                progressDialog = new ProgressDialog(RegistrationManager.this);
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
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            dialog.dismiss();
            }
        });
    }

}