package com.example.industrial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Activitycommentrating extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    RatingBar ratingBar;
    EditText Editcomment;
    ListView listView;
    ArrayList<UserRaw>arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitycommentrating);
        ratingBar=findViewById(R.id.rating_id);
        listView=findViewById(R.id.listviewrating_id);
        Editcomment=findViewById(R.id.Edit_comment_id);
        String posisionitem=getIntent().getStringExtra("posision");
        ///////////////////////////////savedata
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(posisionitem);
        /////////////////////
        arrayList=new ArrayList<>();
    }
    /// btnAddRating
    public void btn_addrating(View view) {
        if(ratingBar.getRating()==0.0f || Editcomment.getText()==null){
            Toast.makeText(this, "Pleas Enter Rating and Comment", Toast.LENGTH_SHORT).show();
        }else{
            arrayList.clear();
            Map<String,String> map=new HashMap<>();
            map.put("rating",String.valueOf(ratingBar.getRating()));
            map.put("comment",Editcomment.getText().toString());
            myRef.push().setValue( map);
            ratingBar.setRating(0.0f);
            Editcomment.setText("");
            arrayList.clear();
        }

    }
    // btnShowRating
    public void btn_ShowRating(View view) {
        arrayList.clear();
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){

                    String rating=ds.child("rating").getValue().toString();
                    String comented=ds.child("comment").getValue().toString();
                    arrayList.add(new UserRaw(Float.parseFloat(rating),comented));
                }
                RatingAdabter adabter=new RatingAdabter(Activitycommentrating.this,arrayList);
                listView.setAdapter(adabter);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }
}
