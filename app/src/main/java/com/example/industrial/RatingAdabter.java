package com.example.industrial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RatingAdabter extends ArrayAdapter<UserRaw> {
        Context context;
    public RatingAdabter(Context context, ArrayList<UserRaw> objects) {
        super(context, 0, objects);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.raw_rating,parent,false);
        RatingBar ratingBar=convertView.findViewById(R.id.rating_raw_id);
        TextView comment=convertView.findViewById(R.id.comment_id);
        UserRaw  convertuserraw=getItem(position);
        ratingBar.setRating(convertuserraw.getRating());
        comment.setText(convertuserraw.getComment());
        return convertView;
    }
}
