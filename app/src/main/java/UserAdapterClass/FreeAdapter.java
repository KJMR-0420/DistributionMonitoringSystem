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

import HelperClass.freehelper;

public class FreeAdapter extends FirebaseRecyclerAdapter<freehelper, FreeAdapter.FreeViewHolder> {

    public FreeAdapter(@NonNull FirebaseRecyclerOptions<freehelper> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FreeViewHolder holder, int position, @NonNull freehelper model) {
        holder.freelinear.setVisibility(View.GONE);
   holder.pname.setText(model.getMilkparentsname());
        holder.cname.setText(model.getMilkchildname());
        holder.cage.setText(model.getMilkchildage());
        holder.cbrand.setText(model.getMilkbrand());
        holder.caddress.setText(model.getMilkaddress());
        holder.ccontact.setText(model.getMilkcontact());
//        holder.freesms.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.pname.getContext())
//                        .setContentHolder(new ViewHolder(R.layout.smsdialogcontent))
//                        .setExpanded(true, 700)
//                        .create();
//                dialogPlus.show();
//            }
//        });
//        holder.freeedit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.pname.getContext())
//                .setContentHolder(new ViewHolder(R.layout.freedialogcontent))
//                .setExpanded(true, 700)
//                .create();
//
//                dialogPlus.show();
//
//                View freeview = dialogPlus.getHolderView();
//                EditText pname = freeview.findViewById(R.id.free_name_edit);
//                EditText cname = freeview.findViewById(R.id.free_childname_edit);
//                EditText cage = freeview.findViewById(R.id.free_Childage__edit);
//                EditText cbrand = freeview.findViewById(R.id.free_milkbrand_edit);
//                EditText caddress = freeview.findViewById(R.id.free_address_edit);
//                EditText ccontact = freeview.findViewById(R.id.free_contact_edit);
//                Button freeupdate = freeview.findViewById(R.id.freeupdate);
//
//                pname.setText(model.getMilkparentsname());
//                cname.setText(model.getMilkchildname());
//                cage.setText(model.getMilkchildage());
//                cbrand.setText(model.getMilkbrand());
//                caddress.setText(model.getMilkaddress());
//                ccontact.setText(model.getMilkcontact());
//
//                dialogPlus.show();
//
//                freeupdate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Map<String ,Object> map = new HashMap<>();
//                        map.put("milkparentsname",pname.getText().toString());
//                        map.put("milkchildname",cname.getText().toString());
//                        map.put("milkchildage",cage.getText().toString());
//                        map.put("milkbrand",cbrand.getText().toString());
//                        map.put("milkaddress",caddress.getText().toString());
//                        map.put("milkcontact",ccontact.getText().toString());
//
//                        FirebaseDatabase.getInstance().getReference().child("Free Milk Registry")
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
//
//            }
//        });
//
//        holder.freedel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(holder.pname.getContext());
//                builder.setTitle("Delete Data");
//                builder.setMessage("Are you sure you want to delete? it will Delete Permanently");
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        FirebaseDatabase.getInstance().getReference().child("Free Milk Registry")
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
    public FreeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.freelayoutlist,parent,false);

        return new FreeViewHolder(view);
    }

    class FreeViewHolder extends RecyclerView.ViewHolder{

        ImageView freeedit,freedel,freesms;
        TextView pname,cname,cage,cbrand,caddress,ccontact;
        LinearLayout freelinear;
        public FreeViewHolder(@NonNull View itemView) {
            super(itemView);
            freelinear =itemView.findViewById(R.id.freeeditL);
            pname = itemView.findViewById(R.id.free_parentname);
            cname = itemView.findViewById(R.id.free_childname);
            cage = itemView.findViewById(R.id.free_childage);
            cbrand = itemView.findViewById(R.id.free_milkbrand);
            caddress = itemView.findViewById(R.id.free_address);
            ccontact = itemView.findViewById(R.id.free_contact);
            freeedit= itemView.findViewById(R.id.freeediticon);
            freedel = itemView.findViewById(R.id.freedeleteicon);


          //  freesms = itemView.findViewById(R.id.freesmsicon);
        }
    }
}
