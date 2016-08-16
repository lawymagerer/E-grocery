package com.example.nicole.e_grocery.Grocery;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicole.e_grocery.R;


/**
 * Created by Magerer on 4/23/2016.
 */
public class CustomViewHolderGrocery extends RecyclerView.ViewHolder {

    protected ImageView imageViewGroceries;
    protected TextView name;
    protected  TextView ID;
    protected TextView price;



    public CustomViewHolderGrocery(View view) {
        super(view);
        this.imageViewGroceries = (ImageView) view.findViewById(R.id.imageViewGroceries);
        this.name = (TextView) view.findViewById(R.id.textViewGroceryName);
        this.ID = (TextView) view.findViewById(R.id.textViewGroceryId);
        this.price = (TextView) view.findViewById(R.id.textViewGroceryPrice);




    }
}
