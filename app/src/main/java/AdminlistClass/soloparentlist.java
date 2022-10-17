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

import AdminAdapterClass.SoloparentAdapter;
import HelperClass.soloparenthelper;

public class soloparentlist extends AppCompatActivity {
    RecyclerView rv_soloparent;
    SoloparentAdapter soloparentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soloparentlist);
        rv_soloparent=findViewById(R.id.RV_soloparent);
        rv_soloparent.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<soloparenthelper> soloparentoption = new FirebaseRecyclerOptions.Builder<soloparenthelper>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Solo Parents Registry"),soloparenthelper.class)
                .build();

        soloparentAdapter = new SoloparentAdapter(soloparentoption);
        rv_soloparent.setAdapter(soloparentAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        soloparentAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        soloparentAdapter.stopListening();
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
        FirebaseRecyclerOptions<soloparenthelper> soloparentoption = new FirebaseRecyclerOptions.Builder<soloparenthelper>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Solo Parents Registry").orderByChild("soloname").startAt(s).endAt(s+"\uf8ff"),soloparenthelper.class)
                .build();

        soloparentAdapter = new SoloparentAdapter(soloparentoption);
        soloparentAdapter.startListening();
        rv_soloparent .setAdapter(soloparentAdapter);
    }
}