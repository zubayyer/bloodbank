package com.example.blood_bank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
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

import java.util.Date;

public class Dashboard_Activity extends AppCompatActivity {
    ConstraintLayout hosp,blood,donor,help;
    ImageButton logoutBtn,backBtn;
    MaterialButton profile;
    ProgressBar progresBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        hosp = findViewById(R.id.dash_hospital);
        blood = findViewById(R.id.dash_bloodGroup);
        donor = findViewById(R.id.dash_donor);
        help = findViewById(R.id.dash_help);
        logoutBtn = findViewById(R.id.logOutB);
        backBtn = findViewById(R.id.backB);
        profile = findViewById(R.id.editProfileB);

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
//        profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dashboard_Activity.viewDialog viewD = new Dashboard_Activity().viewDialog();
//                viewD.showDialog(Dashboard_Activity.this);
//            }
//        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            EditText UPDname = dialog.findViewById(R.id.update_userName);
            EditText UPDpswd = dialog.findViewById(R.id.update_userpswd);
            EditText UPDphone = dialog.findViewById(R.id.update_userPhone);
            EditText UPDaddress = dialog.findViewById(R.id.update_userAddress);
            EditText UPDemail = dialog.findViewById(R.id.update_userEmail);
            EditText UPDwhats = dialog.findViewById(R.id.update_userWhatsapp);
            AppCompatSpinner UPDblood = dialog.findViewById(R.id.update_BG);


            String[] bloodG = {"AB+","A+","B+","O+","AB-","A-","B-","O-"};
            ArrayAdapter adpt1 = new ArrayAdapter<>(Dashboard_Activity.this, android.R.layout.simple_spinner_dropdown_item,bloodG);
            adpt1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            UPDblood.setAdapter(adpt1);

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
                    progresBar.setVisibility(View.VISIBLE);
                    String Id = "user" + new Date().getTime();
                    String Name = String.valueOf(UPDname.getText());
                    String Email = UPDemail.getText().toString();
                    String Blood = UPDblood.getSelectedItem().toString();
                    String Phone = UPDphone.getText().toString();
                    String Whatsapp = UPDwhats.getText().toString();
                    String Address = UPDaddress.getText().toString();
                    String Password = UPDpswd.getText().toString();

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
//                    if (TextUtils.isEmpty(User)) {
//                        Toast.makeText(context, "Please Select User", Toast.LENGTH_SHORT).show();
//                        return;
//                    }

//                    String password;
//                    mAuth.createUserWithEmailAndPassword(Email, Password)
//                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                    progresBar.setVisibility(View.GONE);
//                                    if (task.isSuccessful()) {
//                                        if(User.equals("Donor")){
//                                            DBref.child("donor").child(Id).setValue(new donorClass(Id,Name,Email,Phone,Whatsapp,Address,Password,User,Blood,"none verified"));
//                                            Toast.makeText(context, "DONE", Toast.LENGTH_SHORT).show();
//                                        } else if (User.equals("Acceptor")) {
//                                            DBref.child("user").child(Id).setValue(new acceptorClass(Id,Name,Email,Phone,Whatsapp,Address,Password,User));
//                                            Toast.makeText(context, "DONE", Toast.LENGTH_SHORT).show();
//                                        }
//                                        dialog.dismiss();
//                                        Intent i = new Intent(UserLoginActivity.this, BloodBank_ShowActivity.class);
//                                        startActivity(i);
//                                        finish();
//                                    } else {
//                                        Toast.makeText(UserLoginActivity.this, task.getException().toString(),
//                                                Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    }

}