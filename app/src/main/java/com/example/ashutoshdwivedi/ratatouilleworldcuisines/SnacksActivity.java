package com.example.ashutoshdwivedi.ratatouilleworldcuisines;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;

public class SnacksActivity extends AppCompatActivity {
    public GridView gridView;
    public int[] snacksimages={R.drawable.trkfst_hcnpitas};
    public String[] title={"Snacks"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snacks);
        gridView=(GridView)findViewById(R.id.gridview_snacks_recipe);
        gridView.setAdapter(new ImageAdapter(this,snacksimages,title));
    }
}
