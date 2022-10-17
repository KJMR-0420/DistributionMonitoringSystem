package ButtonClass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.distributionmonitoring.R;

import UserlistClass.pwdlist;
import Manager.Pwdmanager;

public class pwdbutton extends AppCompatActivity {

    Button pwdlist,pwdreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwdbutton);

        pwdlist = findViewById(R.id.btn_pwdlist);
        pwdreg = findViewById(R.id.btn_pwdreg);

        pwdreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(pwdbutton.this, Pwdmanager.class));
                finish();
            }
        });
        pwdlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(pwdbutton.this, pwdlist.class));
            }
        });
    }
}