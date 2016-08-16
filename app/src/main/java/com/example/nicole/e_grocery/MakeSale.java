package com.example.nicole.e_grocery;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.nicole.e_grocery.Grocery.GroceryRecyclerAdapter;
import com.example.nicole.e_grocery.Grocery.groceryItem;
import com.example.nicole.e_grocery.Makesalecart.CartRecyclerAdapter;
import com.example.nicole.e_grocery.Makesalecart.cartItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MakeSale extends AppCompatActivity {
    ImageView imageViewCart;
    TextView textViewNamecart;
    TextView textViewQuantity;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ProgressDialog pDialog;
    List<cartItem> rowItems;


    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makesale);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        recyclerView= (RecyclerView) findViewById(R.id.listViewCart);
        //setting onClickItem Listner
        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });


        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    //Toast.makeText(MainActivity.this, "The Item Clicked is: " + recyclerView.getChildPosition(child), Toast.LENGTH_SHORT).show();


                    return true;

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getCartList();
    }
    public void getCartList(){
        rowItems= new ArrayList<>();


        String list="[{'name':'Sukuma','quantity':'25','cost':'9250'}]";
        try {
            JSONArray jsonArray=new JSONArray(list);
            for(int i=0;i<jsonArray.length();i++) {
                JSONObject obj=jsonArray.getJSONObject(i);
                String name = obj.getString("name");
                String quantity = obj.getString("quantity");
                String total = obj.getString("cost");


                cartItem item = new cartItem();
                //add model info to an arrayList
                item.setCart_name(name);
                item.setCart_quantity("x "+quantity+" :Ksh "+total);

                rowItems.add(item);
            }
            adapter = new CartRecyclerAdapter(this, rowItems);
            recyclerView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        String url="";
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                JSONObject jsonObject= response;
//
//                String status= null;
//                try {
//                    status = jsonObject.getString("status");
//                    if(status.equals("success")){
//
//                    }else{
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        queue.add(jsonObjectRequest);
    }


}
