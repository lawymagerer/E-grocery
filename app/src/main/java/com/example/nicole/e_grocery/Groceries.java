package com.example.nicole.e_grocery;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.nicole.e_grocery.Grocery.GroceryRecyclerAdapter;
import com.example.nicole.e_grocery.Grocery.groceryItem;
import com.example.nicole.e_grocery.Makesalecart.AddCartDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Groceries extends AppCompatActivity {
    ImageView imageViewGroceries;
    TextView textViewGroceryName;
    TextView textViewGroceryPrice;
    ListView listViewGroceries;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ProgressDialog pDialog;
    List<groceryItem> rowItems;

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groceries);

        imageViewGroceries = (ImageView)findViewById(R.id.imageViewGroceries);
        textViewGroceryName = (TextView)findViewById(R.id.textViewGroceryName);
        textViewGroceryPrice = (TextView)findViewById(R.id.textViewGroceryPrice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        queue=MySingleton.getInstance(getApplicationContext()).getRequestQueue();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView= (RecyclerView) findViewById(R.id.listViewGroceries);
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
                    //Creating the instance of PopupMenu
                    int position = recyclerView.getChildPosition(child);
                    groceryItem item = rowItems.get(position);
                    final String name = item.getGrocery_name();
                    final String price = item.getGrocery_price();
                    String id = item.getGrocery_id();
                    PopupMenu popup = new PopupMenu(getApplicationContext(),recyclerView.getChildAt(position));
                    //Inflating the Popup using xml file
                    popup.getMenuInflater().inflate(R.menu.popupmenu, popup.getMenu());

                    //registering popup with OnMenuItemClickListener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            int id = item.getItemId();
                            if (id == R.id.popup_edit) {
                                Bundle pass = new Bundle();
                                pass.putString("name",name);
                                pass.putString("price",price);

                                Intent editIntent = new Intent(getApplicationContext(), Edit.class);
                                editIntent.putExtras(pass);
                                startActivity(editIntent);

                            }else if (id == R.id.popup_addtocart) {
                                showNoticeDialog();

                            }else if (id == R.id.popup_delete){

                            }
                            return true;
                        }
                    });

                    popup.show();//showing popup menu

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
        getGroceryList();
    }


    public void getGroceryList() {
        pDialog = new ProgressDialog(getApplicationContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
//        pDialog.show();

        rowItems = new ArrayList<>();


        RequestQueue queue = MySingleton.getInstance(getApplicationContext()).getRequestQueue();
        String url = new ServerAddressClass().serverAddress() + "VegetableList";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("message", response);
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String name = obj.getString("name");
                        String price = obj.getString("price");


                        groceryItem item = new groceryItem();
                        //add model info to an arrayList
                        item.setGrocery_name(name);
                        item.setGrocery_price(price);
                        rowItems.add(item);
                    }
                    adapter = new GroceryRecyclerAdapter(getApplicationContext(), rowItems, getFragmentManager());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }
    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new AddCartDialog();
        dialog.show(getFragmentManager(), "NoticeDialogFragment");
        //new AddCartDialog().showAlertDialog(mContext);
    }


}
