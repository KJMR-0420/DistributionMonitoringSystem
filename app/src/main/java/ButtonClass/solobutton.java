package ButtonClass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.distributionmonitoring.R;

import Manager.soloparentmanager;
import UserlistClass.soloparentlist;

public class solobutton extends AppCompatActivity {

    Button sololist,soloreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solobutton);

        sololist = findViewById(R.id.btn_sololist);
        soloreg = findViewById(R.id.btn_soloreg);


        soloreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(solobutton.this, soloparentmanager.class));
                finish();
            }
        });
        sololist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(solobutton.this, soloparentlist.class));
            }
        });
    }
}