package com.example.blood_bank.Adapters;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blood_bank.Dashboard_Activity;
import com.example.blood_bank.R;
import com.example.blood_bank.Register.UserLoginActivity;
import com.example.blood_bank.Show.Donors_ShowActivity;
import com.example.blood_bank.acceptorClass;
import com.example.blood_bank.donorClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class donorAdapter extends RecyclerView.Adapter<donorAdapter.Viewholder> {
    ArrayList<donorClass> donorArray;
    FirebaseUser currentUser;
    DatabaseReference DBref;
    FirebaseAuth mAuth;

    public donorAdapter(Donors_ShowActivity donors_showActivity, ArrayList<donorClass> donorArray) {
        this.donorArray = donorArray;
    }

    @NonNull
    @Override
    public donorAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.donor_card,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        mAuth = FirebaseAuth.getInstance();
        DBref = FirebaseDatabase.getInstance().getReference();
        currentUser = mAuth.getCurrentUser();
        String id = (donorArray.get(position).getId());
        String email = (donorArray.get(position).getEmail());
        String whatsapp = (donorArray.get(position).getWhatsApp());
        holder.Dname.setText(donorArray.get(position).getName());
        holder.Dphone.setText(donorArray.get(position).getPhone());
        holder.Dblood.setText(donorArray.get(position).getBlood());
        holder.Daddress.setText(donorArray.get(position).getAddress());

//        Intent upd = new Intent(holder.itemView.getContext(),Dashboard_Activity.class);
//        upd.putExtra("id",""+ donorArray.get(position).getId());
//        upd.putExtra("email",donorArray.get(position).getEmail());
//        upd.putExtra("whatsapp",donorArray.get(position).getWhatsApp());
//        upd.putExtra("name",donorArray.get(position).getName());
//        upd.putExtra("phone",donorArray.get(position).getPhone());
//        upd.putExtra("blood",donorArray.get(position).getBlood());
//        upd.putExtra("address",donorArray.get(position).getAddress());
//        holder.itemView.getContext().startActivity(upd);

//        holder.upd.setOnClickListener(new View.OnClickListener() {
//            ProgressBar progresBar;
//
//            @Override
//            public void onClick(View view) {
////                UserLoginActivity.viewDialog viewD = new UserLoginActivity.viewDialog();
////                viewD.showDialog(view.getContext());
//                final Dialog dialog = new Dialog(view.getContext());
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setCancelable(false);
//                dialog.setContentView(R.layout.alert_dialog_update);
//
//                progresBar = view.findViewById(R.id.update_progBar);
//
//                EditText UPDpass = dialog.findViewById(R.id.update_userpswd);
//                EditText UPDname = dialog.findViewById(R.id.update_userName);
//                EditText UPDphone = dialog.findViewById(R.id.update_userPhone);
//                EditText UPDaddress = dialog.findViewById(R.id.update_userAddress);
//                EditText UPDemail = dialog.findViewById(R.id.update_userEmail);
//                EditText UPDwhats = dialog.findViewById(R.id.update_userWhatsapp);
//                EditText UPDblood = dialog.findViewById(R.id.update_BG);
//                UPDblood.setVisibility(view.INVISIBLE);
//                EditText UPDuser = dialog.findViewById(R.id.update_User);
////                AppCompatSpinner UPDblood = dialog.findViewById(R.id.update_BG);
//
//                UPDname.setText(donorArray.get(position).getName());
//                UPDphone.setText(donorArray.get(position).getPhone());
//                UPDaddress.setText(donorArray.get(position).getAddress());
//                UPDemail.setText(donorArray.get(position).getEmail());
//                UPDwhats.setText(donorArray.get(position).getWhatsApp());
//                UPDblood.setText(donorArray.get(position).getBlood());
//                UPDpass.setText(donorArray.get(position).getPassword());
//                UPDuser.setText(donorArray.get(position).getUser());
//
//                SharedPreferences sh = view.getContext().getSharedPreferences("Donor",MODE_PRIVATE);
//                String usr = sh.getString("Role","");
////            SharedPreferences.Editor ed = sh.edit();
//                if(sh.contains("DEmail")) {
//                    if(usr.equals("Donor")){
//                        UPDblood.setVisibility(View.VISIBLE);
//                    }
//                }
//
////                String[] bloodG = {"AB+","A+","B+","O+","AB-","A-","B-","O-"};
////                ArrayAdapter adpt1 = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item,bloodG);
////                adpt1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////                UPDblood.setAdapter(adpt1);
//
//                AppCompatButton add = dialog.findViewById(R.id.update_UpdBtn);
//                AppCompatButton exit = dialog.findViewById(R.id.update_ExitBtn);
//                exit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//                add.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        progresBar.setVisibility(View.VISIBLE);
//                        String Id = "user" + new Date().getTime();
//                        String Name = String.valueOf(UPDname.getText());
//                        String Email = UPDemail.getText().toString();
//                        String Blood = UPDblood.getText().toString();
//                        String Phone = UPDphone.getText().toString();
//                        String Whatsapp = UPDwhats.getText().toString();
//                        String Address = UPDaddress.getText().toString();
//                        String Password = UPDpass.getText().toString();
//                        String User = UPDuser.getText().toString();
//
//                        if (TextUtils.isEmpty(Name)) {
//                            Toast.makeText(view.getContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                        if (TextUtils.isEmpty(Email)) {
//                            Toast.makeText(view.getContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                        if (TextUtils.isEmpty(Phone)) {
//                            Toast.makeText(view.getContext(), "Please Enter Phone", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                        if (TextUtils.isEmpty(Whatsapp)) {
//                            Toast.makeText(view.getContext(), "Please Enter Whatsapp", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                        if (TextUtils.isEmpty(Address)) {
//                            Toast.makeText(view.getContext(), "Please Enter Address", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//
//                    String password;
//
//                    }
//                });
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();
//
//
//
//            }
//        });

        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email= (donorArray.get(position).getEmail());
                Uri format = Uri.parse(("mailto" + Email));
                Intent i = new Intent(Intent.ACTION_SENDTO,format);
                i.putExtra(Intent.EXTRA_SUBJECT,"Request for blood");
                i.putExtra(Intent.EXTRA_EMAIL,"Hi, i am "+currentUser+" i need this (-"+donorArray.get(position).getBlood()+"- blood group");
                Intent.createChooser(i,"Send Email");
                view.getContext().startActivity(Intent.createChooser(i,"Send Email"));
            }
        });
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pho= (donorArray.get(position).getPhone());
                Uri format = Uri.parse(("tel:" + pho));
                Intent i = new Intent(Intent.ACTION_DIAL,format);
                view.getContext().startActivity(i);
            }
        });

