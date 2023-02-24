package com.example.lab__7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle extras = getIntent().getExtras();
        String characterName = extras.getString("characterName");
        String characterHeight = extras.getString("characterHeight");
        String characterMass = extras.getString("characterMass");

        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("characterName", characterName);
        bundle.putString("characterHeight", characterHeight);
        bundle.putString("characterMass", characterMass);
        detailsFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.details_container, detailsFragment)
                .commit();
    }
}