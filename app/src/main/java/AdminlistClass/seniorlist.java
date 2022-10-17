package AdminlistClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.distributionmonitoring.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import AdminAdapterClass.SeniorAdapter;
import HelperClass.seniorhelper;

public class seniorlist extends AppCompatActivity {

    RecyclerView rv_senior;
    SeniorAdapter seniorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorlist);
        rv_senior=findViewById(R.id.RV_senior);
        rv_senior.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<seniorhelper> senioroption = new FirebaseRecyclerOptions.Builder<seniorhelper>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Senior Citizen Registry"),seniorhelper.class)
                .build();

        seniorAdapter = new SeniorAdapter(senioroption);
        rv_senior.setAdapter(seniorAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        seniorAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        seniorAdapter.stopListening();
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
        FirebaseRecyclerOptions<seniorhelper> senioroption = new FirebaseRecyclerOptions.Builder<seniorhelper>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Senior Citizen Registry").orderByChild("seniorname").startAt(s).endAt(s+"\uf8ff"),seniorhelper.class)
                .build();

        seniorAdapter = new SeniorAdapter(senioroption);
        seniorAdapter.startListening();
        rv_senior .setAdapter(seniorAdapter);
    }
}