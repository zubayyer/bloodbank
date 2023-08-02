package com.example.blood_bank.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blood_bank.R;
import com.example.blood_bank.Show.BloodBank_ShowActivity;
import com.example.blood_bank.bloodbankClass;

import java.util.ArrayList;

public class bloodbankAdapter extends RecyclerView.Adapter<bloodbankAdapter.Viewholder> {
    ArrayList<bloodbankClass> bloodbankArray;

    public bloodbankAdapter(BloodBank_ShowActivity context, ArrayList<bloodbankClass> bloodbankArray) {
        this.bloodbankArray = bloodbankArray;
    }

    public bloodbankAdapter(ArrayList<bloodbankClass> bloodbankArray) {
        this.bloodbankArray = bloodbankArray;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_blood_bank_form,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
//        final String bbId = ""+bloodbankArray.get(position).getId();
        holder.bbname.setText(bloodbankArray.get(position).getBloodBank());
        holder.bbemail.setText(bloodbankArray.get(position).getEmail());
        holder.bbphone.setText(bloodbankArray.get(position).getPhoneNo());
        holder.bbwhatsapp.setText(bloodbankArray.get(position).getWhatsApp());
        holder.bbaddress.setText(bloodbankArray.get(position).getAddress());

        holder.bbemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email= (bloodbankArray.get(position).getEmail());
                Uri format = Uri.parse(("mailto" + Email));
                Intent i = new Intent(Intent.ACTION_SENDTO,format);
                i.putExtra(Intent.EXTRA_SUBJECT,"Request for blood");
                i.putExtra(Intent.EXTRA_EMAIL,"Hi, i am --- i need this --- blood group");
                Intent.createChooser(i,"Send Email");
                view.getContext().startActivity(Intent.createChooser(i,"Send Email"));
            }
        });
        holder.bbphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pho= (bloodbankArray.get(position).getPhoneNo());
                Uri format = Uri.parse(("tel:" + pho));
                Intent i = new Intent(Intent.ACTION_DIAL,format);
                view.getContext().startActivity(i);
            }
        });
        holder.bbaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("geo:0, 0?q="+bloodbankArray.get(position).getAddress());
                Intent i = new Intent(Intent.ACTION_VIEW,uri);
                i.setPackage("com.google.android.apps.maps");
                view.getContext().startActivity(i);
            }
        });
        holder.bbwhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://api.whatsapp.com/send?phone="+bloodbankArray.get(position).getWhatsApp();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bloodbankArray.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView bbname,bbemail,bbphone,bbwhatsapp,bbaddress;
        Button selectBtn;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            bbname = itemView.findViewById(R.id.bloodbank_name);
            bbemail = itemView.findViewById(R.id.bloodbank_email);
            bbphone = itemView.findViewById(R.id.bloodbank_phone);
            bbwhatsapp = itemView.findViewById(R.id.bloodbank_whatsapp);
            bbaddress = itemView.findViewById(R.id.bloodbank_address);
            bbwhatsapp = itemView.findViewById(R.id.bloodbank_whatsapp);

        }
    }
}
