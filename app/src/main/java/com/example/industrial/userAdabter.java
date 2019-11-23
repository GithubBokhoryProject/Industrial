package com.example.industrial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class userAdabter extends ArrayAdapter<user> {
    Context context;
    public userAdabter(Context context, ArrayList<user> objects) {
        super(context, 0, objects);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.raw,parent,false);
        TextView name=convertView.findViewById(R.id.nameview_id);
        TextView phone=convertView.findViewById(R.id.phoneview_id);
        TextView specialize=convertView.findViewById(R.id.specializeview_id);
        TextView mainlocation=convertView.findViewById(R.id.mainlocationview_id);
        TextView anotherlocation=convertView.findViewById(R.id.anotherlocationview_id);
        ImageView imageView=convertView.findViewById(R.id.img_userview);
        user convertuser=getItem(position);
        name.setText(convertuser.getName());
        phone.setText(convertuser.getPhone());
        specialize.setText(convertuser.getSpecialize());
        mainlocation.setText(convertuser.getMainlocation());
        anotherlocation.setText(convertuser.getAnotherlocation());
        Picasso.get().load(convertuser.getImageindustry()).into(imageView);
        return convertView;
    }
}
