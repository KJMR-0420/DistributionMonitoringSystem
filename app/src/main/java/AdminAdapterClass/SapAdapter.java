package AdminAdapterClass;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.distributionmonitoring.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import HelperClass.saphelper;

public class SapAdapter extends FirebaseRecyclerAdapter <saphelper,SapAdapter.SapViewholder>{

    public SapAdapter(@NonNull FirebaseRecyclerOptions<saphelper> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SapViewholder holder, @SuppressLint("RecyclerView") int position, @NonNull saphelper model) {

        holder.name.setText(model.getSapname());
        holder.address.setText(model.getSapaddress());
        holder.contact.setText(model.getSapcontact());
        holder.occupation.setText(model.getSapoccupation());
        holder.salary.setText(model.getSapsalary());

        //Update Button
        holder.sapedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final DialogPlus dialogPlus = DialogPlus.newDialog(holder.name.getContext())
                       .setContentHolder(new ViewHolder(R.layout.sapdialogcontent))
                       .setExpanded(true, 700)
                       .create();

               dialogPlus.show();

               View sapview =dialogPlus.getHolderView();
               EditText sapname = sapview.findViewById(R.id.sap_name_edit);
               EditText sapaddress = sapview.findViewById(R.id.sap_address_edit);
               EditText sapcontact = sapview.findViewById(R.id.sap_contact_edit);
                EditText sapoccupation = sapview.findViewById(R.id.sap_occupation_edit);
                EditText sapsalary = sapview.findViewById(R.id.sap_salary_edit);
                Button sapupdate = sapview.findViewById(R.id.sapupdate);

                sapname.setText(model.getSapname());
                sapaddress.setText(model.getSapaddress());
                sapcontact.setText(model.getSapcontact());
                sapoccupation.setText(model.getSapoccupation());
                sapsalary.setText(model.getSapsalary());

                dialogPlus.show();

                sapupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String , Object> map = new HashMap<>();
                        map.put("sapname",sapname.getText().toString());
                        map.put("sapaddress",sapaddress.getText().toString());
                        map.put("sapcontact",sapcontact.getText().toString());
                        map.put("sapoccupation",sapoccupation.getText().toString());
                        map.put("sapsalary",sapsalary.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("SAP Registry")
                                .child((getRef(position).getKey())).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                    dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialogPlus.dismiss();
                            }
                        });
                    }
                });

            }
        });

        holder.sapdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Delete Data");
                builder.setMessage("Are you sure you want to delete? it will Delete Permanently");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("SAP Registry")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public SapViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.saplayoutlist,parent,false);
        return new SapViewholder(view);
    }

    class SapViewholder extends RecyclerView.ViewHolder{


        ImageView sapedit,sapdel,sapsms;
        TextView name,address,contact,occupation,salary;
        public SapViewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nametext);
            address = itemView.findViewById(R.id.addresstext);
            contact = itemView.findViewById(R.id.contacttxt);
            occupation = itemView.findViewById(R.id.occupationtxt);
            salary = itemView.findViewById(R.id.salarytxt);

            //Button For update and Delete
            sapedit = itemView.findViewById(R.id.sapediticon);
            sapdel = itemView.findViewById(R.id.sapdeleteicon);
           // sapsms = itemView.findViewById(R.id.sapsmsicon);

        }
    }
}