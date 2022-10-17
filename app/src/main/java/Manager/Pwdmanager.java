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

import HelperClass.pwdhelper;

public class Pwdmanager extends AppCompatActivity {
    private  static final int PICK_IMAGE_REQUEST = 1;
TextInputEditText pwdname,pwdaddress,pwdcontact;
TextView pwdphotos;
Button procceed,pwdfile;
ImageView pwdimage;
FirebaseDatabase root;
DatabaseReference ref;
StorageReference storageRef;
Uri imageuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwdmanager);

        pwdname = findViewById(R.id.pwdNameE);
        pwdaddress = findViewById(R.id.pwdAddressE);
        pwdcontact = findViewById(R.id.pwdContactE);
        pwdphotos = findViewById(R.id.pwdphoto);
        pwdimage = findViewById(R.id.pwdimage);
        procceed = findViewById(R.id.pwdProceed);
        pwdfile = findViewById(R.id.pwdFile);

        storageRef = FirebaseStorage.getInstance().getReference("PWD Upload");
        root = FirebaseDatabase.getInstance();
        ref = root.getReference("PWD Registry");

        pwdfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        pwdfiles();
            }
        });
        procceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pwdname.getText().toString().isEmpty()){
                    pwdname.setError("Required Filled");
                    return;
                }
                if (pwdaddress.getText().toString().isEmpty()){
                    pwdaddress.setError("Required Filled");
                    return;
                }
                if (pwdcontact.getText().toString().isEmpty()){
                    pwdcontact.setError("Required Filled");
                    return;
                }

                uploadfile(imageuri);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String path = data.getData().getPath();
            pwdphotos.setText(path);
        }
        if(requestCode == PICK_IMAGE_REQUEST ){
            imageuri = data.getData();
            pwdimage.setImageURI(imageuri);
        }
    }
    private void pwdfiles(){
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
    private  void uploadfile(Uri uri){
        if (imageuri !=null){
            StorageReference fileref = storageRef.child(System.currentTimeMillis()+"."+ getFileExtension(imageuri));
            fileref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String fname = pwdname.getText().toString();
                            String faddress = pwdaddress.getText().toString();
                            String fcontact = pwdcontact.getText().toString();
                            String furl = pwdphotos.getText().toString();

                            pwdhelper upload = new pwdhelper(fname,faddress,fcontact,furl,uri.toString());
                            String  uploadId = ref.push().getKey();
                            ref.push().child("").setValue(upload);
                            Toast.makeText(Pwdmanager.this,"Upload Successfully",Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Pwdmanager.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(Pwdmanager.this,"No File Selected",Toast.LENGTH_SHORT).show();
        }
    }
    }
