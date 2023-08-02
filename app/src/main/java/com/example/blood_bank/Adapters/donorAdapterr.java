package com.example.blood_bank.Adapters;

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

import java.util.ArrayList;

public class donorAdapterr extends RecyclerView.Adapter<donorAdapterr.innerDonor> {
    ArrayList<donorClass> donorArray;

    public donorAdapterr(Donors_ShowActivity donors_showActivity, ArrayList<donorClass> donorArray) {
        this.donorArray = donorArray;
    }

    @NonNull
    @Override
    public donorAdapterr.innerDonor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.donor_card,parent,false);
        return new donorAdapterr.innerDonor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull innerDonor holder, int position) {
        holder.Dname.setText(donorArray.get(position).getName());
        holder.Dphone.setText(donorArray.get(position).getPhone());
        holder.Dblood.setText(donorArray.get(position).getBlood());
        holder.Daddress.setText(donorArray.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return donorArray.size();
    }
            public class innerDonor extends RecyclerView.ViewHolder {
                TextView Dname,Dphone,Dblood,Daddress;
                ImageView share,email,call;
                public innerDonor(View itemView) {
                    super(itemView);
                    Dname = itemView.findViewById(R.id.donor_name);
                    Dphone = itemView.findViewById(R.id.donor_phone_no);
                    Dblood = itemView.findViewById(R.id.donor_blood_group);
                    Daddress = itemView.findViewById(R.id.donor_location);
                    share = itemView.findViewById(R.id.share);
                    email = itemView.findViewById(R.id.mail);
                    call = itemView.findViewById(R.id.call);
                }
            }

}