//        holder.del.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                AlertDialog.Builder buider = new AlertDialog.Builder(view.getContext())
//                        .setTitle("Delete Helper").setIcon(R.drawable.baseline_delete_24).setMessage("Are you sure you want to Delete ?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
////                                FirebaseDatabase.getInstance().getReference().child("donor")
////                                        .child(getRef(position).getKey()).removeValue();
////
//                             }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                            }
//                        });
//                buider.show();
//                return true;
//            }
//        });



    }
//        holder.bbaddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Uri uri = Uri.parse("geo:0, 0?q="+bloodbankArray.get(position).getAddress());
//                Intent i = new Intent(Intent.ACTION_VIEW,uri);
//                i.setPackage("com.google.android.apps.maps");
//                view.getContext().startActivity(i);
//            }
//        });

    @Override
    public int getItemCount() {
        return donorArray.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView Dname,Dphone,Dblood,Daddress;
        ImageView email,call;
        Button del,upd;
        LinearLayout LLD,LLA;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            Dname = itemView.findViewById(R.id.donor_name);
            Dphone = itemView.findViewById(R.id.donor_phone_no);
            Dblood = itemView.findViewById(R.id.donor_blood_group);
            Daddress = itemView.findViewById(R.id.donor_location);
            email = itemView.findViewById(R.id.mail);
            call = itemView.findViewById(R.id.call);

        }
    }
}
