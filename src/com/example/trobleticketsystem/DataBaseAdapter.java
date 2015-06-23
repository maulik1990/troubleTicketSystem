package com.example.trobleticketsystem;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseAdapter {
	static final String DATABASE_NAME = "tts.db";
	static final int DATABASE_VERSION = 2;
	public static final int NAME_COLUMN = 1;
	// TODO: Create public field for each column in your table.
	// SQL Statement to create a new database.
	static final String DATABASE_LOGIN_TABLE = "create table " + "LOGIN" + "( "
			+ "ID" + " integer primary key autoincrement,"
			+ "USERNAME  text,PASSWORD text); ";
	static final String DATABASE_TICKET_TABLE = "create table "
			+ "TICKET_DETAILS" + "( " + "ID"
			+ " integer primary key autoincrement,"
			+ "SUBJECT  text,DETAILS text,PRIORITY text); ";
	// Variable to hold the database instance
	public SQLiteDatabase db;
	// Context of the application using the database.
	private final Context context;
	// Database open/upgrade helper
	private DataBaseHelper dbHelper;

	public DataBaseAdapter(Context _context) {
		context = _context;
		dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,
				DATABASE_VERSION);
	}

	public DataBaseAdapter open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		db.close();
	}

	public SQLiteDatabase getDatabaseInstance() {
		return db;
	}

	/*
	 * INSERT DATA INTO LOGIN TABLE
	 */
	public void insertLoginDetails(String userName, String password) {
		ContentValues newValues = new ContentValues();
		// Assign values for each row.
		newValues.put("USERNAME", userName);
		newValues.put("PASSWORD", password);

		// Insert the row into your table
		db.insert("LOGIN", null, newValues);
		// /Toast.makeText(context, "Reminder Is Successfully Saved",
		// Toast.LENGTH_LONG).show();
	}

	/*
	 * Insert Data into TicketDetails table
	 */
	public void insertTicketDetails(String subject, String details,
			String priority) {
		ContentValues newValues = new ContentValues();
		// Assign values for each row.
		newValues.put("SUBJECT", subject);
		newValues.put("DETAILS", details);
		newValues.put("PRIORITY", priority);

		// Insert the row into your table
		try {

			db.insert("TICKET_DETAILS", null, newValues);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// /Toast.makeText(context, "Reminder Is Successfully Saved",
		// Toast.LENGTH_LONG).show();
	}

	/*
	 * Update Data into TicketDetails table
	 */
	public void updateTicketDetails(String subject, String details,
			String priority, int id) {

		String updateQuery = "UPDATE TICKET_DETAILS SET SUBJECT= \"" + subject
				+ "\",DETAILS= \"" + details + "\",PRIORITY= \"" + priority
				+ "\"  WHERE id=" + id;
		db.execSQL(updateQuery);
	}

	/*
	 * Fetch Single row from database
	 */
	public String getSinlgeEntry(String userName) {
		Cursor cursor = db.query("LOGIN", null, " USERNAME=?",
				new String[] { userName }, null, null, null);
		if (cursor.getCount() < 1) // UserName Not Exist
		{
			cursor.close();
			return "NOT EXIST";
		}
		cursor.moveToFirst();
		String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
		cursor.close();
		return password;
	}

	/*
	 * Fetch All subject from TicketTable
	 */
	public List<String> fetchAllSubject() {
		List<String> subList = new ArrayList<String>();
		// Select All Query
		String selectQuery = "SELECT SUBJECT FROM TICKET_DETAILS";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				subList.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}

		// return contact list
		return subList;
	}

	public Cursor fetchTicketDetails(String subject) {

		String selectTicketDetails = "SELECT ID,SUBJECT,DETAILS,PRIORITY FROM TICKET_DETAILS where SUBJECT=\""
				+ subject + "\"";

		Cursor mCursor = db.rawQuery(selectTicketDetails, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	/*
	 * Delete Data into TicketDetails table
	 */
	public void closeTicket(int id) {

		String deleteQuery = "DELETE FROM TICKET_DETAILS WHERE id=" + id;
		db.execSQL(deleteQuery);
	}
}
