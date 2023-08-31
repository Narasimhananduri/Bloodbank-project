package com.example.bloodbank;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;



public class UserAdapter extends ArrayAdapter<Users> {
    public UserAdapter(Context context, List<Users> object){
        super(context,0, object);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView =  ((Activity)getContext()).getLayoutInflater().inflate(R.layout.user_item,parent,false);
        }

        TextView nameTextView = (TextView) convertView.findViewById(R.id.user_name);
        TextView ageTextView = (TextView) convertView.findViewById(R.id.user_age);
        TextView phoneTextView = (TextView) convertView.findViewById(R.id.user_phone);
        TextView bloodGroupTextView = (TextView) convertView.findViewById(R.id.user_bloodgroup);
        TextView genderTextView = (TextView) convertView.findViewById(R.id.user_gender);
        TextView stateTextView = (TextView) convertView.findViewById(R.id.user_state);
        TextView cityTextView = (TextView) convertView.findViewById(R.id.user_city);

        Users user = getItem(position);

        nameTextView.setText(user.getName());
        ageTextView.setText(user.getAge());
        phoneTextView.setText(user.getPhone());
        bloodGroupTextView.setText(user.getBloodGroup());
        genderTextView.setText(user.getGender());
        stateTextView.setText(user.getState());
        cityTextView.setText(user.getCity());
        return convertView;
    }

}