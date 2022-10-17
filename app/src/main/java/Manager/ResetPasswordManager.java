package Manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.distributionmonitoring.Dashboard;
import com.example.distributionmonitoring.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResetPasswordManager extends AppCompatActivity {
    EditText userpass,userconpass;
    Button resbtn;
    FirebaseUser user ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_manager);
    userpass = findViewById(R.id.respass);
    userconpass = findViewById(R.id.conrespass);
    resbtn= findViewById(R.id.resetbtn);

    user=FirebaseAuth.getInstance().getCurrentUser();
    resbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(userpass.getText().toString().isEmpty()){
                userpass.setError("Required Field");
                return;
            }

            if(userconpass.getText().toString().isEmpty()){
                userconpass.setError("Required Field");
                return;
            }

            if(!userpass.getText().toString().equals(userconpass.getText().toString())){
                userconpass.setError("Password do not Match");
            return;
            }
user.updatePassword(userpass.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
    @Override
    public void onSuccess(Void aVoid) {
        Toast.makeText(ResetPasswordManager.this, "Password Updated",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ResetPasswordManager.this, Dashboard.class));
        finish();
    }
}).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
        Toast.makeText(ResetPasswordManager.this,e.getMessage(),Toast.LENGTH_SHORT).show();
    }
});
        }
    });
    }
}