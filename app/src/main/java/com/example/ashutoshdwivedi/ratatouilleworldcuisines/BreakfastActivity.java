package com.example.ashutoshdwivedi.ratatouilleworldcuisines;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class BreakfastActivity extends AppCompatActivity {

    GridView gridView;

    public static int [] breakfast_images={R.drawable.bkf_bnanamffins,R.drawable.bkf_strbrysmthi,R.drawable.bkf_cinmnswrl,R.drawable.bkf_cliflrgrlc,

            R.drawable.bkf_corangerls,R.drawable.bkf_stkomlet,R.drawable.bkf_craspmfin,R.drawable.bkf_mshdpotos,R.drawable.bkf_pmpknbsct,R.drawable.bkf_pntomaca};

    String [] title={"Banana Muffins","Strawberry Smith","Cinnommom Swirl","Cauliflower GArlic Sticks","Orange Rolls","Stoke omwlette",
    "CraspBerry Muffins","Mashed potatos","Pumpkin Seed Biscuits","Pan Tomaca"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Breakfast Recipes");
        setContentView(R.layout.activity_breakfast);
        gridView=(GridView)findViewById(R.id.gridview_breakfast_recipes);
        gridView.setClickable(true);
        gridView.setAdapter(new ImageAdapter(this,breakfast_images,title));
    }
}
