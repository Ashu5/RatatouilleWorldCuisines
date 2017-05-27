package com.example.ashutoshdwivedi.ratatouilleworldcuisines;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Ashutosh Dwivedi on 18-04-2017.
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private int[] imageId;
    String [] title;



    // constructor
    public ImageAdapter (Context context, int [] images, String[] itemTitle)
    {
        this.imageId=images;
        this.mContext=context;
        this.title=itemTitle;

    }
    public class ViewHolder
    {
        TextView textView;
        ImageView imageView;
    }


    @Override
    public int getCount() {
        return title.length ;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder= new ViewHolder();
        View rowView=null;
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        rowView=inflater.inflate(R.layout.gridview_items,null);
        holder.textView=(TextView)rowView.findViewById(R.id.GridView_itemText);
        holder.imageView=(ImageView)rowView.findViewById(R.id.GridView_item_image);
        holder.textView.setText(title[position]);
        holder.imageView.setImageResource(imageId[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"You Clicked" +title[position],Toast.LENGTH_SHORT).show();

            }
        });

        return  rowView;
    }
}
