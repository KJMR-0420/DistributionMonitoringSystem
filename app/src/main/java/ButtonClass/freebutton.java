package ButtonClass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.distributionmonitoring.R;

import Manager.freemilk;
import UserlistClass.Freelist;

public class freebutton extends AppCompatActivity {

    Button freelist, freereg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freebutton);

        freelist = findViewById(R.id.btn_freelist);
        freereg = findViewById(R.id.btn_freereg);

        freereg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(freebutton.this, freemilk.class));
                finish();
            }
        });
        freelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(freebutton.this, Freelist.class));
            }
        });
    }
}