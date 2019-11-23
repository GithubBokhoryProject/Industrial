package com.example.industrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityUser extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase database ;
    DatabaseReference myRef;
    ArrayList<user> arrayList;
    Spinner myspinner;
    String sellected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        listView=findViewById(R.id.listview_id);
        myspinner=findViewById(R.id.myspinner);
        arrayList=new ArrayList<>();
        database= FirebaseDatabase.getInstance();
       /////////////////////////////
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posision, long l) {
                String Posisionitem=String.valueOf(posision);
                Intent intent=new Intent(ActivityUser.this,Activitycommentrating.class);
                intent.putExtra("posision",Posisionitem+sellected);
                startActivity(intent);
            }
        });
        /////////////////////////////
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

        ////////---------------


    }
    // btn search
    public void btn_Search(View view) {
        arrayList.clear();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    String Name=ds.child("name").getValue().toString();
                    String Phone=ds.child("phone").getValue().toString();
                    String PHOTO=ds.child("PhotoURL").getValue().toString();
                    String specialize=ds.child("specialize").getValue().toString();
                    String  mainlocation=ds.child(" mainlocation").getValue().toString();
                    String  anotherlocation=ds.child(" anotherlocation").getValue().toString();
                    arrayList.add(new user(Name,Phone,specialize,mainlocation,anotherlocation,PHOTO));
                }
                userAdabter adabter=new userAdabter(ActivityUser.this,arrayList);
                listView.setAdapter(adabter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ActivityUser.this, "Error="+databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
