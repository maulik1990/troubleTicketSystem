package com.example.trobleticketsystem;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateTicket extends Activity implements OnItemSelectedListener {

	/*
	 * Variable to store spinner item selected value
	 */
	String item = null;
	DataBaseAdapter loginDataBaseAdapter = null;
	String updateContent = null;
	Boolean isUpdateReq = false;
	String sub = null;
	String details= null;
	String priority = null;
	int id = 0;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create);

		// create a instance of SQLite Database
		loginDataBaseAdapter = new DataBaseAdapter(this);
		loginDataBaseAdapter = loginDataBaseAdapter.open();

		// get the References of views
		final EditText subjectName = (EditText) findViewById(R.id.subjectContent);
		final EditText troubleDetails = (EditText) findViewById(R.id.troubleDetails);
		final Button createBtn = (Button) findViewById(R.id.create);

		final Button updateBtn;

		Spinner priorityList = (Spinner) findViewById(R.id.priorityList);

		// Spinner click listener
		priorityList.setOnItemSelectedListener(this);

		// Spinner Drop down elements
		List<String> priorities = new ArrayList<String>();
		priorities.add("Low");
		priorities.add("Medium");
		priorities.add("High");
		priorities.add("Critical");

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, priorities);

		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		priorityList.setAdapter(dataAdapter);

		/*
		 * Checks whether request is for create or update
		 */
		Bundle content = getIntent().getExtras();
		if (content != null) {
			updateContent = content.getString("subject");
			if (!updateContent.equalsIgnoreCase("")
					|| !updateContent.equalsIgnoreCase(null)) {

				Cursor cursor = loginDataBaseAdapter
						.fetchTicketDetails(updateContent);

				id = cursor.getInt(0);
				sub = cursor.getString(1);
				details = cursor.getString(2);
				priority = cursor.getString(3); 
				
				int itemPosition = dataAdapter.getPosition(priority);
				priorityList.setSelection(itemPosition);
				subjectName.setText(sub);
				troubleDetails.setText(details);

				updateBtn = createBtn;
				updateBtn.setText("Update");
				
				isUpdateReq = true;

			}
		}
			/*
			 * Button even on click Listener for Create operation
			 */
			createBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					sub = subjectName.getText().toString();
					details = troubleDetails.getText().toString();
					
					if(isUpdateReq){


					 loginDataBaseAdapter.updateTicketDetails(sub, details,
					 item,id);
					Toast.makeText(CreateTicket.this,
							"Congrats: Ticket Updated Successfully",
							Toast.LENGTH_LONG).show();
					}
					else
					{	
						 loginDataBaseAdapter.insertTicketDetails(sub, details,
								 item);
								Toast.makeText(CreateTicket.this,
										"Ticket Raised Successfully",
										Toast.LENGTH_LONG).show();
					}
					Intent homePage = new Intent(getApplicationContext(),
							GridViewActivity.class);
					startActivity(homePage);
					
				}
			});
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// On selecting a spinner item
		item = parent.getItemAtPosition(position).toString();

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		loginDataBaseAdapter.close();
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