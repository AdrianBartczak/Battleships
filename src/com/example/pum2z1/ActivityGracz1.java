package com.example.pum2z1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Base64;

//import android.util.Base64InputStream;

import android.util.Base64InputStream;
import android.util.Base64OutputStream;

import java.io.Serializable;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



@SuppressWarnings("serial")
public class ActivityGracz1 extends Activity implements Serializable
{

	private GridLayout siatkaPlanszyMoja;
	private GridLayout siatkaPlanszyPrzeciwnika;
	private GridLayout siatkaPlanszyGracz2Moja;
	private GridLayout siatkaPlanszyGracz2Przeciwnika;
	
	private RelativeLayout widokGracz1;
	private RelativeLayout widokGracz2;
	
	private boolean ustawionoStatkiGracz1 = false, ustawionoStatkiGracz2 = false;
	
	private Plansza planszaPrzeciwnika;
	private Plansza planszaMoja;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gracz1);
		
		String czyWczytywanaGra = (String) getIntent().getSerializableExtra("czyWczytywanaGra");
		
		if (czyWczytywanaGra.equals("nie"))
		{
			widokGracz1 = (RelativeLayout) findViewById(R.id.widokGracz1);
			
			siatkaPlanszyPrzeciwnika = (GridLayout)findViewById(R.id.siatkaPlanszyPrzeciwnika);
			siatkaPlanszyMoja = (GridLayout)findViewById(R.id.siatkaPlanszyMoja);
		
			siatkaPlanszyMoja.setVisibility(View.GONE);
		
		widokGracz2 = (RelativeLayout)findViewById(R.id.widokGracz2);

			siatkaPlanszyGracz2Przeciwnika = (GridLayout)findViewById(R.id.siatkaPlanszyGracz2Przeciwnika);
			siatkaPlanszyGracz2Moja = (GridLayout)findViewById(R.id.siatkaPlanszyGracz2Moja);
		
			siatkaPlanszyGracz2Moja.setVisibility(View.GONE);
		widokGracz2.setVisibility(View.GONE);
		
		
		planszaPrzeciwnika = (Plansza) getIntent().getSerializableExtra("planszaGracza1");
		planszaPrzeciwnika.generowaniePlanszyPrzeciwnika(this, siatkaPlanszyPrzeciwnika);
		
		planszaMoja = (Plansza) getIntent().getSerializableExtra("planszaGracza2");
		planszaMoja.generowaniePlanszyPrzeciwnika(this, siatkaPlanszyGracz2Przeciwnika);
			
		}
		else
		{
			
			widokGracz1 = (RelativeLayout) findViewById(R.id.widokGracz1);
			
			siatkaPlanszyPrzeciwnika = (GridLayout)findViewById(R.id.siatkaPlanszyPrzeciwnika);
			siatkaPlanszyMoja = (GridLayout)findViewById(R.id.siatkaPlanszyMoja);
		
			siatkaPlanszyMoja.setVisibility(View.GONE);
		
		widokGracz2 = (RelativeLayout)findViewById(R.id.widokGracz2);

			siatkaPlanszyGracz2Przeciwnika = (GridLayout)findViewById(R.id.siatkaPlanszyGracz2Przeciwnika);
			siatkaPlanszyGracz2Moja = (GridLayout)findViewById(R.id.siatkaPlanszyGracz2Moja);
		
			siatkaPlanszyGracz2Moja.setVisibility(View.GONE);
		widokGracz2.setVisibility(View.GONE);
		
		
		planszaPrzeciwnika = (Plansza) getIntent().getSerializableExtra("planszaGracza1");
		planszaPrzeciwnika.generowaniePlanszyPrzeciwnika(this, siatkaPlanszyPrzeciwnika);
		
		String zserialozowanyZapisPlanszy1 = (String) getIntent().getSerializableExtra("zserialozowanyZapisPlanszy1");		
        PlanszaZapisy planszaOdczutuDeserializacji1 = (PlanszaZapisy) stringToObject(zserialozowanyZapisPlanszy1);
        
        planszaPrzeciwnika.odczatajStanGryZObiektu(planszaOdczutuDeserializacji1);
        planszaPrzeciwnika.generowaniePodgladuMojejPlanszy(this, siatkaPlanszyGracz2Moja);
        


		planszaMoja = (Plansza) getIntent().getSerializableExtra("planszaGracza2");
		planszaMoja.generowaniePlanszyPrzeciwnika(this, siatkaPlanszyGracz2Przeciwnika);
		
        String zserialozowanyZapisPlanszy2 = (String) getIntent().getSerializableExtra("zserialozowanyZapisPlanszy2");	
        PlanszaZapisy planszaOdczutuDeserializacji2 = (PlanszaZapisy) stringToObject(zserialozowanyZapisPlanszy2);
        planszaMoja.odczatajStanGryZObiektu(planszaOdczutuDeserializacji2);
        planszaMoja.generowaniePodgladuMojejPlanszy(this, siatkaPlanszyMoja);
        
        planszaMoja.naniesZmianyPoOdczycieNaPlanszePrzeciwnika();
        planszaPrzeciwnika.naniesZmianyPoOdczycieNaPlanszePrzeciwnika();

        
        setUstawionoStatkiGracz1(true);
        setUstawionoStatkiGracz2(true);
        Button losujGre = (Button) findViewById(R.id.btnlosujStatki);
        losujGre.setVisibility(View.GONE);
        Button strzelaj = (Button) findViewById(R.id.oddajStrzal);
        strzelaj.setClickable(true);
        
			
		}
				
		
	}
	public void oddajStrzal(View v)
	{
		TextView wspX = (TextView) findViewById(R.id.wspX);
		TextView wspY = (TextView) findViewById(R.id.wspY);
		TextView liczbaStatkow = (TextView) findViewById(R.id.textViewLiczbaPozostalychStatkowWroga);
		Button btnOddajStrzal = (Button) findViewById(R.id.oddajStrzal);
		Button btnKolejnaTura = (Button) findViewById(R.id.koniecTury);
	
	if (wspX.getText().equals("") || wspY.getText().equals("")) { } else
	{
		
		if(widokGracz1.isShown())
		{
	
			String wynik = planszaPrzeciwnika.oddajStrzal(Integer.parseInt((String) wspX.getText()), Integer.parseInt((String) wspY.getText()));
			
			if (wynik.equalsIgnoreCase("Pud³o!")) {
				
				final MediaPlayer odtwarzacz = MediaPlayer.create(getApplicationContext(), R.raw.water_miss);
				odtwarzacz.setOnCompletionListener(new OnCompletionListener()
				{
					
					@Override
					public void onCompletion(MediaPlayer mp)
					{
						odtwarzacz.stop();
						
					}
				});
		    	odtwarzacz.start();
				
			}
			else
			{
				final MediaPlayer odtwarzacz = MediaPlayer.create(getApplicationContext(), R.raw.cannon);
				odtwarzacz.setOnCompletionListener(new OnCompletionListener()
				{
					
					@Override
					public void onCompletion(MediaPlayer mp)
					{
						odtwarzacz.stop();
						
					}
				});
		    	odtwarzacz.start();
			
			}
		Toast.makeText(this,wynik, Toast.LENGTH_LONG).show();
		
		liczbaStatkow.setText("Liczba statków wroga: "+String.valueOf(planszaPrzeciwnika.zliczNiezniszczoneStatki()));
		
		if (planszaPrzeciwnika.isStrzalTrafiony()) { wspX.setText(""); wspY.setText(""); } 
		else {  wspX.setText(""); wspY.setText(""); btnOddajStrzal.setClickable(false); btnKolejnaTura.setClickable(true);}
		
		if (planszaPrzeciwnika.isWszystkieStatkiZniszczone())
		{
	        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

	        dlgAlert.setMessage("Wszystkie statki wroga zosta³y zniszczone!");
	        dlgAlert.setTitle("Gratulacje Komandorze");
	        dlgAlert.setPositiveButton("OK", null);
	        dlgAlert.setCancelable(true);
	        dlgAlert.create().show();	
		}
		}
		else
		{
			String wynik = planszaMoja.oddajStrzal(Integer.parseInt((String) wspX.getText()), Integer.parseInt((String) wspY.getText()));
			
			if (wynik.equalsIgnoreCase("Pud³o!")) {
				
				final MediaPlayer odtwarzacz = MediaPlayer.create(getApplicationContext(), R.raw.water_miss);
				odtwarzacz.setOnCompletionListener(new OnCompletionListener()
				{
					
					@Override
					public void onCompletion(MediaPlayer mp)
					{
						odtwarzacz.stop();
						
					}
				});
		    	odtwarzacz.start();
				
			}
			else
			{
				final MediaPlayer odtwarzacz = MediaPlayer.create(getApplicationContext(), R.raw.cannon);
				odtwarzacz.setOnCompletionListener(new OnCompletionListener()
				{
					
					@Override
					public void onCompletion(MediaPlayer mp)
					{
						odtwarzacz.stop();
						
					}
				});
		    	odtwarzacz.start();
			
			}
		Toast.makeText(this,wynik, Toast.LENGTH_LONG).show();
			
			liczbaStatkow.setText("Liczba statków wroga: "+String.valueOf(planszaMoja.zliczNiezniszczoneStatki()));
			
			if (planszaMoja.isStrzalTrafiony()) { wspX.setText(""); wspY.setText(""); } 
			else {  wspX.setText(""); wspY.setText(""); btnOddajStrzal.setClickable(false); btnKolejnaTura.setClickable(true); }
			
			if (planszaMoja.isWszystkieStatkiZniszczone())
			{
		        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

		        dlgAlert.setMessage("Wszystkie statki wroga zosta³y zniszczone!");
		        dlgAlert.setTitle("Gratulacje Komandorze");
		        dlgAlert.setPositiveButton("OK", null);
		        dlgAlert.setCancelable(true);
		        dlgAlert.create().show();	
			}
			
		}
	}
	}
	

	public void przelaczPlansze(View v)
	{
		TextView ktoraPlansza = (TextView) findViewById(R.id.textViewTrybWidokuPlanszy);
		
		if (widokGracz1.isShown())
		{

		if (siatkaPlanszyPrzeciwnika.isShown())
		{
			siatkaPlanszyPrzeciwnika.setVisibility(View.GONE);	
			planszaMoja.generowaniePodgladuMojejPlanszy(this, siatkaPlanszyMoja);
			siatkaPlanszyMoja.setVisibility(View.VISIBLE);
			ktoraPlansza.setText("MOJE USTAWIENIE");
			ktoraPlansza.setTextColor(getResources().getColor(R.color.yellow));
			
		}else
		{
			siatkaPlanszyPrzeciwnika.setVisibility(View.VISIBLE);
			siatkaPlanszyMoja.setVisibility(View.GONE);	
			ktoraPlansza.setText("PLANSZA PRZECIWNIKA");
			ktoraPlansza.setTextColor(getResources().getColor(R.color.red));
		}
		}
		else if (widokGracz2.isShown())
		{

			if (siatkaPlanszyGracz2Przeciwnika.isShown())
			{
				siatkaPlanszyGracz2Przeciwnika.setVisibility(View.GONE);	
				planszaPrzeciwnika.generowaniePodgladuMojejPlanszy(this, siatkaPlanszyGracz2Moja);
				siatkaPlanszyGracz2Moja.setVisibility(View.VISIBLE);			
				ktoraPlansza.setText("MOJE USTAWIENIE");
				ktoraPlansza.setTextColor(getResources().getColor(R.color.yellow));
				
			}else
			{
				siatkaPlanszyGracz2Przeciwnika.setVisibility(View.VISIBLE);
				siatkaPlanszyGracz2Moja.setVisibility(View.GONE);	
				ktoraPlansza.setText("PLANSZA PRZECIWNIKA");
				ktoraPlansza.setTextColor(getResources().getColor(R.color.red));
			}
		}

		
		//Toast.makeText(this,planszaPrzeciwnika.oddajStrzal(Integer.parseInt((String) wspX.getText()), Integer.parseInt((String) wspY.getText())), Toast.LENGTH_LONG).show();	

	}
	
	public void nastepnyGracz(View v)
	{		
	
		TextView wspX = (TextView) findViewById(R.id.wspX);
		TextView wspY = (TextView) findViewById(R.id.wspY);
		
		TextView liczbaStatkow = (TextView) findViewById(R.id.textViewLiczbaPozostalychStatkowWroga);
		
		TextView tytGracz1 = (TextView) findViewById(R.id.wievtytgracz1);
		TextView tytGracz2 = (TextView) findViewById(R.id.wievtytgracz2);
		
		TextView ktoraPlansza = (TextView) findViewById(R.id.textViewTrybWidokuPlanszy);
		
		Button btnOddajStrzal = (Button) findViewById(R.id.oddajStrzal);
		Button btnKolejnaTura = (Button) findViewById(R.id.koniecTury);
		Button btnLosujStatki = (Button) findViewById(R.id.btnlosujStatki);
		
		btnOddajStrzal.setClickable(true);
		btnKolejnaTura.setClickable(false);
		
		if (widokGracz1.isShown())
		{
			if (isUstawionoStatkiGracz2())
			{
			widokGracz1.setVisibility(View.GONE);
			widokGracz2.setVisibility(View.VISIBLE);
			wspX.setText("");
			wspY.setText("");	
			
			btnLosujStatki.setVisibility(View.GONE);
				if (siatkaPlanszyGracz2Przeciwnika.isShown()) 
				{
					ktoraPlansza.setText("PLANSZA PRZECIWNIKA");
					ktoraPlansza.setTextColor(getResources().getColor(R.color.red));
				}
				else
				{
					ktoraPlansza.setText("MOJE USTAWIENIE");
					ktoraPlansza.setTextColor(getResources().getColor(R.color.yellow));				
				}
			
			tytGracz1.setVisibility(View.GONE);
			tytGracz2.setVisibility(View.VISIBLE);
			liczbaStatkow.setText("Liczba statków wroga: "+String.valueOf(planszaMoja.zliczNiezniszczoneStatki()));
			}
			else
			{
				widokGracz1.setVisibility(View.GONE);
				widokGracz2.setVisibility(View.VISIBLE);
				wspX.setText("");
				wspY.setText("");	
				
				 btnLosujStatki.setVisibility(View.VISIBLE);
				 
					ktoraPlansza.setText("TRYB USTAWIANIA STATKÓW");
					ktoraPlansza.setTextColor(getResources().getColor(R.color.yellow));	
				 
				 btnOddajStrzal.setClickable(false);
				 
					tytGracz1.setVisibility(View.GONE);
					tytGracz2.setVisibility(View.VISIBLE);
					liczbaStatkow.setText("Liczba statków wroga: "+String.valueOf(planszaMoja.zliczNiezniszczoneStatki()));
			}
		}
		else
		{
			if (isUstawionoStatkiGracz1())
			{
			widokGracz1.setVisibility(View.VISIBLE);
			widokGracz2.setVisibility(View.GONE);			
			wspX.setText("");
			wspY.setText("");	
			
			if (isUstawionoStatkiGracz1()) btnLosujStatki.setVisibility(View.GONE); else btnLosujStatki.setVisibility(View.VISIBLE);
			
			if (siatkaPlanszyPrzeciwnika.isShown()) 
			{
				ktoraPlansza.setText("PLANSZA PRZECIWNIKA");
				ktoraPlansza.setTextColor(getResources().getColor(R.color.red));
			}
			else
			{
				ktoraPlansza.setText("MOJE USTAWIENIE");
				ktoraPlansza.setTextColor(getResources().getColor(R.color.yellow));				
			}
			
			tytGracz2.setVisibility(View.GONE);
			tytGracz1.setVisibility(View.VISIBLE);
			liczbaStatkow.setText("Liczba statków wroga: "+String.valueOf(planszaPrzeciwnika.zliczNiezniszczoneStatki()));
			}
			else
			{
				widokGracz1.setVisibility(View.VISIBLE);
				widokGracz2.setVisibility(View.GONE);	
				
				wspX.setText("");
				wspY.setText("");	
				
				ktoraPlansza.setText("TRYB USTAWIANIA STATKÓW");
				ktoraPlansza.setTextColor(getResources().getColor(R.color.yellow));	
				
				tytGracz2.setVisibility(View.GONE);
				tytGracz1.setVisibility(View.VISIBLE);
				
				liczbaStatkow.setText("Liczba statków wroga: "+String.valueOf(planszaPrzeciwnika.zliczNiezniszczoneStatki()));
				
				 btnLosujStatki.setVisibility(View.VISIBLE);
				 
				 btnOddajStrzal.setClickable(false);
			}
		}

	}
	
	public void losujPolozenie(View v)
	{
		
		Button btnKolejnaTura = (Button) findViewById(R.id.koniecTury);
		
		if (widokGracz1.isShown())
		{ 
		planszaMoja.wyczyscPlanszeZeStatkow();
		planszaMoja.losoweUstawienieStatkow();
		
		setUstawionoStatkiGracz1(true);
		
		siatkaPlanszyPrzeciwnika.setVisibility(View.GONE);	
		planszaMoja.generowaniePodgladuMojejPlanszy(this, siatkaPlanszyMoja);
		siatkaPlanszyMoja.setVisibility(View.VISIBLE);	
		btnKolejnaTura.setClickable(true);
		
		}
		else 
		{
			planszaPrzeciwnika.wyczyscPlanszeZeStatkow();
			planszaPrzeciwnika.losoweUstawienieStatkow();
			setUstawionoStatkiGracz2(true);
			
			siatkaPlanszyGracz2Przeciwnika.setVisibility(View.GONE);	
			planszaPrzeciwnika.generowaniePodgladuMojejPlanszy(this, siatkaPlanszyGracz2Moja);
			siatkaPlanszyGracz2Moja.setVisibility(View.VISIBLE);
			btnKolejnaTura.setClickable(true);
		}
	}
	public boolean isUstawionoStatkiGracz1()
	{
		return ustawionoStatkiGracz1;
	}
	public void setUstawionoStatkiGracz1(boolean ustawionoStatkiGracz1)
	{
		this.ustawionoStatkiGracz1 = ustawionoStatkiGracz1;
	}
	public boolean isUstawionoStatkiGracz2()
	{
		return ustawionoStatkiGracz2;
	}
	public void setUstawionoStatkiGracz2(boolean ustawionoStatkiGracz2)
	{
		this.ustawionoStatkiGracz2 = ustawionoStatkiGracz2;
	}
	

	public void zapiszStanGry(View v)
	{  
    	final Dialog noweOknoDialogowe = new Dialog(ActivityGracz1.this);
    	//podobnie jak np dla g³ównego okna wskazujemy to co ma byæ zawartoœci¹ - np: setContentView(R.layout.activity_main);
    	noweOknoDialogowe.setContentView(R.layout.okienko_dialogowe);
    	noweOknoDialogowe.setTitle("Podaj nazwê zapisu gry : ");
    	
    	final Button btnZapiszGreWOkienku = (Button) noweOknoDialogowe.findViewById(R.id.buttonZapisz);
    	btnZapiszGreWOkienku.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View v)
			{
				EditText editNazwaZapisu = (EditText) noweOknoDialogowe.findViewById(R.id.editNazwaZapisu);
				
				String nazwaZapisu = editNazwaZapisu.getText().toString();
				
		        PlanszaZapisy planszaZapisyGracz1 = new PlanszaZapisy();		        
		        planszaZapisyGracz1 = planszaMoja.przepiszStanGryDoObiektuZapisu(planszaZapisyGracz1);
		        
		        PlanszaZapisy planszaZapisyGracz2 = new PlanszaZapisy();	
		        planszaZapisyGracz2 = planszaPrzeciwnika.przepiszStanGryDoObiektuZapisu(planszaZapisyGracz2);
				
				
		        String zserializowanyObiektPlanszy1 = objectToString(planszaZapisyGracz1);
		        String zserializowanyObiektPlanszy2 = objectToString(planszaZapisyGracz2);
		                
		        SQLiteDatabase  Bazadanych= openOrCreateDatabase("BazaStatki", MODE_PRIVATE, null);
		        Bazadanych.execSQL("CREATE TABLE IF NOT EXISTS GRA (gra_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,gra_nazwa TEXT,gra_ktorygracz TEXT, gra_plansza1 VARCHAR, gra_plansza2 VARCHAR)");        
		        ContentValues values = new ContentValues();
		        values.put("gra_plansza1", zserializowanyObiektPlanszy1);
		        values.put("gra_plansza2", zserializowanyObiektPlanszy2);
		        values.put("gra_nazwa", nazwaZapisu);
		        
		        if(widokGracz1.isShown()) { values.put("gra_ktorygracz", "gracz1"); } else { values.put("gra_ktorygracz", "gracz2"); }
		        
		        Bazadanych.insert("GRA", null, values);
			    
		        Bazadanych.close();
		    
				
				
				noweOknoDialogowe.cancel();
				
			}
		});
    	
    	noweOknoDialogowe.show();

	}
	
	  
    public static String objectToString(Serializable obj) {
        try {

          ByteArrayOutputStream baos = new ByteArrayOutputStream();

          ObjectOutputStream oos = new ObjectOutputStream(

              new Base64OutputStream(baos, Base64.NO_PADDING

                  | Base64.NO_WRAP));

          oos.writeObject(obj);
          oos.close();

          return baos.toString("UTF-8");

        } catch (IOException e) {

          e.printStackTrace();

        }

        return null;

}
    
	  public static Object stringToObject(String str) {

          try {

            return new ObjectInputStream(new Base64InputStream(

                new ByteArrayInputStream(str.getBytes()), Base64.NO_PADDING

                    | Base64.NO_WRAP)).readObject();


          } catch (Exception e) {

            e.printStackTrace();

          }

          return null;

        }
}
