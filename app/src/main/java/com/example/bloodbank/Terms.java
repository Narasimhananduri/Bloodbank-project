package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Terms extends AppCompatActivity {

    Button agree, disagree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        agree = findViewById(R.id.agree);
        disagree = findViewById(R.id.disagree);

        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Terms.this, Register.class);
                startActivity(intent);
            }
        });

        disagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Terms.this, "Sorry!! You cannot register without accepting our terms and conditions", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Terms.this, home.class);
                startActivity(intent);
            }
        });

    }
}