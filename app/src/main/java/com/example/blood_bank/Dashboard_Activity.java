package com.example.blood_bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;

public class Dashboard_Activity extends AppCompatActivity {
    ConstraintLayout hosp,blood,donor,help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        hosp = findViewById(R.id.dash_hospital);
        blood = findViewById(R.id.dash_bloodGroup);
        donor = findViewById(R.id.dash_donor);
        help = findViewById(R.id.dash_help);
    }
}