package com.example.trobleticketsystem;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CommonTicketList extends ListActivity {
	
	DataBaseAdapter loginDataBaseAdapter = null;
	String activityName = null;

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
                R.layout.cust_list_view,R.id.label,list);


        // Assign adapter to List
        setListAdapter(adapter); 
   }

    
  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
        
        super.onListItemClick(l, v, position, id);
        
           
           // ListView Clicked item value
           String  itemValue    = (String) l.getItemAtPosition(position);
           
           
           Toast.makeText(CommonTicketList.this,"Clicked Item "+itemValue,Toast.LENGTH_LONG).show();
           
       	Bundle content = getIntent().getExtras();
		if (content != null) {
			
			activityName = content.getString("activity");
			if(activityName.equalsIgnoreCase("update")){
				
	           Intent backToCreate = new Intent(getApplicationContext(),CreateTicket.class);
	           backToCreate.putExtra("subject", itemValue);
	           startActivity(backToCreate);
			}else if(activityName.equalsIgnoreCase("close")){
				
				 Intent closeTicket = new Intent(getApplicationContext(),CloseTicket.class);
				 closeTicket.putExtra("subject", itemValue);
		          startActivity(closeTicket);
			}else if(activityName.equalsIgnoreCase("view")){
				
				Intent viewIntnet = new Intent(getApplicationContext(),ViewTicket.class);
				viewIntnet.putExtra("subject", itemValue);
		        startActivity(viewIntnet);
				
			}
           
		}
        
  }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_actions, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}

