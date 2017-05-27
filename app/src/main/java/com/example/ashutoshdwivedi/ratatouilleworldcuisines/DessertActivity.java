package com.example.ashutoshdwivedi.ratatouilleworldcuisines;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class DessertActivity extends AppCompatActivity {
  GridView gridView;

    private int []recipeimages={R.drawable.bkf_pntomaca};
    private String [] title={""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner_acivity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Desserts ");
        gridView=(GridView)findViewById(R.id.gridview_dinner_recipes);
        gridView.setAdapter(new ImageAdapter(this,recipeimages,title));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
