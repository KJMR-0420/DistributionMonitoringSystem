package Manager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.distributionmonitoring.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import HelperClass.saphelper;

public class sapmanager extends AppCompatActivity {

    private  static final int PICK_IMAGE_REQUEST = 1;

    TextInputEditText fullname,address,contact,occupation,salary;
    TextView url;
    Button sapfile,procceed;
    ImageView sapimage;
    FirebaseDatabase root;
    DatabaseReference ref;
    StorageReference storageRef;
    Uri imageuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sapmanager);

        fullname= findViewById(R.id.sapnameE);
        address = findViewById(R.id.sapddressE);
        contact = findViewById(R.id.sapcontactE);
        occupation=findViewById(R.id.sapoccupationE);
        salary=findViewById(R.id.sapsalaryE);
        url =findViewById(R.id.sapphoto);
        sapimage = findViewById(R.id.sapimage);

        sapfile=findViewById(R.id.sapfilebutton);
        procceed=findViewById(R.id.sapprocceed);

        storageRef = FirebaseStorage.getInstance().getReference("Sap Upload");
        root = FirebaseDatabase.getInstance();
        ref = root.getReference("SAP Registry");

        sapfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            sapfile();
            }
        });

        procceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullname.getText().toString().isEmpty()){
                    fullname.setError("Required Filled");
                    return;
                }
                if (address.getText().toString().isEmpty()){
                    address.setError("Required Filled");
                    return;
                }
                if (contact.getText().toString().isEmpty()){
                    contact.setError("Required Filled");
                    return;
                }
                if (occupation.getText().toString().isEmpty()){
                    occupation.setError("Required Filled");
                    return;
                }
                if (salary.getText().toString().isEmpty()){
                    salary.setError("Required Filled");
                    return;
                }
                uploadsapfile(imageuri);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String path = data.getData().getPath();
            url.setText(path);
        }
        if(requestCode == PICK_IMAGE_REQUEST ){
                imageuri = data.getData();
                sapimage.setImageURI(imageuri);
        }
    }
    private   void sapfile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    private String getFileExtension (Uri uri){
        //get the extension of the photo
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
    private void uploadsapfile(Uri uri){
    if (imageuri !=null){
        StorageReference fileref = storageRef.child(System.currentTimeMillis()+"."+ getFileExtension(imageuri));
        fileref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String fname = fullname.getText().toString();
                        String faddress = address.getText().toString();
                        String fcontact = contact.getText().toString();
                        String foccupation = occupation.getText().toString();
                        String fsalary = salary.getText().toString();
                        String furl = url.getText().toString();
                        saphelper upload = new saphelper(fname,faddress,fcontact,foccupation,fsalary,furl,uri.toString());
                        String  uploadId = ref.push().getKey();
                        ref.push().child("").setValue(upload);
                        Toast.makeText(sapmanager.this,"Upload Successfully",Toast.LENGTH_SHORT).show();

                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(sapmanager.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }else {
        Toast.makeText(sapmanager.this,"No File Selected",Toast.LENGTH_SHORT).show();
    }
    }
}