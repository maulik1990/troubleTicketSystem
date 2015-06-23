package com.example.trobleticketsystem;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateTicket extends ListActivity {
TextView content;
    
DataBaseAdapter loginDataBaseAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        
        loginDataBaseAdapter = new DataBaseAdapter(this);
	     loginDataBaseAdapter = loginDataBaseAdapter.open();
        
        //listView = (ListView) findViewById(R.id.list);
        List<String> list = new ArrayList<String>();

        
        list = loginDataBaseAdapter.fetchAllSubject();
        

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list);


        // Assign adapter to List
        setListAdapter(adapter); 
   }

    
  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
        
        super.onListItemClick(l, v, position, id);
        
           
           // ListView Clicked item value
           String  itemValue    = (String) l.getItemAtPosition(position);
           
           
           Toast.makeText(UpdateTicket.this,"Clicked Item "+itemValue,Toast.LENGTH_LONG).show();
          
           Intent backToCreate = new Intent(getApplicationContext(),CreateTicket.class);
           backToCreate.putExtra("subject", itemValue);
           startActivity(backToCreate);
        
  }

}