package com.example.nicole.e_grocery;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    EditText txtUsernameL, txtPasswordL;
    Button btnLoginL;
    String username = "cashier";
    String password = "admin";
    String idNo="";
    ImageView imgIlab;


    private ProgressDialog pDialog;
    //AlertDialog alert = new AlertDialog();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");
        imgIlab = (ImageView)findViewById(R.id.imgLogo);
        imgIlab.setImageResource(R.drawable.logo);

        //Assigning textfields and buttons to their ids
        txtUsernameL = (EditText)findViewById(R.id.txtUsernameL);
        txtPasswordL = (EditText)findViewById(R.id.txtPasswordL);
        btnLoginL = (Button)findViewById(R.id.btnLoginL);
        btnLoginL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


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
    }
    public void login() {

        //Get values from the UI
        final String id = txtUsernameL.getText().toString().trim();
        final String pass = txtPasswordL.getText().toString().trim();


        // Showing progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();



                    if (id.equals(username) && pass.equals(password)){
                        Intent mainactivity = new Intent(Login.this, Home.class);

                        pDialog.cancel();
                        Bundle passID = new Bundle();
                        passID.putString("id", idNo);

                        startActivity(mainactivity);
                    }else{
                        pDialog.cancel();
                        //alert.setMessage(Login.this, "Username and Password do not match");

                        txtUsernameL.setText("");
                        txtPasswordL.setText("");


                    }



    }
}
