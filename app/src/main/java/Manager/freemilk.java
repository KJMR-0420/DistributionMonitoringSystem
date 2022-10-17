package Manager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.distributionmonitoring.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import HelperClass.freehelper;

public class freemilk extends AppCompatActivity {
TextInputEditText parentname,childname,childage,address,milkbrand,contact;
Button procceed;
TextView status;
FirebaseDatabase root;
DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freemilk);


        parentname = findViewById(R.id.freeparentE);
        childname = findViewById(R.id.freechildE);
        childage = findViewById(R.id.freeageE);
        address = findViewById(R.id.freeaddressE);
        milkbrand = findViewById(R.id.freebrandE);
        contact = findViewById(R.id.freecontactE);
        procceed = findViewById(R.id.freeprocceed);
        status = findViewById(R.id.freestatus);
        procceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status.setText("Pending");
                String fparent = parentname.getText().toString();
                String fchild = childname.getText().toString();
                String fchildage = childage.getText().toString();
                String faddress = address.getText().toString();
                String fmilkbrand = milkbrand.getText().toString();
                String fcontact = contact.getText().toString();
                String fstatus = status.getText().toString();
                if (parentname.getText().toString().isEmpty()){
                    parentname.setError("Required Filled");
                    return;
                }
                if (address.getText().toString().isEmpty()){
                    address.setError("Required Filled");
                    return;
                }
                if (contact.getText().toString().isEmpty()){
                    contact.setError("Required Filled");
                    return;
                }
                if (childname.getText().toString().isEmpty()){
                    childname.setError("Required Filled");
                    return;
                }
                if (childage.getText().toString().isEmpty()){
                    childage.setError("Required Filled");
                    return;
                }
                if (milkbrand.getText().toString().isEmpty()){
                    milkbrand.setError("Required Filled");
                    return;
                }

                //status.setVisibility(v);
                root = FirebaseDatabase.getInstance();
                ref = root.getReference("Free Milk Registry");
                freehelper freehelper = new freehelper(fparent,fchild,fchildage,faddress,fmilkbrand,fcontact,fstatus);
                ref.push().child("").setValue(freehelper);

            }
        });

    }
}