package com.example.nicole.e_grocery.Grocery;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;


import com.example.nicole.e_grocery.Edit;
import com.example.nicole.e_grocery.Makesalecart.AddCartDialog;
import com.example.nicole.e_grocery.R;

import java.util.List;



/**
 * Created by Magerer on 4/23/2016.
 */
public class GroceryRecyclerAdapter extends RecyclerView.Adapter<CustomViewHolderGrocery>{
    private List<groceryItem> groceryItemList;
    private Context mContext;
    FragmentManager fragmentManager;

    public GroceryRecyclerAdapter(Context context, List<groceryItem> ItemList,FragmentManager _fragementManager) {
        this.groceryItemList = ItemList;
        this.mContext = context;
        this.fragmentManager=_fragementManager;

    }

    @Override
    public CustomViewHolderGrocery onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.groceries_listitem, null);

        CustomViewHolderGrocery viewHolder = new CustomViewHolderGrocery(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolderGrocery customViewHolder, int i) {
        final groceryItem groceryItemItem = groceryItemList.get(i);
        //Setting text view title
        customViewHolder.name.setText(groceryItemItem.getGrocery_name());
        customViewHolder.ID.setText(groceryItemItem.getGrocery_id());
        customViewHolder.imageViewGroceries.setImageResource(R.drawable.vegetable2);
        customViewHolder.price.setText(groceryItemItem.getGrocery_price());

        //getImage(customViewHolder);
    }


    @Override
    public int getItemCount() {
        return (null != groceryItemList ? groceryItemList.size() : 0);
    }

    //set model logo
    public void getImage(CustomViewHolderGrocery customViewHolder) {

        String groceryname=customViewHolder.name.getText().toString();

        //customViewHolder.imageViewGroceries.setImageDrawable();

    }

}
