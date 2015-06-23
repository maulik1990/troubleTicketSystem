package com.example.trobleticketsystem;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HomePage extends ListActivity  {
    
    TextView content;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomescreen);
        
        
        
        //listView = (ListView) findViewById(R.id.list);
        String[] values = new String[] { "Create Ticket", "Update Ticket", "View Ticket",
          "Close Ticket"};

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_2, values);


        // Assign adapter to List
        setListAdapter(adapter); 
   }

    
  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
        
        super.onListItemClick(l, v, position, id);

           // ListView Clicked item index
           int itemPosition     = position;
           
           // ListView Clicked item value
           String  itemValue    = (String) l.getItemAtPosition(position);
           Log.d("List Item Value",itemValue);
           
           if(itemValue.equalsIgnoreCase("Create Ticket")){
        	   
        	   Intent createTicket=new Intent(getApplicationContext(),CreateTicket.class);
        	   createTicket.putExtra("activity","create");
               startActivity(createTicket);
				
           }else if(itemValue.equalsIgnoreCase("Update Ticket")){
        	   
        	   Intent updateTicket=new Intent(getApplicationContext(),CommonTicketList.class);
        	   updateTicket.putExtra("activity","update");
			   startActivity(updateTicket);
        	   
           }else if(itemValue.equalsIgnoreCase("View Ticket")){
        	   
        	   Intent viewTicket = new Intent(getApplicationContext(),CommonTicketList.class);
        	   viewTicket.putExtra("activity","view");
			   startActivity(viewTicket);
        	   
           }else if(itemValue.equalsIgnoreCase("Close Ticket")){
        	   
        	   Intent closeTicket=new Intent(getApplicationContext(),CommonTicketList.class);
        	   closeTicket.putExtra("activity","close");
			   startActivity(closeTicket);
           }
           
           
  }
}
