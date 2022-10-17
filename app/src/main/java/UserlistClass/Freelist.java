package UserlistClass;

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

import UserAdapterClass.FreeAdapter;
import HelperClass.freehelper;

public class Freelist extends AppCompatActivity {

    RecyclerView rv_free;
    FreeAdapter freeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelist);
        rv_free=findViewById(R.id.RV_free);
        rv_free.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<freehelper> freeoption = new FirebaseRecyclerOptions.Builder<freehelper>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Free Milk Registry"),freehelper.class)
                .build();
        freeAdapter = new FreeAdapter(freeoption);
        rv_free.setAdapter(freeAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        freeAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        freeAdapter.stopListening();
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
        FirebaseRecyclerOptions<freehelper> freeoption = new FirebaseRecyclerOptions.Builder<freehelper>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Free Milk Registry").orderByChild("sapname").startAt(s).endAt(s+"\uf8ff"),freehelper.class)
                .build();

        freeAdapter = new FreeAdapter(freeoption);
        freeAdapter.startListening();
        rv_free.setAdapter(freeAdapter);
    }


}