package AdminAdapterClass;

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

import HelperClass.seniorhelper;

public class SeniorAdapter extends FirebaseRecyclerAdapter<seniorhelper, SeniorAdapter.SeniorViewHolder> {

    public SeniorAdapter(@NonNull FirebaseRecyclerOptions<seniorhelper> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SeniorAdapter.SeniorViewHolder holder, int position, @NonNull seniorhelper model) {
        holder.seniorname.setText(model.getSeniorname());
        holder.senioraddress.setText(model.getSenioraddress());
        holder.seniorcontact.setText(model.getSeniorcontact());

        holder.senioredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.seniorname.getContext())
                      .setContentHolder(new ViewHolder(R.layout.seniordialogcontent))
                      .setExpanded(true, 700)
                      .create();

                dialogPlus.show();

                View seniorview = dialogPlus.getHolderView();

                EditText sname = seniorview.findViewById(R.id.senior_name_edit);
                EditText saddress = seniorview.findViewById(R.id.senior_address_edit);
                EditText scontact = seniorview.findViewById(R.id.senior_contact_edit);

                Button supdate = seniorview.findViewById(R.id.seniorupdate);


                sname.setText(model.getSeniorname());
                saddress.setText(model.getSenioraddress());
                scontact.setText(model.getSeniorcontact());


                supdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();

                        map.put("seniorname",sname.getText().toString());
                        map.put("senioraddress",saddress.getText().toString());
                        map.put("seniorcontact",scontact.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Senior Citizen Registry")
                                .child(getRef(position).getKey()).updateChildren(map)
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
    holder.seniordel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.seniorname.getContext());
            builder.setTitle("Delete Data");
            builder.setMessage("Are you sure you want to delete? it will Delete Permanently");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseDatabase.getInstance().getReference().child("Senior Citizen Registry")
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
    public SeniorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seniorlayoutlist,parent, false);

        return new SeniorViewHolder(view);
    }
    class  SeniorViewHolder extends RecyclerView.ViewHolder{
        ImageView senioredit,seniordel,seniorsms;

        TextView seniorname,senioraddress,seniorcontact;
        public SeniorViewHolder(@NonNull View itemView) {
            super(itemView);


            seniorname = itemView.findViewById(R.id.senior_name);
            senioraddress = itemView.findViewById(R.id.senior_address);
            seniorcontact = itemView.findViewById(R.id.senior_contact);

            senioredit = itemView.findViewById(R.id.seniorediticon);
            seniordel = itemView.findViewById(R.id.seniordeleteicon);
           // seniorsms= itemView.findViewById(R.id.seniorsmsicon);
        }
    }

}
