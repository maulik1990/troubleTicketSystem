package com.example.trobleticketsystem;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CloseTicket extends Activity {

	DataBaseAdapter loginDataBaseAdapter = null;
	String closeContent = null;
	int id = 0;
	String details = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.close);
		// create a instance of SQLite Database
		loginDataBaseAdapter = new DataBaseAdapter(this);
		loginDataBaseAdapter = loginDataBaseAdapter.open();


		final EditText troubleDetails = (EditText) findViewById(R.id.troubleDetails);
		final Button closeBtn = (Button) findViewById(R.id.close);

		Bundle content = getIntent().getExtras();
		if (content != null) {

			closeContent = content.getString("subject");
			if (!closeContent.equalsIgnoreCase("")
					|| !closeContent.equalsIgnoreCase(null)) {

				Cursor cursor = loginDataBaseAdapter.fetchTicketDetails(closeContent);

				id = cursor.getInt(0);
				details = cursor.getString(2);

				troubleDetails.setText(details);

				
				closeBtn.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

						loginDataBaseAdapter.closeTicket(id);
								Toast.makeText(CloseTicket.this,
										"Ticket Closed Successfully",
										Toast.LENGTH_LONG).show();

								Intent homePage = new Intent(getApplicationContext(),
										GridViewActivity.class);
								startActivity(homePage);
					}	
					});
			

		}
	}
}
}