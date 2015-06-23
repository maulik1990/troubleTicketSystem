package com.example.trobleticketsystem;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewTicket extends Activity {
	DataBaseAdapter loginDataBaseAdapter = null;
	String viewContent = null;
	String details = null;
	String item = null;
	Boolean isUpdateReq = false;
	String sub = null;
	String priority = null;
	int id = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view);
		// create a instance of SQLite Database
		loginDataBaseAdapter = new DataBaseAdapter(this);
		loginDataBaseAdapter = loginDataBaseAdapter.open();

		final EditText subjectName = (EditText) findViewById(R.id.subjectContent);
		final EditText troubleDetails = (EditText) findViewById(R.id.troubleDetails);
		final EditText prorityBox = (EditText) findViewById(R.id.priorityEdit);
		final Button closeBtn = (Button) findViewById(R.id.create);

		Bundle content = getIntent().getExtras();
		if (content != null) {

			viewContent = content.getString("subject");
			if (!viewContent.equalsIgnoreCase("")
					|| !viewContent.equalsIgnoreCase(null)) {

				Cursor cursor = loginDataBaseAdapter
						.fetchTicketDetails(viewContent);


				id = cursor.getInt(0);
				sub = cursor.getString(1);
				details = cursor.getString(2);
				priority = cursor.getString(3); 
				
				
				prorityBox.setText(priority);
				subjectName.setText(sub);
				troubleDetails.setText(details);


			}
		}
	}

}
