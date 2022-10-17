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

import HelperClass.pwdhelper;

public class PwdAdapter extends FirebaseRecyclerAdapter<pwdhelper,PwdAdapter.PwdViewHolder> {

    public PwdAdapter(@NonNull FirebaseRecyclerOptions<pwdhelper> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PwdViewHolder holder, int position, @NonNull pwdhelper model) {
        holder.pwdlinear.setVisibility(View.GONE);
holder.pwdname.setText(model.getPwdname());
holder.pwdaddress.setText(model.getPwdaddress());
holder.pwdcontact.setText(model.getPwdcontact());

//holder.pwdedit.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        final DialogPlus dialogPlus = DialogPlus.newDialog(holder.pwdname.getContext())
//                .setContentHolder(new ViewHolder(R.layout.pwddialogcontent))
//                .setExpanded(true, 700)
//                .create();
//
//        dialogPlus.show();
//
//        View pwdview = dialogPlus.getHolderView();
//
//        EditText pwdName = pwdview.findViewById(R.id.pwd_name_edit);
//        EditText pwdAddress = pwdview.findViewById(R.id.pwd_address_edit);
//        EditText pwdContact = pwdview.findViewById(R.id.pwd_contact_edit);
//
//        Button pwdupdate = pwdview.findViewById(R.id.pwdupdate);
//
//
//        pwdName.setText(model.getPwdname());
//        pwdAddress.setText(model.getPwdaddress());
//        pwdContact.setText(model.getPwdcontact());
//        dialogPlus.show();
//
//        pwdupdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Map<String, Object> map = new HashMap<>();
//
//                map.put("pwdname",pwdName.getText().toString());
//                map.put("pwdaddress",pwdAddress.getText().toString());
//                map.put("pwdcontact",pwdContact.getText().toString());
//
//                FirebaseDatabase.getInstance().getReference().child("PWD Registry")
//                        .child(getRef(position).getKey()).updateChildren(map)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                dialogPlus.dismiss();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        dialogPlus.dismiss();
//                    }
//                });
//            }
//        });
//    }
//});
//holder.pwddel.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(holder.pwdname.getContext());
//        builder.setTitle("Delete Data");
//        builder.setMessage("Are you sure you want to delete? it will Delete Permanently");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                FirebaseDatabase.getInstance().getReference().child("PWD Registry")
//                        .child(getRef(position).getKey()).removeValue();
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        builder.show();
//    }
//});
    }

    @NonNull
    @Override
    public PwdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pwdlayoutlist,parent,false);

       return  new PwdViewHolder(view);
    }

    class PwdViewHolder extends RecyclerView.ViewHolder{

        ImageView pwdedit,pwddel,pwdsms;

        TextView pwdname,pwdaddress,pwdcontact;
        LinearLayout pwdlinear;
        public PwdViewHolder(@NonNull View itemView) {
            super(itemView);
            pwdlinear = itemView.findViewById(R.id.pwdeditL);
            pwdname = itemView.findViewById(R.id.pwd_name);
            pwdaddress = itemView.findViewById(R.id.pwd_address);
            pwdcontact = itemView.findViewById(R.id.pwd_contact);
            pwdedit = itemView.findViewById(R.id.pwdediticon);
            pwddel = itemView.findViewById(R.id.pwddeleteicon);
          // pwdsms = itemView.findViewById(R.id.pwdsmsicon);

        }
    }
}
