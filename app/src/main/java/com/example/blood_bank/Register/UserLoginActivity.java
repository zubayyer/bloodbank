package com.example.blood_bank.Register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.blood_bank.Dashboard_Activity;
import com.example.blood_bank.R;
import com.example.blood_bank.Show.BloodBank_ShowActivity;
import com.example.blood_bank.MainActivity;
import com.example.blood_bank.donorClass;
import com.example.blood_bank.acceptorClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Objects;

public class UserLoginActivity extends AppCompatActivity {
    AppCompatButton loginUser,btnNewUser;
    EditText emaill,pswd;
    DatabaseReference DBref;
    FirebaseAuth mAuth;
    ProgressBar progresBar;
    AppCompatSpinner userr;
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        userr = findViewById(R.id.AdminUserLogin);
        DBref = FirebaseDatabase.getInstance().getReference();
        progresBar = findViewById(R.id.progBar);
        emaill = findViewById(R.id.user_Email);
        pswd = findViewById(R.id.user_pswd);

        mAuth = FirebaseAuth.getInstance();
        loginUser = findViewById(R.id.loginUser);
        btnNewUser = findViewById(R.id.newUser);
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        Log.e( "uinfo ",currentUser.getDisplayName().toString() );

        SharedPreferences sh = getSharedPreferences("Donor",MODE_PRIVATE);
        if (sh.contains("DEmail")){
            Intent i = new Intent(UserLoginActivity.this, Dashboard_Activity.class);
            startActivity(i);
            finish();
        }

