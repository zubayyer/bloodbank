package com.example.blood_bank.Adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blood_bank.R;
import com.example.blood_bank.Show.Donors_ShowActivity;
import com.example.blood_bank.donorClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class donorAdapter extends RecyclerView.Adapter<donorAdapter.Viewholder> {
    ArrayList<donorClass> donorArray;
    FirebaseUser currentUser;
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
        currentUser = mAuth.getCurrentUser();
        holder.Dname.setText(donorArray.get(position).getName());
        holder.Dphone.setText(donorArray.get(position).getPhone());
        holder.Dblood.setText(donorArray.get(position).getBlood());
        holder.Daddress.setText(donorArray.get(position).getAddress());
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
