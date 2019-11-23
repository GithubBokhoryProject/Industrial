package com.example.industrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class ActivityMyHome extends AppCompatActivity {
    EditText name,phone,mainlocation,anotherlocation;
    ImageView imageView;
    Spinner myspinner;
    ProgressDialog progressDialog;
    private StorageReference mStorageRef;
    Uri img_Uri;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String sellected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home);
        name=findViewById(R.id.edit_name_id);
        phone=findViewById(R.id.edit_phone_id);
        myspinner=findViewById(R.id.myspinner);
        mainlocation=findViewById(R.id.edit_locationmain_id);
        anotherlocation=findViewById(R.id.edit_anotherlocation_id);
        imageView=findViewById(R.id.imgview_id);
        progressDialog=new ProgressDialog(this);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        ///////////////////////
        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posision, long id) {
                 sellected=adapterView.getItemAtPosition(posision).toString();
                myRef = database.getReference(sellected);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    // on click image view
    public void imageviewronclick(View view){

        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            this.img_Uri = data.getData();
            imageView.setImageURI(img_Uri);

        }

    }
    // btn add industry
    public void btn_addindustry(View view) {

        if (img_Uri!=null){
            progressDialog.show();

            final StorageReference  riversRef=mStorageRef.child("photos/"+System.currentTimeMillis());
            riversRef.putFile(img_Uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Map<String,String> map=new HashMap<>();
                                    map.put("PhotoURL",uri.toString());
                                    map.put("name",name.getText().toString());
                                    map.put("phone",phone.getText().toString());
                                    map.put("specialize",sellected);
                                    map.put(" mainlocation", mainlocation.getText().toString());
                                    map.put(" anotherlocation", anotherlocation.getText().toString());
                                    myRef.push().setValue(map);
                                    Toast.makeText(ActivityMyHome.this, "Upload Success", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            });


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            Toast.makeText(ActivityMyHome.this, "Exception"+ exception.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            // ...
                        }
                    });

        }
        else{
            Toast.makeText(this, "Pleas Select photo", Toast.LENGTH_SHORT).show();
        }
    }
}
