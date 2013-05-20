package com.example.bicelche;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DB_adaptador {
public static final String KEY_ROWID = "id";
public static final String KEY_FACEBOOK = "facebook";
public static final String KEY_NOME = "nome";
public static final String KEY_IDADE = "idade";
public static final String KEY_SEXO = "sexo";
public static final String TAG = "DB_adaptador";
public static final String DATABASE_NAME = "telvo";
public static final String DATABASE_TABLE = "usuario";
public static final int DATABASE_VERSION = 1;
public static final String DATABASE_CREATE = "create table usuario" +
		"(id integer primary key autoincrement," +
		"facebook text,nome text,idade text,sexo text);";

private Context context;

private DatabaseHelper DBHelper;
private static SQLiteDatabase db;

public  DB_adaptador(Context ctx){
	
	this.context = ctx;
	DBHelper = new DatabaseHelper(context);
	
}
private static class DatabaseHelper extends SQLiteOpenHelper
 {
 DatabaseHelper(Context context)
 {
 super(context, DATABASE_NAME, null, DATABASE_VERSION);
 }

@Override
public void onCreate(SQLiteDatabase db) {
	// TODO Auto-generated method stub
	try{
		db.execSQL(DATABASE_CREATE);
		
		
	}catch(SQLException e){
		e.printStackTrace();
	
		// NUNCA DESISTA DE SEUS SONHOSSS PERCISTE!
		
	}
	}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// TODO Auto-generated method stub
	Log.w(TAG,"UPDATE databese from version"+ oldVersion+"para"+newVersion+
			", com will destroy all old data");
	db.execSQL("DROP TABLE IF EXISTS loja");
	onCreate(db);
	
}
}

public  DB_adaptador open() throws SQLException{
	
	db = DBHelper.getWritableDatabase();
	return this;
}
public void close(){
	DBHelper.close();
	}
public long insertRecord(String facebook, String nome, String idade, String
		sexo){
	
	ContentValues initialValues = new ContentValues();
	initialValues.put(KEY_FACEBOOK,facebook);
	initialValues.put(KEY_NOME, nome);
	initialValues.put(KEY_IDADE, idade);
	initialValues.put(KEY_SEXO, sexo);
	return db.insert(DATABASE_TABLE,null,initialValues);
}

public boolean deleteContact(long rowId){
	
	return db.delete(DATABASE_TABLE , KEY_ROWID +"="+rowId, null) >0;
}
public Cursor getAllRecords(long rowId){
	
	return db.query(DATABASE_TABLE, new String[]{KEY_ROWID,KEY_FACEBOOK,
			KEY_NOME,KEY_IDADE,KEY_SEXO},null,null,
			null,null,null);
}
public Cursor getRecord(long rowid) throws SQLException{
	
	{
		 Cursor mCursor =
		 db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
		 KEY_FACEBOOK, KEY_NOME, KEY_IDADE, KEY_SEXO},
		 KEY_ROWID + "=" + rowid, null, null, null, null, null);
		 if (mCursor != null) {
		 mCursor.moveToFirst();
		 
		 }
		 return mCursor;
	}}


public boolean updateRecord(long rowId, String title, String duedate, String
 course, String notes)
 {
 ContentValues args = new ContentValues();
 args.put(KEY_FACEBOOK, title);
 args.put(KEY_NOME, duedate);
args.put(KEY_IDADE, course);
 args.put(KEY_SEXO, notes);
 return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) >
0;
 }
 }


