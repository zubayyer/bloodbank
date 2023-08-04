package com.example.blood_bank.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blood_bank.R;
import com.example.blood_bank.Show.BloodGroup_ShowActivity;
import com.example.blood_bank.bloodClass;

import java.util.ArrayList;
public class bloodAdapter extends RecyclerView.Adapter<bloodAdapter.innerDonor> {
    ArrayList<bloodClass> bloodArray;

    public bloodAdapter(BloodGroup_ShowActivity bloodGroup_showActivity, ArrayList<bloodClass> bloodArray) {
        this.bloodArray = bloodArray;
    }

    @NonNull
    @Override
    public bloodAdapter.innerDonor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bloodgroup_card,parent,false);
        return new bloodAdapter.innerDonor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull innerDonor holder, int position) {
        holder.Bname.setText(bloodArray.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return bloodArray.size();
    }
            public class innerDonor extends RecyclerView.ViewHolder {
                TextView Bname;
                public innerDonor(View itemView) {
                    super(itemView);
                    Bname = itemView.findViewById(R.id.blood_group_name);
                }
            }

}