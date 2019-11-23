package com.example.industrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityIndustry extends AppCompatActivity {
        EditText Email,Pasword;
        private FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industry);
        Email=findViewById(R.id.email_id);
        Pasword=findViewById(R.id.pass_id);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Waiting");
        progressDialog.setMessage("Pleas wait");

    }

    // btn تسجيل الدخول
    public void btn_Logain(View view) {
        String email,password;
        email=Email.getText().toString().trim();
        password=Pasword.getText().toString().trim();
        if (email.isEmpty() && password.isEmpty()){
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
        }
        else {
            progressDialog.show();
            mAuth.signInWithEmailAndPassword( email, password)
                    .addOnCompleteListener(ActivityIndustry.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Intent intent=new Intent(ActivityIndustry.this,ActivityMyHome.class);
                                        startActivity(intent);
                                        progressDialog.dismiss();
                                    }
                                });

                            } else {
                                Toast.makeText(ActivityIndustry.this, "EMail or password is not Successful", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                            // ...
                        }
                    });
        }

    }
    //btn تسجيل صنيعي جديد
    public void btn_Register(View view){
        Intent intent=new Intent(this,ActivityAddIndustry.class);
        startActivity(intent);

    }
}
