package com.example.industrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityAddIndustry extends AppCompatActivity {
    EditText email,pasword;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_industry);
        email=findViewById(R.id.editemail_id_adduser);
        pasword=findViewById(R.id.editpass_id_adduser);
        progressDialog=new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
    }
    // btn اضافه صنيعي جديد
    public void btn_Add(View view) {
        String Email,password;
        Email=email.getText().toString().trim();
        password=pasword.getText().toString().trim();
        if (Email.isEmpty() && password.isEmpty()){
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
        }
        else {
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(Email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(ActivityAddIndustry.this, "saved Datd", Toast.LENGTH_SHORT).show();
                                email.setText("");
                                pasword.setText("");
                                progressDialog.dismiss();

                            } else {
                                // If sign in fails, display a message to the user.
                                progressDialog.dismiss();
                                Toast.makeText(ActivityAddIndustry.this, task.getException().toString(), Toast.LENGTH_SHORT).show();


                            }

                            // ...
                        }
                    });
        }


    }
}
