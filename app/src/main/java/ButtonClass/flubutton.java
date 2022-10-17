package ButtonClass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.distributionmonitoring.R;

import Manager.vaccinemanager;
import UserlistClass.vaccinelist;

public class flubutton extends AppCompatActivity {

    Button flulist,flureg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flubutton);

        flulist = findViewById(R.id.btn_flulist);
        flureg = findViewById(R.id.btn_flureg);

        flureg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(flubutton.this, vaccinemanager.class));
                finish();
            }
        });
        flulist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(flubutton.this, vaccinelist.class));
            }
        });
    }
}