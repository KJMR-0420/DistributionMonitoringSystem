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

import HelperClass.soloparenthelper;

public class SoloparentAdapter extends FirebaseRecyclerAdapter <soloparenthelper,SoloparentAdapter.SoloparentViewHolder> {

    public SoloparentAdapter(@NonNull FirebaseRecyclerOptions<soloparenthelper> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SoloparentViewHolder holder, int position, @NonNull soloparenthelper model) {
            holder.sololinear.setVisibility(View.GONE);
                holder.soloparentname.setText(model.getSoloname());
        holder.soloparentaddress.setText(model.getSoloaddress());
        holder.soloparentcontact.setText(model.getSolocontact());
//
//
//        holder.soloparentedit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.soloparentname.getContext())
//                        .setContentHolder(new ViewHolder(R.layout.soloparentdialogcontent))
//                        .setExpanded(true, 700)
//                        .create();
//
//                dialogPlus.show();
//
//                View soloparentview = dialogPlus.getHolderView();
//
//                EditText spname = soloparentview.findViewById(R.id.soloparent_name_edit);
//                EditText spaddress = soloparentview.findViewById(R.id.soloparent_address_edit);
//                EditText spcontact = soloparentview.findViewById(R.id.soloparent_contact_edit);
//
//                Button spupdate = soloparentview.findViewById(R.id.seniorupdate);
//
//
//                spname.setText(model.getSoloname());
//                spaddress.setText(model.getSoloaddress());
//                spcontact.setText(model.getSolocontact());
//
//
//                spupdate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Map<String, Object> map = new HashMap<>();
//
//                        map.put("soloname",spname.getText().toString());
//                        map.put("soloaddress",spaddress.getText().toString());
//                        map.put("solocontact",spcontact.getText().toString());
//
//                        FirebaseDatabase.getInstance().getReference().child("Solo Parents Registry")
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
//        holder.soloparentdel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(holder.soloparentname.getContext());
//                builder.setTitle("Delete Data");
//                builder.setMessage("Are you sure you want to delete? it will Delete Permanently");
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        FirebaseDatabase.getInstance().getReference().child("Solo Parents Registry")
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
    public SoloparentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.soloparentlayoutlist,parent, false);

        return new SoloparentViewHolder(view);
    }

    class SoloparentViewHolder extends RecyclerView.ViewHolder{
        ImageView soloparentedit,soloparentdel,soloparentsms;
        TextView soloparentname,soloparentaddress,soloparentcontact;
        LinearLayout sololinear;
        public SoloparentViewHolder(@NonNull View itemView) {
            super(itemView);
            sololinear = itemView.findViewById(R.id.soloparenteditL);
            soloparentname = itemView.findViewById(R.id.soloparent_name);
            soloparentaddress = itemView.findViewById(R.id.soloparent_address);
            soloparentaddress = itemView.findViewById(R.id.soloparent_contact);

            soloparentedit = itemView.findViewById(R.id.soloparentediticon);
            soloparentdel = itemView.findViewById(R.id.soloparentdeleteicon);
           // soloparentsms = itemView.findViewById(R.id.soloparentsmsicon);
        }
    }

}
