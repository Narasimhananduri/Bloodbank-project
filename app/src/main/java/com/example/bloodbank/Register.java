package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    String TAG = "hello";
    EditText name, age, phnno, state, city;
    Spinner gender, bldgrp;
    TextView result;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        gender = (Spinner)findViewById(R.id.gender);
        bldgrp = (Spinner)findViewById(R.id.blood_group);
        phnno = findViewById(R.id.phone_number);
        state = findViewById(R.id.state);
        city = findViewById(R.id.city);
        submit = findViewById(R.id.button);


        //get the spinner from the xml.
        Spinner gender_dropdown = findViewById(R.id.gender);
        String[] gender_items = new String[]{"Male", "Female", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, gender_items);
        gender_dropdown.setAdapter(adapter);

        //get the spinner from the xml.
        Spinner blood_group_dropdown = findViewById(R.id.blood_group);
        String[] blood_group_items = new String[]{"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
        ArrayAdapter<String> blood_group_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, blood_group_items);
        blood_group_dropdown.setAdapter(blood_group_adapter);





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String ageTXT = age.getText().toString();
                String phnTXT = phnno.getText().toString();
                String stateTXT = state.getText().toString();
                String cityTXT = city.getText().toString();
                String bldTXT = bldgrp.getSelectedItem().toString();
                String genderTXT = gender.getSelectedItem().toString();

                if (nameTXT.matches("") && ageTXT.matches("") && phnTXT.matches("") && stateTXT.matches("") && cityTXT.matches("") && bldTXT.matches("") && genderTXT.matches("")) {
                    Toast.makeText(Register.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    // Create a new user with a first and last name
                    Map<String, Object> user = new HashMap<>();
                    user.put("name", nameTXT);
                    user.put("age", ageTXT);
                    user.put("state", stateTXT);
                    user.put("gender", genderTXT);
                    user.put("bloodgroup", bldTXT);
                    user.put("city", cityTXT);
                    user.put("phone", phnTXT);
                    // Add a new document with a generated ID
                    db.collection("users")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(Register.this, "Donor added successfully", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                    Intent intent = new Intent(Register.this, home.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Register.this, "Something error", Toast.LENGTH_SHORT).show();
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });
                }
            }
        });


    }
}