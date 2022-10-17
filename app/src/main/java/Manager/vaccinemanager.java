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

import HelperClass.vaccinehelper;

public class vaccinemanager extends AppCompatActivity {
    private  static final int PICK_IMAGE_REQUEST = 1;
TextInputEditText vaccinename,vaccineaddress,vaccinecontact;
TextView vaccinephoto;
ImageView freeimage;
Button procceed,choosephoto;
StorageReference storageRef;
FirebaseDatabase root;
DatabaseReference ref;
Uri imageuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccinemanager);

        vaccinename = findViewById(R.id.vaccinenameE);
        vaccineaddress=findViewById(R.id.vaccineaddressE);
        vaccinecontact=findViewById(R.id.vaccinecontactE);
        vaccinephoto = findViewById(R.id.vaccinephoto);
        freeimage = findViewById(R.id.vaccineimage);
        procceed = findViewById(R.id.vaccineprocceed);
        choosephoto= findViewById(R.id.vaccinefilebutton);

        storageRef = FirebaseStorage.getInstance().getReference("Vaccine Upload");
        root = FirebaseDatabase.getInstance();
        ref = root.getReference("Vaccine Registry");

        choosephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaccinefiles();
            }
        });
        procceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (vaccinename.getText().toString().isEmpty()){
                    vaccinename.setError("Required Filled");
                    return;
                }
                if (vaccineaddress.getText().toString().isEmpty()){
                    vaccineaddress.setError("Required Filled");
                    return;
                }
                if (vaccinecontact.getText().toString().isEmpty()){
                    vaccinecontact.setError("Required Filled");
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
            vaccinephoto.setText(path);
        }
        if(requestCode == PICK_IMAGE_REQUEST ){
            imageuri = data.getData();
            freeimage.setImageURI(imageuri);
        }
    }
    private void vaccinefiles(){
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
                            String fname = vaccinename.getText().toString();
                            String faddress = vaccineaddress.getText().toString();
                            String fcontact = vaccinecontact.getText().toString();
                            String furl = vaccinephoto.getText().toString();
                            vaccinehelper upload = new vaccinehelper(fname,faddress,fcontact,furl,uri.toString());
                            String  uploadId = ref.push().getKey();
                            ref.push().child("").setValue(upload);
                            Toast.makeText(vaccinemanager.this,"Upload Successfully",Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(vaccinemanager.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(vaccinemanager.this,"No File Selected",Toast.LENGTH_SHORT).show();
        }
    }
    }
