package AdminlistClass;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.distributionmonitoring.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import AdminAdapterClass.PwdAdapter;
import HelperClass.pwdhelper;

public class pwdlist extends AppCompatActivity {
    RecyclerView rv_pwd;
    PwdAdapter pwdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwdlist);

        rv_pwd=findViewById(R.id.RV_pwd);
        rv_pwd.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<pwdhelper> pwdoption = new FirebaseRecyclerOptions.Builder<pwdhelper>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("PWD Registry"),pwdhelper.class)
                .build();
        pwdAdapter = new PwdAdapter(pwdoption);
        rv_pwd.setAdapter(pwdAdapter);


    }
    @Override
    protected void onStart() {
        super.onStart();
        pwdAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        pwdAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);

        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView= (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processSearch(s);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processSearch(String s) {
        FirebaseRecyclerOptions<pwdhelper> pwdoption = new FirebaseRecyclerOptions.Builder<pwdhelper>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("PWD Registry").orderByChild("pwdname").startAt(s).endAt(s+"\uf8ff"),pwdhelper.class)
                .build();

        pwdAdapter = new PwdAdapter(pwdoption);
        pwdAdapter.startListening();
        rv_pwd .setAdapter(pwdAdapter);
    }
}