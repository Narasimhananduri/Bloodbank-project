package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {
    private SearchView search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Find Donors");


        search = (SearchView) findViewById(R.id.search);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("users").whereEqualTo("bloodgroup", query.toUpperCase()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<Users> mMissionsList = new ArrayList<>();
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()) {
                                Users miss = document.toObject(Users.class);
                                mMissionsList.add(miss);
                            }
                            if(mMissionsList.isEmpty()){
                                Toast.makeText(Search.this, "No donors found with this group", Toast.LENGTH_SHORT).show();
                            } else {
                                ListView mMissionsListView = (ListView) findViewById(R.id.list_view);
                                UserAdapter mMissionAdapter = new UserAdapter(Search.this, mMissionsList);
                                mMissionsListView.setAdapter(mMissionAdapter);
                            }
                        } else {
                            Log.d("MissionActivity", "Error getting documents: ", task.getException());
                        }
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//              if (searchView.isExpanded() && TextUtils.isEmpty(newText)) {
//                callSearch(newText);
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("users").whereEqualTo("bloodgroup", newText.toUpperCase()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<Users> mMissionsList = new ArrayList<>();
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()) {
                                Users miss = document.toObject(Users.class);
                                mMissionsList.add(miss);
                            }

                                ListView mMissionsListView = (ListView) findViewById(R.id.list_view);
                                UserAdapter mMissionAdapter = new UserAdapter(Search.this, mMissionsList);
                                mMissionsListView.setAdapter(mMissionAdapter);

                        } else {
                            Log.d("MissionActivity", "Error getting documents: ", task.getException());
                        }
                    }
                });
//              }
                return true;
            }

            public void callSearch(String query) {
                //Do searching
            }

        });

    }
}