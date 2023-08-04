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

import com.example.blood_bank.Adapters.bloodAdapter;
import com.example.blood_bank.R;
import com.example.blood_bank.Register.UserLoginActivity;
import com.example.blood_bank.bloodClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BloodGroup_ShowActivity extends AppCompatActivity {
    DatabaseReference DBref;
    RecyclerView recycle;
    bloodAdapter adapter;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    ArrayList<bloodClass> bloodArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_group_show);
        AppCompatButton logout = findViewById(R.id.buttonlogOUT);
        DBref = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Intent i = new Intent(BloodGroup_ShowActivity.this, UserLoginActivity.class);
            startActivity(i);
            finish();
        } else {
            logout.setText("LOG OUT    :      "+ currentUser.getEmail());
        }

        recycle = findViewById(R.id.recyclerViewBlood);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        //        RecyclerView.setHasFixedSize(true);
        bloodArray = new ArrayList<>();
        readData();
    }
    private void readData() {
        DBref.child("bloodgroup").orderByChild("BloodGroup").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bloodArray.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    bloodClass users = dataSnapshot.getValue(bloodClass.class);
                    bloodArray.add(users);
                }
                Log.e("abc",bloodArray.size()+"");
                adapter = new bloodAdapter(BloodGroup_ShowActivity.this,bloodArray);
                recycle.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
