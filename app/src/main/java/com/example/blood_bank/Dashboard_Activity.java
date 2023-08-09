package com.example.blood_bank;

import static android.view.View.INVISIBLE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
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
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blood_bank.Register.UserLoginActivity;
import com.example.blood_bank.Show.BloodBank_ShowActivity;
import com.example.blood_bank.Show.BloodGroup_ShowActivity;
import com.example.blood_bank.Show.Donors_ShowActivity;
import com.example.blood_bank.Show.HelperActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class Dashboard_Activity extends AppCompatActivity {
    ConstraintLayout hosp,blood,donor,help;
    TextView SPemail,SPuser;
    ImageButton logoutBtn;
    MaterialButton profile;
    DatabaseReference DBref;
    ProgressBar progresBar;
    FirebaseAuth mAuth;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        hosp = findViewById(R.id.dash_hospital);
        blood = findViewById(R.id.dash_bloodGroup);
        donor = findViewById(R.id.dash_donor);
        help = findViewById(R.id.dash_help);
        SPemail = findViewById(R.id.textView2);
        SPuser = findViewById(R.id.textView3);
        mAuth = FirebaseAuth.getInstance();
        DBref = FirebaseDatabase.getInstance().getReference();

        logoutBtn = findViewById(R.id.logOutB);
        profile = findViewById(R.id.editProfileB);
        SharedPreferences sh = getSharedPreferences("Donor",MODE_PRIVATE);
    if (!sh.contains("DEmail")){
        Intent i = new Intent(Dashboard_Activity.this, UserLoginActivity.class);
        startActivity(i);
        finish();
    }
    if(sh.contains("DEmail")){
        String usr = sh.getString("Role","");
        String ema = sh.getString("DEmail","");
        SPemail.setText(ema);
        if(usr.equals("Donor")){
            SPuser.setText(usr);
        }
        else{
            SPuser.setText(usr);
        }
    }
        hosp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard_Activity.this, BloodBank_ShowActivity.class);
                startActivity(i);
            }
        });

        blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard_Activity.this, BloodGroup_ShowActivity.class);
                startActivity(i);
            }
        });

        donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard_Activity.this, Donors_ShowActivity.class);
                startActivity(i);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard_Activity.this, HelperActivity.class);
                startActivity(i);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dashboard_Activity.viewDialog viewD = new Dashboard_Activity.viewDialog();
                viewD.showDialog(Dashboard_Activity.this);
                Toast.makeText(Dashboard_Activity.this, "aaaa   :  "+uid, Toast.LENGTH_SHORT).show();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sh = getSharedPreferences("Donor",MODE_PRIVATE);
                SharedPreferences.Editor ed = sh.edit();
                ed.clear();
                ed.apply();
                ed.commit();

                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(Dashboard_Activity.this, UserLoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public class viewDialog{
        public void showDialog(Context context){
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_dialog_update);

            progresBar = findViewById(R.id.update_progBar);

            EditText UPDpass = dialog.findViewById(R.id.update_userpswd);
            EditText UPDname = dialog.findViewById(R.id.update_userName);
            EditText UPDphone = dialog.findViewById(R.id.update_userPhone);
            EditText UPDaddress = dialog.findViewById(R.id.update_userAddress);
            EditText UPDemail = dialog.findViewById(R.id.update_userEmail);
            EditText UPDwhats = dialog.findViewById(R.id.update_userWhatsapp);
            EditText UPDblood = dialog.findViewById(R.id.update_BG);
            UPDblood.setVisibility(INVISIBLE);
            EditText UPDuser = dialog.findViewById(R.id.update_User);
            SharedPreferences sh = getSharedPreferences("Donor",MODE_PRIVATE);

            Query emailQuery = FirebaseDatabase.getInstance().getReference().orderByChild("email").equalTo(sh.getString("DEmail",""));
            emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot child : snapshot.getChildren()) {
                        donorClass user = child.getValue(donorClass.class);
                        uid = user.getName().toString();
                        Log.d("hhhhhhhhhhh" ,uid);
                        UPDname.setText(uid);
                        //Do what you need to do with UID.
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("TAG", error.getMessage()); //Never ignore potential errors!
                }
            });

//                AppCompatSpinner UPDblood = dialog.findViewById(R.id.update_BG);


//            Intent upd = getIntent();
//            UPDphone.setText(upd.getStringExtra("phone"));
//            UPDname.setText(upd.getStringExtra("name"));
//            UPDemail.setText(upd.getStringExtra("email"));
//            UPDwhats.setText(upd.getStringExtra("whatapp"));
//            UPDblood.setText(upd.getStringExtra("blood"));
//            UPDuser.setText(upd.getStringExtra("user"));
//            UPDaddress.setText(upd.getStringExtra("address"));
//
//            SharedPreferences sh = getSharedPreferences("Donor",MODE_PRIVATE);
//            String usr = sh.getString("Role","");
////            SharedPreferences.Editor ed = sh.edit();
//            if(sh.contains("DEmail")) {
//                if(usr.equals("Donor")){
//                    UPDblood.setVisibility(View.VISIBLE);
//                }
//            }

//                String[] bloodG = {"AB+","A+","B+","O+","AB-","A-","B-","O-"};
//                ArrayAdapter adpt1 = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item,bloodG);
//                adpt1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                UPDblood.setAdapter(adpt1);

            AppCompatButton add = dialog.findViewById(R.id.update_UpdBtn);
            AppCompatButton exit = dialog.findViewById(R.id.update_ExitBtn);
            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    progresBar.setVisibility(View.VISIBLE);
//                    String Id = upd.getStringExtra("id");
//                    String Name = String.valueOf(UPDname.getText());
//                    String Email = UPDemail.getText().toString();
//                    String Blood = UPDblood.getText().toString();
//                    String Phone = UPDphone.getText().toString();
//                    String Whatsapp = UPDwhats.getText().toString();
//                    String Address = UPDaddress.getText().toString();
//                    String Password = UPDpass.getText().toString();
//                    String User = UPDuser.getText().toString();
//
//                    if (TextUtils.isEmpty(Name)) {
//                        Toast.makeText(view.getContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    if (TextUtils.isEmpty(Email)) {
//                        Toast.makeText(view.getContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    if (TextUtils.isEmpty(Phone)) {
//                        Toast.makeText(view.getContext(), "Please Enter Phone", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    if (TextUtils.isEmpty(Whatsapp)) {
//                        Toast.makeText(view.getContext(), "Please Enter Whatsapp", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    if (TextUtils.isEmpty(Address)) {
//                        Toast.makeText(view.getContext(), "Please Enter Address", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//
//
//
//

                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();


        }
    }

}