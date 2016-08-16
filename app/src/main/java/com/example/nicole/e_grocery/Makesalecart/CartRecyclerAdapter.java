package com.example.nicole.e_grocery.Makesalecart;

import android.app.DialogFragment;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nicole.e_grocery.Grocery.CustomViewHolderGrocery;
import com.example.nicole.e_grocery.R;

import java.util.List;


/**
 * Created by Magerer on 4/23/2016.
 */
public class CartRecyclerAdapter extends RecyclerView.Adapter<CustomViewHolderCart>{
    private List<cartItem> cartItemList;
    private Context mContext;

    public CartRecyclerAdapter(Context context, List<cartItem> ItemList) {
        this.cartItemList = ItemList;
        this.mContext = context;

    }

    @Override
    public CustomViewHolderCart onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_listitem, null);

        CustomViewHolderCart viewHolder = new CustomViewHolderCart(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolderCart customViewHolder, int i) {
        final cartItem cartItemItem = cartItemList.get(i);
        //Setting text view title
        customViewHolder.textViewNamecart.setText(cartItemItem.getCart_name());
        customViewHolder.textViewcartId.setText(cartItemItem.getCart_id());
        customViewHolder.imageViewCart.setImageResource(R.drawable.vegetable2);
        customViewHolder.textViewQuantity.setText(cartItemItem.getCart_quantity());
        customViewHolder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogFragment();
        //dialog.show(getF, "NoticeDialogFragment");
    }

    @Override
    public int getItemCount() {
        return (null != cartItemList ? cartItemList.size() : 0);
    }

    //set model logo
    public void getImage(CustomViewHolderGrocery customViewHolder) {



        //customViewHolder.imageViewGroceries.setImageDrawable();

    }

}
