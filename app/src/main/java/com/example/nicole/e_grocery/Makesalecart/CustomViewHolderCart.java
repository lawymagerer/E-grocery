package com.example.nicole.e_grocery.Makesalecart;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicole.e_grocery.R;


/**
 * Created by Magerer on 4/23/2016.
 */
public class CustomViewHolderCart extends RecyclerView.ViewHolder {

    protected ImageView imageViewCart;
    protected TextView textViewNamecart;
    protected  TextView textViewcartId;
    protected TextView textViewQuantity;
    protected ImageView btnCancel;



    public CustomViewHolderCart(View view) {
        super(view);
        this.imageViewCart = (ImageView) view.findViewById(R.id.imageViewCart);
        this.textViewNamecart = (TextView) view.findViewById(R.id.textViewNamecart);
        this.textViewcartId = (TextView) view.findViewById(R.id.textViewcartId);
        this.textViewQuantity = (TextView) view.findViewById(R.id.textViewQuantity);
        this.btnCancel = (ImageView) view.findViewById(R.id.btnCancel);




    }
}
