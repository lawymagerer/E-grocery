package com.example.nicole.e_grocery;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Home extends AppCompatActivity {
    ArrayList<HashMap<String,String>> menulist;
    ImageView imageViewHome;
    ListView listViewMenu;
    List<RowItem> rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageViewHome = (ImageView)findViewById(R.id.imageViewHome);
        imageViewHome.setImageResource(R.drawable.vegetable1);

        getMenu();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void getMenu(){
        menulist=new ArrayList<HashMap<String,String>>();
        rowItems = new ArrayList<>();
        rowItems.add(0, new RowItem(R.drawable.vegetable2, "View Groceries"));
        rowItems.add(1, new RowItem(R.drawable.sale, "Make Sale"));
        listViewMenu = (ListView)findViewById(R.id.listViewMenu);
        listViewMenu.setAdapter(new CustomListViewAdapter(getApplicationContext(), R.layout.menu_listitems, rowItems));
        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Intent groceryIntent = new Intent(Home.this,Groceries.class);
                    startActivity(groceryIntent);
                }else{
                    Intent cartIntent = new Intent(Home.this, MakeSale.class);
                    startActivity(cartIntent);
                }
            }
        });
    }
}
