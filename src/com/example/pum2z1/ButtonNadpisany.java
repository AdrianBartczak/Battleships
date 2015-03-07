package com.example.pum2z1;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Base64InputStream;
import android.view.View;
import android.widget.Button;

public class ButtonNadpisany extends Button
{
	private String stringPlansza1, stringPlansza2, nazwaZapisu, ktoryGracz;
	private int id;
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getStringPlansza1()
	{
		return stringPlansza1;
	}

	public void setStringPlansza1(String stringPlansza1)
	{
		this.stringPlansza1 = stringPlansza1;
	}

	public String getStringPlansza2()
	{
		return stringPlansza2;
	}

	public void setStringPlansza2(String stringPlansza2)
	{
		this.stringPlansza2 = stringPlansza2;
	}

	public String getNazwaZapisu()
	{
		return nazwaZapisu;
	}

	public void setNazwaZapisu(String nazwaZapisu)
	{
		this.nazwaZapisu = nazwaZapisu;
	}

	public String getKtoryGracz()
	{
		return ktoryGracz;
	}

	public void setKtoryGracz(String ktoryGracz)
	{
		this.ktoryGracz = ktoryGracz;
	}

	public ButtonNadpisany(Context context)
	{
		super(context);
	}
	
	public void ustawClickListener(final Activity zrodlo)
	{
    	this.setOnClickListener(new View.OnClickListener(){
    		
			@Override
			public void onClick(View v) {
				
		        Plansza planszaGracza1 = new Plansza();
//		        PlanszaZapisy planszaOdczutuDeserializacji1 = (PlanszaZapisy) stringToObject(getStringPlansza1());
//		        planszaGracza1.odczatajStanGryZObiektu(planszaOdczutuDeserializacji1);
		        
		        Plansza planszaGracza2 = new Plansza();
//		        PlanszaZapisy planszaOdczutuDeserializacji2 = (PlanszaZapisy) stringToObject(getStringPlansza2());
//		        planszaGracza2.odczatajStanGryZObiektu(planszaOdczutuDeserializacji2);
				
		        
		    	Intent aktivityGracz1 = new Intent(zrodlo, ActivityGracz1.class);
		    	aktivityGracz1.putExtra("planszaGracza1", planszaGracza1);
		    	aktivityGracz1.putExtra("planszaGracza2", planszaGracza2);
		    	aktivityGracz1.putExtra("zserialozowanyZapisPlanszy1", getStringPlansza1());
		    	aktivityGracz1.putExtra("zserialozowanyZapisPlanszy2", getStringPlansza2());
		    	aktivityGracz1.putExtra("ktoryGracz", getKtoryGracz());
		    	aktivityGracz1.putExtra("czyWczytywanaGra", "tak");
		    	
		    	zrodlo.startActivity(aktivityGracz1);
				
				
			}
			
    	}
    	
    	);
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
