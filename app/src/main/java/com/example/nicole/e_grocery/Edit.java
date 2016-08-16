package com.example.nicole.e_grocery;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Edit extends AppCompatActivity {
    EditText editTextEditName;
    EditText editTextEditPrice;
    String name, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        setTitle("Edit Grocery");
        editTextEditName = (EditText) findViewById(R.id.editTextEditName);
        editTextEditPrice = (EditText) findViewById(R.id.editTextEditPrice);

        name = getIntent().getExtras().getString("name");
        price = getIntent().getExtras().getString("price");

        editTextEditName.setText(name);
        editTextEditPrice.setText(price);

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
        //editItem();
    }
    public void editItem(){
        RequestQueue requestQueue=MySingleton.getInstance(getApplicationContext()).getRequestQueue();

        int id=1;
        String newTitle=editTextEditName.getText().toString();
        String newPrice=editTextEditPrice.getText().toString();

        String url=new ServerAddressClass().serverAddress()+"edit.php?ID="+id+"&Title="+newTitle+"&Description="+newPrice;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                JSONObject jsonObject=response;

                try {
                    String status=jsonObject.getString("status");
                    if(status.equals("success")){
                        String message=jsonObject.getString("message");
                        Toast.makeText(Edit.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Server error", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);


    }

}
