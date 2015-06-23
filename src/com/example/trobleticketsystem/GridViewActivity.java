package com.example.trobleticketsystem;

import com.example.trobleticketsystem.ImageAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
 
public class GridViewActivity extends Activity {
 
	GridView gridView;
 
	static final String[] OPERATIONS = new String[] { 
		"Create Ticket", "Update Ticket","View Ticket", "Close Ticket" };
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid);
 
		gridView = (GridView) findViewById(R.id.gridView1);
 
		gridView.setAdapter(new ImageAdapter(this, OPERATIONS));
 
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				
				
				String itemValue = ((TextView) v.findViewById(R.id.grid_item_label)).getText().toString();
				
				Toast.makeText(
						   getApplicationContext(),
						   itemValue, Toast.LENGTH_SHORT).show();
				
				if(itemValue.equalsIgnoreCase("Create Ticket")){
		        	   
		        	   Intent createTicket=new Intent(getApplicationContext(),CreateTicket.class);
		        	   //createTicket.putExtra("activity","create");
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
		});
 
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