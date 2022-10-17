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

import AdminAdapterClass.SapAdapter;
import HelperClass.saphelper;

public class Saplist extends AppCompatActivity {

    RecyclerView rv_sap;
    SapAdapter sapAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saplist);
        rv_sap=findViewById(R.id.RV_sap);
        rv_sap.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<saphelper> sapoption = new FirebaseRecyclerOptions.Builder<saphelper>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("SAP Registry"),saphelper.class)
                .build();

        sapAdapter = new SapAdapter(sapoption);
        rv_sap.setAdapter(sapAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        sapAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        sapAdapter.stopListening();
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
        FirebaseRecyclerOptions<saphelper> sapoption = new FirebaseRecyclerOptions.Builder<saphelper>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("SAP Registry").orderByChild("sapname").startAt(s).endAt(s+"\uf8ff"),saphelper.class)
                .build();

        sapAdapter = new SapAdapter(sapoption);
        sapAdapter.startListening();
        rv_sap.setAdapter(sapAdapter);
    }
}