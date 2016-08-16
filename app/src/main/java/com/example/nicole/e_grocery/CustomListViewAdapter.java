package com.example.nicole.e_grocery;

/**
 * Created by Magerer on 2/16/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

public class CustomListViewAdapter extends ArrayAdapter<RowItem> {

    Context context;
    Bitmap image;
    ViewHolder holder = null;


    public CustomListViewAdapter(Context context, int resourceId,
                                 List<RowItem> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageViewMenu;
        TextView textViewMenu;


    }

    public View getView(int position, View convertView, ViewGroup parent) {

        RowItem rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.menu_listitems, null);
            holder = new ViewHolder();
            holder.textViewMenu = (TextView) convertView.findViewById(R.id.textViewMenu);
            holder.imageViewMenu = (ImageView) convertView.findViewById(R.id.imageViewMenu);


            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.textViewMenu.setText(rowItem.getMenuName());
        holder.imageViewMenu.setImageResource(rowItem.getImage());
       // holder.imgUrlView.setText(rowItem.getImageId());



        return convertView;
    }


}