        String[] userLogin = {"Donor", "Acceptor"};
        ArrayAdapter adpt = new ArrayAdapter<>(UserLoginActivity.this, android.R.layout.simple_spinner_dropdown_item, userLogin);
        adpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userr.setAdapter(adpt);

        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLoginActivity.viewDialog viewD = new UserLoginActivity.viewDialog();
                viewD.showDialog(UserLoginActivity.this);
            }
        });
        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = String.valueOf(emaill.getText());
                String Password = pswd.getText().toString();
                String user = userr.getSelectedItem().toString();
                progresBar.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(UserLoginActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    Toast.makeText(UserLoginActivity.this, "Please Enter PAssword", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(UserLoginActivity.this, "Please Select User", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progresBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(UserLoginActivity.this, "Run", Toast.LENGTH_SHORT).show();
                                    if (user.equals("Donor")){
                                        Query Q = FirebaseDatabase.getInstance().getReference().child("donor").orderByChild("verification").equalTo("verified");
                                        Q.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists()){
//                                                    String name = snapshot.getValue(String.class);
//                                                    Log.d( "onDataChangedfdddd: ",name);
                                                    Toast.makeText(UserLoginActivity.this, "Donor", Toast.LENGTH_SHORT).show();
                                                    SharedPreferences sh = getSharedPreferences("Donor",MODE_PRIVATE);
                                                    SharedPreferences.Editor ed = sh.edit();
                                                    ed.putString("DEmail",Email);
                                                    ed.putString("Role","Donor");
                                                    ed.commit();
                                                    ed.apply();

                                                    Intent i = new Intent(UserLoginActivity.this, Dashboard_Activity.class);
                                                    startActivity(i);
                                                    finish();

                                                }
                                                else{
                                                    Toast.makeText(UserLoginActivity.this,"you are not verified", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }

                                    else if (user.equals("Acceptor")){
                                        Query Q = FirebaseDatabase.getInstance().getReference().child("user").orderByChild("email").equalTo(Email);
                                        Q.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists()){
                                                    Toast.makeText(UserLoginActivity.this, "Acceptor", Toast.LENGTH_SHORT).show();
                                                    SharedPreferences sh = getSharedPreferences("Donor",MODE_PRIVATE);
                                                    SharedPreferences.Editor ed = sh.edit();
                                                    ed.putString("DEmail",Email);
                                                    ed.putString("Role","Acceptor");
                                                    ed.commit();
                                                    ed.apply();
                                                    Intent i = new Intent(UserLoginActivity.this, Dashboard_Activity.class);
                                                    startActivity(i);
                                                    finish();
                                                }
                                                else{
                                                    Toast.makeText(UserLoginActivity.this,"Error", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }
                                } else {
                                    Toast.makeText(UserLoginActivity.this, "The ERROR is : "+task.getException().toString(),Toast.LENGTH_SHORT).show();
                                    Log.d( "onComplete: ",task.getException().toString());
                                }
                            }
                        });

            }
        });
    }

    public class login{

    }
    public class viewDialog{
        public void showDialog(Context context){
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_dialog_new_user);
            EditText Textname = dialog.findViewById(R.id.userName);
            EditText Textpswd = dialog.findViewById(R.id.userpswd);
            EditText Textphone = dialog.findViewById(R.id.userPhone);
            EditText Textaddress = dialog.findViewById(R.id.userAddress);
            EditText Textemail = dialog.findViewById(R.id.userEmail);
            EditText Textwhats = dialog.findViewById(R.id.userWhatsapp);
            AppCompatSpinner spinner = dialog.findViewById(R.id.AdminUser);
            AppCompatSpinner blood = dialog.findViewById(R.id.AdminBG);

            String[] user = {"Donor","Acceptor"};
            ArrayAdapter adpt = new ArrayAdapter<>(UserLoginActivity.this, android.R.layout.simple_spinner_dropdown_item,user);
            adpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adpt);

            String[] bloodG = {"AB+","A+","B+","O+","AB-","A-","B-","O-"};
            ArrayAdapter adpt1 = new ArrayAdapter<>(UserLoginActivity.this, android.R.layout.simple_spinner_dropdown_item,bloodG);
            adpt1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            blood.setAdapter(adpt1);

            AppCompatButton add = dialog.findViewById(R.id.bloodbank_AddBtn);
            AppCompatButton exit = dialog.findViewById(R.id.bloodbank_ExitBtn);
            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progresBar.setVisibility(View.VISIBLE);
                    String Id = "user" + new Date().getTime();
                    String Name = String.valueOf(Textname.getText());
                    String Email = Textemail.getText().toString();
                    String User = spinner.getSelectedItem().toString();
                    String Blood = blood.getSelectedItem().toString();
                    String Phone = Textphone.getText().toString();
                    String Whatsapp = Textwhats.getText().toString();
                    String Address = Textaddress.getText().toString();
                    String Password = Textpswd.getText().toString();

                    if (TextUtils.isEmpty(Name)) {
                        Toast.makeText(context, "Please Enter Name", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(Email)) {
                        Toast.makeText(context, "Please Enter Email", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(Phone)) {
                        Toast.makeText(context, "Please Enter Phone", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(Whatsapp)) {
                        Toast.makeText(context, "Please Enter Whatsapp", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(Address)) {
                        Toast.makeText(context, "Please Enter Address", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(Password)) {
                        Toast.makeText(context, "Please Enter Password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(User)) {
                        Toast.makeText(context, "Please Select User", Toast.LENGTH_SHORT).show();
                        return;
                    }

//                    String password;
                    mAuth.createUserWithEmailAndPassword(Email, Password)
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progresBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        if(User.equals("Donor")){
                                            DBref.child("donor").child(Id).setValue(new donorClass(Id,Name,Email,Phone,Whatsapp,Address,Password,User,Blood,"none verified"));
                                            Toast.makeText(context, "DONE", Toast.LENGTH_SHORT).show();
                                        } else if (User.equals("Acceptor")) {
                                            DBref.child("user").child(Id).setValue(new acceptorClass(Id,Name,Email,Phone,Whatsapp,Address,Password,User));
                                            Toast.makeText(context, "DONE", Toast.LENGTH_SHORT).show();
                                        }
                                        dialog.dismiss();
                                        Intent i = new Intent(UserLoginActivity.this, BloodBank_ShowActivity.class);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(UserLoginActivity.this, task.getException().toString(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    }
}