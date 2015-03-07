package com.example.pum2z1;


import android.os.Bundle;
import android.util.Base64;
import android.util.Base64InputStream;

//import android.util.Base64OutputStream;


import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.LinearLayout;



public class DatabaseActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database);
	        
		
		
	        SQLiteDatabase  Bazadanych= openOrCreateDatabase("BazaStatki", MODE_PRIVATE, null);	        
	        Bazadanych.execSQL("CREATE TABLE IF NOT EXISTS GRA (gra_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,gra_nazwa TEXT,gra_ktorygracz TEXT, gra_plansza1 VARCHAR, gra_plansza2 VARCHAR)");        
	        
	        
	        LinearLayout listaZapisow = (LinearLayout) findViewById(R.id.listaZapisow);
	               
	        Cursor cursor = Bazadanych.rawQuery("SELECT * from GRA", null);
	        cursor.moveToFirst();
	        
	        while (cursor.isAfterLast() == false) {
	        	ButtonNadpisany przycisk = new ButtonNadpisany(this);
	        	przycisk.setText(cursor.getString(cursor.getColumnIndex("gra_nazwa")));
//	        	przycisk.setId(Integer.valueOf(cursor.getString(cursor.getColumnIndex("gra_id"))));
	        	
	        	przycisk.setStringPlansza1(cursor.getString(cursor.getColumnIndex("gra_plansza1")));
	        	przycisk.setStringPlansza2(cursor.getString(cursor.getColumnIndex("gra_plansza2")));
	        	przycisk.setKtoryGracz(cursor.getString(cursor.getColumnIndex("gra_ktorygracz")));
	        	przycisk.setId(Integer.valueOf(cursor.getString(cursor.getColumnIndex("gra_id"))));
	        	przycisk.setNazwaZapisu(cursor.getString(cursor.getColumnIndex("gra_nazwa")));
	        	
	        	
	        	przycisk.ustawClickListener(this);

	        	listaZapisow.addView(przycisk);
	        	cursor.moveToNext();
	        }
	        
	        Bazadanych.close();

//	            stringPlansza1 = cursor.getString(cursor.getColumnIndex("gra_plansza1"));
//
//	            
//	       // Toast.makeText(this, stringPlansza1 , Toast.LENGTH_LONG).show();
//
//
//	      

//	        
//	        
//	        
	        
	        
	        
//	        Toast.makeText(this, String.valueOf(planszaGracza1.zliczNiezniszczoneStatki()) , Toast.LENGTH_LONG).show();
//	        
//	        if (planszaOdczutuDeserializacji.isWszystkieStatkiZniszczone())
//	        {
//	        	Toast.makeText(this, "statki zniszczone" , Toast.LENGTH_LONG).show();
//	        } else Toast.makeText(this, "som" , Toast.LENGTH_LONG).show();
//	        
//	        int i;
	        
	}
	
	

	  
	  public static Object stringToObject(String str) 
	  {
          try {
            return new ObjectInputStream(new Base64InputStream(new ByteArrayInputStream(str.getBytes()), Base64.NO_PADDING | Base64.NO_WRAP)).readObject();
          } catch (Exception e) {
            e.printStackTrace();
          }
          return null;
      }

}
