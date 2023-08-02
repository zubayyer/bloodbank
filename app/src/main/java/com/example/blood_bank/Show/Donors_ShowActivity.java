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

import com.example.blood_bank.Adapters.donorAdapter;
import com.example.blood_bank.Adapters.donorAdapterr;
import com.example.blood_bank.R;
import com.example.blood_bank.Register.UserLoginActivity;
import com.example.blood_bank.donorClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Donors_ShowActivity extends AppCompatActivity {
    DatabaseReference DBref;
    RecyclerView recycle;
    donorAdapter adapter;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    ArrayList<donorClass> donorArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors_show);
        AppCompatButton logout = findViewById(R.id.buttonlogOUtt);
        DBref = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Intent i = new Intent(Donors_ShowActivity.this, UserLoginActivity.class);
            startActivity(i);
            finish();
        } else {
            logout.setText("LOG OUT    :      "+ currentUser.getEmail());
        }

        recycle = findViewById(R.id.recyclerViewDonor);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        //        RecyclerView.setHasFixedSize(true);
        donorArray = new ArrayList<>();
        readData();
    }
    private void readData() {
        DBref.child("donor").orderByChild("Name").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                donorArray.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    donorClass users = dataSnapshot.getValue(donorClass.class);
                    donorArray.add(users);
                }
                Log.e("abc",donorArray.size()+"");
                adapter = new donorAdapter(Donors_ShowActivity.this,donorArray);
                recycle.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}

