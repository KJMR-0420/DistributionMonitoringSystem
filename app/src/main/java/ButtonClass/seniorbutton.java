package ButtonClass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.distributionmonitoring.R;

import Manager.SeniorCitizenManager;
import UserlistClass.seniorlist;

public class seniorbutton extends AppCompatActivity {
 Button seniorlist,seniorreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorbutton);

        seniorlist = findViewById(R.id.btn_seniorlist);
        seniorreg = findViewById(R.id.btn_senioreg);

        seniorreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(seniorbutton.this, SeniorCitizenManager.class));
                finish();
            }
        });
        seniorlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(seniorbutton.this, seniorlist.class));
            }
        });
    }

}