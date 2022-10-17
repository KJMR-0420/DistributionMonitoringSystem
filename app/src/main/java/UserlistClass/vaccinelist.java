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

import UserAdapterClass.VaccineAdapter;
import HelperClass.vaccinehelper;

public class vaccinelist extends AppCompatActivity {
    RecyclerView rv_vaccine;
    VaccineAdapter vaccineAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccinelist);
        rv_vaccine=findViewById(R.id.RV_vaccine);
        rv_vaccine.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<vaccinehelper> vaccineoption = new FirebaseRecyclerOptions.Builder<vaccinehelper>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Vaccine Registry"),vaccinehelper.class)
                .build();

        vaccineAdapter = new VaccineAdapter(vaccineoption);
        rv_vaccine.setAdapter(vaccineAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        vaccineAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        vaccineAdapter.stopListening();
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
        FirebaseRecyclerOptions<vaccinehelper> vaccineoption = new FirebaseRecyclerOptions.Builder<vaccinehelper>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Vaccine Registry").orderByChild("vaccinename").startAt(s).endAt(s+"\uf8ff"),vaccinehelper.class)
                .build();

        vaccineAdapter = new VaccineAdapter(vaccineoption);
        vaccineAdapter.startListening();
        rv_vaccine .setAdapter(vaccineAdapter);
    }
}