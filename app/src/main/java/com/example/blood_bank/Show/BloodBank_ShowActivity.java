package com.example.blood_bank.Show;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.blood_bank.Adapters.bloodbankAdapter;
import com.example.blood_bank.R;
import com.example.blood_bank.Register.UserLoginActivity;
import com.example.blood_bank.bloodbankClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BloodBank_ShowActivity extends AppCompatActivity {
    DatabaseReference DBref;
    RecyclerView recycle;
    bloodbankAdapter adapter;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    ArrayList<bloodbankClass> bloodbankArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_show);
        AppCompatButton logout = findViewById(R.id.buttonlogOUtt);
        DBref = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Intent i = new Intent(BloodBank_ShowActivity.this, UserLoginActivity.class);
            startActivity(i);
            finish();
        }
        else {
            logout.setText("LOG OUT    :      "+ currentUser.getEmail());
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(BloodBank_ShowActivity.this,UserLoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        recycle = findViewById(R.id.recyclerView);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        //        RecyclerView.setHasFixedSize(true);
        bloodbankArray = new ArrayList<>();
        readData();
    }
    private void readData() {
        DBref.child("bloodbank").orderByChild("bloodbank").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bloodbankArray.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    bloodbankClass users = dataSnapshot.getValue(bloodbankClass.class);
                    bloodbankArray.add(users);
                }
                Log.e("abc",bloodbankArray.size()+"");
                adapter = new bloodbankAdapter(BloodBank_ShowActivity.this,bloodbankArray);
                recycle.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}

