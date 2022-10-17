package ButtonClass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.distributionmonitoring.R;

import Manager.sapmanager;
import UserlistClass.Saplist;

public class Sapbutton extends AppCompatActivity {
        Button saplist,sapreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sapbutton);

        saplist = findViewById(R.id.btn_saplist);
        sapreg = findViewById(R.id.btn_sapreg);


        sapreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sapbutton.this, sapmanager.class));
                finish();
            }
        });
        saplist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sapbutton.this, Saplist.class));
            }
        });
    }
}