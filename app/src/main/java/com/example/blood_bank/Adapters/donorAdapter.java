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

public class donorAdapter extends RecyclerView.Adapter<donorAdapter.Viewholder> {
    ArrayList<donorClass> donorArray;

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

        holder.Dname.setText(donorArray.get(position).getName());
        holder.Dphone.setText(donorArray.get(position).getPhone());
        holder.Dblood.setText(donorArray.get(position).getBlood());
        holder.Daddress.setText(donorArray.get(position).getAddress());
    }
        //
//        holder.bbemail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String Email= (bloodbankArray.get(position).getEmail());
//                Uri format = Uri.parse(("mailto" + Email));
//                Intent i = new Intent(Intent.ACTION_SENDTO,format);
//                i.putExtra(Intent.EXTRA_SUBJECT,"Request for blood");
//                i.putExtra(Intent.EXTRA_EMAIL,"Hi, i am --- i need this --- blood group");
//                Intent.createChooser(i,"Send Email");
//                view.getContext().startActivity(Intent.createChooser(i,"Send Email"));
//            }
//        });
//        holder.bbphone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String pho= (bloodbankArray.get(position).getPhoneNo());
//                Uri format = Uri.parse(("tel:" + pho));
//                Intent i = new Intent(Intent.ACTION_DIAL,format);
//                view.getContext().startActivity(i);
//            }
//        });
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
        ImageView share,email,call;
        public Viewholder(@NonNull View itemView) {
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
