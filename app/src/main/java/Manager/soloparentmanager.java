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

import HelperClass.soloparenthelper;

public class soloparentmanager extends AppCompatActivity {
    private  static final int PICK_IMAGE_REQUEST = 1;
TextInputEditText soloname,soloaddress,solocontact;
TextView solophoto;
FirebaseDatabase root;
DatabaseReference ref;
StorageReference storageRef;
ImageView soloimage;
Button procceed,choosefile;
Uri imageuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soloparentmanager);

        soloname = findViewById(R.id.soloParentsNameE);
        soloaddress = findViewById(R.id.soloParentsAddressE);
        solocontact = findViewById(R.id.soloContactE);
        solophoto = findViewById(R.id.solophoto);
        soloimage = findViewById(R.id.soloimage);
        procceed = findViewById(R.id.soloParentsProceed);
        choosefile = findViewById(R.id.soloParentsFile);

        storageRef = FirebaseStorage.getInstance().getReference("Solo Parents upload");
        root = FirebaseDatabase.getInstance();
        ref = root.getReference("Solo Parents Registry");

        choosefile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solofiles();
            }
        });

        procceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soloname.getText().toString().isEmpty()){
                    soloname.setError("Required Filled");
                    return;
                }
                if (soloaddress.getText().toString().isEmpty()){
                    soloaddress.setError("Required Filled");
                    return;
                }
                if (solocontact.getText().toString().isEmpty()){
                    solocontact.setError("Required Filled");
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
            solophoto.setText(path);
        }
        if(requestCode == PICK_IMAGE_REQUEST ){
            imageuri = data.getData();
            soloimage.setImageURI(imageuri);
        }
    }
    private  void solofiles(){
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
    private void uploadfile(Uri uri){
        if (imageuri !=null){
            StorageReference fileref = storageRef.child(System.currentTimeMillis()+"."+ getFileExtension(imageuri));
            fileref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String fname = soloname.getText().toString();
                            String faddress = soloaddress.getText().toString();
                            String fcontact = solocontact.getText().toString();
                            String furl = solophoto.getText().toString();
                            soloparenthelper upload = new soloparenthelper(fname,faddress,fcontact,furl,uri.toString());
                            String  uploadId = ref.push().getKey();
                            ref.push().child("").setValue(upload);
                            Toast.makeText(soloparentmanager.this,"Upload Successfully",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(soloparentmanager.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(soloparentmanager.this,"No File Selected",Toast.LENGTH_SHORT).show();
        }
    }
    }
