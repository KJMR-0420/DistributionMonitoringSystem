package UserAdapterClass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.distributionmonitoring.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import HelperClass.vaccinehelper;

public class VaccineAdapter extends FirebaseRecyclerAdapter<vaccinehelper,VaccineAdapter.VaccineViewHolder> {
    public VaccineAdapter(@NonNull FirebaseRecyclerOptions<vaccinehelper> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull VaccineViewHolder holder, int position, @NonNull vaccinehelper model) {
        holder.vaccinelinear.setVisibility(View.GONE);
        holder.vaccinename.setText(model.getVaccinename());
        holder.vaccineaddress.setText(model.getVaccineaddress());
        holder.vaccinecontact.setText(model.getVaccinecontact());
//
//        holder.vaccineedit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.vaccinename.getContext())
//                        .setContentHolder(new ViewHolder(R.layout.vaccinedialogcontent))
//                        .setExpanded(true, 700)
//                        .create();
//
//                dialogPlus.show();
//
//                View vaccineview = dialogPlus.getHolderView();
//
//                EditText vaccinename = vaccineview.findViewById(R.id.vaccine_name_edit);
//                EditText vaccineaddres = vaccineview.findViewById(R.id.vaccine_address_edit);
//                EditText vaccinecontact = vaccineview.findViewById(R.id.vaccine_contact_edit);
//
//                Button vaccineupdate = vaccineview.findViewById(R.id.vaccineupdate);
//
//                vaccinename.setText(model.getVaccinename());
//                vaccineaddres.setText(model.getVaccineaddress());
//                vaccinecontact.setText(model.getVaccinecontact());
//
//                dialogPlus.show();
//
//                vaccineupdate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Map<String, Object> map = new HashMap<>();
//
//                        map.put("vaccinename",vaccinename.getText().toString());
//                        map.put("vaccineaddress",vaccineaddres.getText().toString());
//                        map.put("vaccinecontact",vaccinecontact.getText().toString());
//
//                        FirebaseDatabase.getInstance().getReference().child("Vaccine Registry")
//                                .child(getRef(position).getKey()).updateChildren(map)
//                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void aVoid) {
//                                        dialogPlus.dismiss();
//                                    }
//                                }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                dialogPlus.dismiss();
//                            }
//                        });
//                    }
//                });
//            }
//        });
//        holder.vaccinedel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(holder.vaccinename.getContext());
//                builder.setTitle("Delete Data");
//                builder.setMessage("Are you sure you want to delete? it will Delete Permanently");
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        FirebaseDatabase.getInstance().getReference().child("Vaccine Registry")
//                                .child(getRef(position).getKey()).removeValue();
//                    }
//                });
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                builder.show();
//            }
//        });
    }

    @NonNull
    @Override
    public VaccineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vaccinelayoutlist,parent,false);

        return  new VaccineViewHolder(view);
    }
    class VaccineViewHolder extends RecyclerView.ViewHolder {

        ImageView vaccineedit,vaccinedel,vaccinesms;
        LinearLayout vaccinelinear;
        TextView vaccinename,vaccineaddress,vaccinecontact;
        public VaccineViewHolder(@NonNull View itemView) {
            super(itemView);
            vaccinelinear= itemView.findViewById(R.id.vaccineeditL);
            vaccinename = itemView.findViewById(R.id.vaccine_name);
            vaccineaddress = itemView.findViewById(R.id.vaccine_address);
            vaccinecontact = itemView.findViewById(R.id.vaccine_contact);

            vaccineedit = itemView.findViewById(R.id.vaccineediticon);
            vaccinedel = itemView.findViewById(R.id.vaccinedeleteicon);
           // vaccinesms = itemView.findViewById(R.id.vaccinesmsicon);
        }
    }

}
