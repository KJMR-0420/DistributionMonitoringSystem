package Manager;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.distributionmonitoring.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import HelperClass.seniorhelper;

public class SeniorCitizenManager extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    TextInputEditText seniorname, senioraddress, seniorcontact;
    TextView seniorphoto;
    Button procceed, choosefile;
    ImageView seniorimage;
    FirebaseDatabase root;
    DatabaseReference ref;
    StorageReference storageRef;
    Uri imageuri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_citizen_manager);

        seniorname = findViewById(R.id.seniorNameE);
        senioraddress = findViewById(R.id.SeniorAddressE);
        seniorcontact = findViewById(R.id.SeniorContactE);
        seniorphoto = findViewById(R.id.seniorphoto);
        seniorimage = findViewById(R.id.seniorimage);
        procceed = findViewById(R.id.SeniorProceed);
        choosefile = findViewById(R.id.SeniorFile);

        storageRef = FirebaseStorage.getInstance().getReference("Senior Upload");
        root = FirebaseDatabase.getInstance();
        ref = root.getReference("Senior Citizen Registry");

        choosefile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seniorfiles();
            }
        });
        procceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (seniorname.getText().toString().isEmpty()) {
                    seniorname.setError("Required Filled");
                    return;
                }
                if (senioraddress.getText().toString().isEmpty()) {
                    senioraddress.setError("Required Filled");
                    return;
                }
                if (seniorcontact.getText().toString().isEmpty()) {
                    seniorcontact.setError("Required Filled");
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
            seniorphoto.setText(path);
        }
        if (requestCode == PICK_IMAGE_REQUEST) {
            imageuri = data.getData();
            seniorimage.setImageURI(imageuri);
        }
    }

    private void seniorfiles() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private String getFileExtension(Uri uri) {
        //get the extension of the photo
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadfile(Uri uri) {
        if (imageuri != null) {
            StorageReference fileref = storageRef.child(System.currentTimeMillis() + "." + getFileExtension(imageuri));
            fileref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String fname = seniorname.getText().toString();
                            String faddress = senioraddress.getText().toString();
                            String fcontact = seniorcontact.getText().toString();
                            String furl = seniorphoto.getText().toString();

                            seniorhelper upload = new seniorhelper(fname, faddress, fcontact, furl, uri.toString());
                            String uploadId = ref.push().getKey();
                            ref.push().child("").setValue(upload);
                            Toast.makeText(SeniorCitizenManager.this, "Upload Successfully", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SeniorCitizenManager.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(SeniorCitizenManager.this, "No File Selected", Toast.LENGTH_SHORT).show();
        }
    }
}
