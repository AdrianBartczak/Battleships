package com.example.pum2z1;

import java.io.Serializable;
import java.util.Random;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;

@SuppressWarnings("serial")
public class Plansza implements Serializable
{

	private Pole[][] komorkaPlanszy = new Pole[10][10];
	private Statek[] tablicaStatkow = new Statek[10];
	private boolean wszystkieStatkiZniszczone, strzalTrafiony;
	private boolean kolejTegoGracza;
	
	public boolean isWszystkieStatkiZniszczone()
	{
		return wszystkieStatkiZniszczone;
	}

	public void setWszystkieStatkiZniszczone(boolean wszystkieStatkiZniszczone)
	{
		this.wszystkieStatkiZniszczone = wszystkieStatkiZniszczone;
	}
	
	public boolean isStrzalTrafiony()
	{
		return strzalTrafiony;
	}

	public void setStrzalTrafiony(boolean strzalTrafiony)
	{
		this.strzalTrafiony = strzalTrafiony;
	}
	
	public boolean isKolejTegoGracza()
	{
		return kolejTegoGracza;
	}

	public void setKolejTegoGracza(boolean kolejTegoGracza)
	{
		this.kolejTegoGracza = kolejTegoGracza;
	}
	

	public void generowaniePlanszyPrzeciwnika(final Activity activity, GridLayout siatkaPlanszy)
	{
		siatkaPlanszy.setPadding(0, 0, 0, 0);
		siatkaPlanszy.setOrientation(0);
		siatkaPlanszy.setColumnCount(10);
		siatkaPlanszy.setRowCount(10);
		
		for (int i=0; i<10; i++)
		{
			for (int j=0; j<10; j++)
			{
				final Pole pole = new Pole(activity);
				pole.setWspX(j);
				pole.setWspY(i);
				pole.setType("puste");
				pole.setShooted(false);
				

				pole.setOnClickListener(new OnClickListener()
				{
						@Override
						public void onClick(View v)
						{			
							TextView wspX = (TextView) activity.findViewById(R.id.wspX);
							TextView wspY = (TextView) activity.findViewById(R.id.wspY);
							
							if (wspX.getText().equals("")) {
								
						        pole.setBackgroundResource(R.anim.animacja);
						        pole.post(new Runnable() 
						        {
						        	@Override
						        	public void run()
						        	{
						        		AnimationDrawable Animacja = (AnimationDrawable) pole.getBackground();
						        		Animacja.start();
						        	}
						        }
						         
						    ); 
							} 
							else
							{
								komorkaPlanszy[Integer.parseInt((String) wspX.getText())][Integer.parseInt((String) wspY.getText())].clearAnimation();
								komorkaPlanszy[Integer.parseInt((String) wspX.getText())][Integer.parseInt((String) wspY.getText())].ustawObrazekTlaDlaPrzeciwnika();
						        pole.setBackgroundResource(R.anim.animacja);
						        pole.post(new Runnable() 
						        {
						        	@Override
						        	public void run()
						        	{
						        		AnimationDrawable Animacja = (AnimationDrawable) pole.getBackground();
						        		Animacja.start();
						        	}
						        }
						         
						    );
								
							}
														
							wspX.setText(String.valueOf(pole.getWspX()));						
							wspY.setText(String.valueOf(pole.getWspY()));
							
						}
				});
		
				
				pole.ustawObrazekTlaDlaPrzeciwnika();
				siatkaPlanszy.addView(pole);
				
				GridLayout.LayoutParams parametry = (GridLayout.LayoutParams) pole.getLayoutParams();
				parametry.width = 100;
				parametry.height = 100; 
				parametry.setMargins(1, 1, 1, 1);
				pole.setLayoutParams(parametry);
				
				komorkaPlanszy[j][i] = pole;
				
			}
			
		}
	}
	
	public void generowaniePodgladuMojejPlanszy(final Activity activity, GridLayout siatkaPlanszy)
	{
		
		siatkaPlanszy.setPadding(0, 0, 0, 0);
		siatkaPlanszy.setOrientation(0);
		siatkaPlanszy.setColumnCount(10);
		siatkaPlanszy.setRowCount(10);
     	siatkaPlanszy.removeAllViews();
     	
		for (int i=0; i<10; i++)
		{
			for (int j=0; j<10; j++)
			{
				ImageView obrazek = new ImageView(activity);
				
					if (!komorkaPlanszy[j][i].isShooted())
					{
					if (komorkaPlanszy[j][i].getType().equals("puste") | komorkaPlanszy[j][i].getType().equals("nieosiagalne")) { obrazek.setBackgroundResource(R.drawable.woda); } 
					else if (komorkaPlanszy[j][i].getType().equals("statek")) { obrazek.setBackgroundResource(R.drawable.statek); } 
					}
					else
					{
						if (komorkaPlanszy[j][i].getType().equals("puste") | komorkaPlanszy[j][i].getType().equals("nieosiagalne")) { obrazek.setBackgroundResource(R.drawable.pudlo); } 
						else if (komorkaPlanszy[j][i].getType().equals("statek")) { obrazek.setBackgroundResource(R.drawable.statek_trafiony_zatopiony); } 
					}
			
			siatkaPlanszy.addView(obrazek);
			
			GridLayout.LayoutParams parametry = (GridLayout.LayoutParams) obrazek.getLayoutParams();
			parametry.width = 100;
			parametry.height = 100; 
			parametry.setMargins(1, 1, 1, 1);
			obrazek.setLayoutParams(parametry);
				

			}
		}

	}

	public PlanszaZapisy przepiszStanGryDoObiektuZapisu(PlanszaZapisy planszaDoZapisu)
	{
		
		for (int i = 0; i<tablicaStatkow.length; i++)
		{			
			StatekZapisy statekZapisy = new StatekZapisy();
			statekZapisy.setIloscMasztow(tablicaStatkow[i].getIloscMasztow());
			statekZapisy.setKierunek(tablicaStatkow[i].getKierunek());
			statekZapisy.setZatopiony(tablicaStatkow[i].isZatopiony());
			
			planszaDoZapisu.ustawStatek(i, statekZapisy);
			
		}
		
		for (int i=0; i<10; i++)
		{
			for (int j=0; j<10; j++)
			{
				PoleZapisy nowePoleDoZapisu = new PoleZapisy();
				
				nowePoleDoZapisu.setWspX(j);
				nowePoleDoZapisu.setWspY(i);
				nowePoleDoZapisu.setType(komorkaPlanszy[j][i].getType());
				nowePoleDoZapisu.setShooted(komorkaPlanszy[j][i].isShooted());
				nowePoleDoZapisu.setIdStatku(komorkaPlanszy[j][i].getIdStatku());

				if (komorkaPlanszy[j][i].getType().equals("statek"))
				{
					planszaDoZapisu.getTablicaStatkow()[nowePoleDoZapisu.getIdStatku()].dodajPoleStatku(nowePoleDoZapisu);
				}
				planszaDoZapisu.ustawPole(j, i, nowePoleDoZapisu);
			}
		}
		
		planszaDoZapisu.setStrzalTrafiony(strzalTrafiony);
		planszaDoZapisu.setStrzalTrafiony(wszystkieStatkiZniszczone);
		planszaDoZapisu.setStrzalTrafiony(kolejTegoGracza);
	
		return planszaDoZapisu;
	}
	
	public void odczatajStanGryZObiektu(PlanszaZapisy planszaOdczytywana)
	{
		
		for (int i = 0; i<tablicaStatkow.length; i++)
		{
			Statek statek = new Statek();
			statek.setIloscMasztow(planszaOdczytywana.getTablicaStatkow()[i].getIloscMasztow());
			statek.setKierunek(planszaOdczytywana.getTablicaStatkow()[i].getKierunek());
			statek.setZatopiony(planszaOdczytywana.getTablicaStatkow()[i].isZatopiony());
			statek.getListaZajmowanychPol().clear();
//			tablicaStatkow[i].setKierunek(planszaOdczytywana.getTablicaStatkow()[i].getKierunek());
//			tablicaStatkow[i].setZatopiony();
			tablicaStatkow[i] = statek;
		}
		
		for (int i=0; i<10; i++)
		{
			for (int j=0; j<10; j++)
			{				
				komorkaPlanszy[j][i].setType(planszaOdczytywana.getKomorkaPlanszy()[j][i].getType());
				komorkaPlanszy[j][i].setShooted(planszaOdczytywana.getKomorkaPlanszy()[j][i].isShooted());
				komorkaPlanszy[j][i].setIdStatku(planszaOdczytywana.getKomorkaPlanszy()[j][i].getIdStatku());
//				komorkaPlanszy[j][i].ustawObrazekTlaDlaMnie();
				

				if (planszaOdczytywana.getKomorkaPlanszy()[j][i].getType().equals("statek"))
				{
					tablicaStatkow[planszaOdczytywana.getKomorkaPlanszy()[j][i].getIdStatku()].dodajPoleStatku(komorkaPlanszy[j][i]);				
				}
			}
		}
		
		this.setStrzalTrafiony(strzalTrafiony);
		this.setStrzalTrafiony(wszystkieStatkiZniszczone);
		this.setStrzalTrafiony(kolejTegoGracza);
		
	}
	
	public void naniesZmianyPoOdczycieNaPlanszePrzeciwnika()
	{
		
		for (int i=0; i<10; i++)
		{
			for (int j=0; j<10; j++)
			{
				if (komorkaPlanszy[j][i].isShooted())
				{
					if (komorkaPlanszy[j][i].getType().equals("puste") | komorkaPlanszy[j][i].getType().equals("nieosiagalne"))
					{
						komorkaPlanszy[j][i].ustaWodaPudlo();
					}
					else
					{
//						if (tablicaStatkow[komorkaPlanszy[j][i].getId()].isZatopiony())
//						{
							komorkaPlanszy[j][i].ustawZatopionyObrazek();
//							tablicaStatkow[komorkaPlanszy[j][i].getId()].zamienObrazkiNaZatopiony();
//						} else {
//							komorkaPlanszy[j][i].ustawAnimacjeTrafionyNiezatopiony();
//						}
					}
				}
				else
				{
					komorkaPlanszy[j][i].ustawWoda();
				}
//				komorkaPlanszy[j][i].ustawZatopionyObrazek();
				
			}
		}
	}
	
	public void losoweUstawienieStatkow()
	{
		
		int wspX = 0, wspY = 0;
		int polozenie = 0;  // 0 - poziom 1 - pion
		int wielkoscOkretu = 4;
		boolean znalezionoPozycjeStatku;

		Random random = new Random();


		
		for (int licznikStatkow = 0; licznikStatkow<10; licznikStatkow++)
		{
			if (licznikStatkow==0) wielkoscOkretu = 4;
			if (licznikStatkow>0 & licznikStatkow<=2) wielkoscOkretu = 3;
			if (licznikStatkow>2 & licznikStatkow<=5) wielkoscOkretu = 2;
			if (licznikStatkow>5) wielkoscOkretu = 1;
		
			znalezionoPozycjeStatku = false;
			
			while (!znalezionoPozycjeStatku)		
			{
				znalezionoPozycjeStatku = true;
			
			polozenie =random.nextInt(2);
		

	
			if (polozenie==0) // losowanie poziom H O R I Z O N T A L
			{
				
				wspX = random.nextInt(10-wielkoscOkretu);
				wspY = random.nextInt(10);

				for (int i=0; i<wielkoscOkretu; i++)
				{

					if (!komorkaPlanszy[wspX+i][wspY].getType().equals("puste"))
					{
						znalezionoPozycjeStatku = false; break;				
					}
				
				}
			}
			else // losowanie pion V E R T I C A L 
			{
				wspX = random.nextInt(10);
				wspY = random.nextInt(10-wielkoscOkretu);
			
				for (int i=0; i<wielkoscOkretu; i++)
				{
					if (!komorkaPlanszy[wspX][wspY+i].getType().equals("puste"))
					{
						znalezionoPozycjeStatku = false; break;					
					}
				
				}

			}
		
		}
			String polozenieStatku = "poziome";
			
			switch (polozenie)
			{
			case 0: polozenieStatku = "poziome";
				
				break;
			case 1: polozenieStatku = "pionowe";

			}
		
		ustawStatek(licznikStatkow, wielkoscOkretu, polozenieStatku, wspX, wspY);
		
		}
		
	}
	
	public void ustawStatek(int nrTablicyStatku,int liczbaMasztow, String polozenie, int pozPoczX, int pozPoczY)
	{
		Statek statek = new Statek();
		statek.setIloscMasztow(liczbaMasztow);
		statek.setKierunek(polozenie);
		
		
		if (polozenie.equals("poziome"))
		{
				
			ustawNiemozliweDoTrafieniaPoziomo(pozPoczX,pozPoczY,liczbaMasztow);
				
				for (int i=0; i<liczbaMasztow; i++)
				{
					statek.dodajPoleStatku(komorkaPlanszy[pozPoczX+i][pozPoczY]);
					komorkaPlanszy[pozPoczX+i][pozPoczY].setType("statek");
					komorkaPlanszy[pozPoczX+i][pozPoczY].ustawObrazekTlaDlaPrzeciwnika();
					komorkaPlanszy[pozPoczX+i][pozPoczY].setIdStatku(nrTablicyStatku);					
				}
	
		} 
		else
		{
			ustawNiemozliweDoTrafieniaPionowo(pozPoczX,pozPoczY,liczbaMasztow);
			
			for (int i=0; i<liczbaMasztow; i++)
			{
				statek.dodajPoleStatku(komorkaPlanszy[pozPoczX][pozPoczY+i]);
				komorkaPlanszy[pozPoczX][pozPoczY+i].setType("statek");
				komorkaPlanszy[pozPoczX][pozPoczY+i].ustawObrazekTlaDlaPrzeciwnika();
				komorkaPlanszy[pozPoczX][pozPoczY+i].setIdStatku(nrTablicyStatku);
				
			}
		}

		tablicaStatkow[nrTablicyStatku] = statek;
	}
	
	public void ustawNiemozliweDoTrafieniaPoziomo(int wspX,int wspY, int liczbaMasztow)
	{
		int wspXReducted = wspX - 1;
		int wspYReducted = wspY - 1;
		
		for (int j=wspYReducted; j<wspYReducted+3; j++)
		{
			
			for (int i=wspXReducted; i<=wspXReducted+liczbaMasztow+1; i++)
			{
				try
				{
					komorkaPlanszy[i][j].setType("nieosiagalne");
				}
				catch (Exception e) {}		
			}
	
		}
	}
	
	public void ustawNiemozliweDoTrafieniaPionowo(int wspX,int wspY, int liczbaMasztow)
	{
		int wspXReducted = wspX - 1;
		int wspYReducted = wspY - 1;
		
		for (int j=wspYReducted; j<wspYReducted+liczbaMasztow+2; j++)
		{
			
			for (int i=wspXReducted; i<=wspXReducted+2; i++)
			{
				try
				{
					komorkaPlanszy[i][j].setType("nieosiagalne");
				}
				catch (Exception e) {}		
			}
	
		}
	}
	
	public String oddajStrzal(int pozX, int pozY)
	{
		String wynik = "";
		setStrzalTrafiony(false);
		komorkaPlanszy[pozX][pozY].setShooted(true);
		
		if (komorkaPlanszy[pozX][pozY].getType().equals("statek"))
		{
			if (tablicaStatkow[komorkaPlanszy[pozX][pozY].getIdStatku()].sprawdzCzyZatopiony())
			{
				wynik = "Trafiony zatopiony! ";
				sprawdzCzyWszystkieStatkiZniszczone();
				setStrzalTrafiony(true);
								
			}
			else {
			     	wynik = "Trafiony niezatopiony!";
			     	komorkaPlanszy[pozX][pozY].ustawAnimacjeTrafionyNiezatopiony();
			     	setStrzalTrafiony(true);
				 }
				
			
		}
		else
		{
			wynik = "Pud³o!";
			komorkaPlanszy[pozX][pozY].ustawObrazekTlaDlaPrzeciwnika();
		}
		
		komorkaPlanszy[pozX][pozY].setClickable(false);
		
		return wynik;
	}

	public void sprawdzCzyWszystkieStatkiZniszczone()
	{
		for (int i=0; i<tablicaStatkow.length; i++)
		{
			if (!tablicaStatkow[i].isZatopiony())
			{
				setWszystkieStatkiZniszczone(false);
				break;
			}
			else setWszystkieStatkiZniszczone(true);
		}
	}
	
	public int zliczNiezniszczoneStatki()
	{
		int liczbaStatkow = 0;
		for (int i=0; i<tablicaStatkow.length; i++)
		{
			if (!tablicaStatkow[i].isZatopiony())
			{
				liczbaStatkow++;
			}
		}
		
		
		
		return liczbaStatkow;
		
	}
	
	public void wyczyscPlanszeZeStatkow()
	{
		for (int i=0; i<tablicaStatkow.length; i++)
		{
			tablicaStatkow[i] = null;
		}
		
		for (int i=0; i<10; i++)
		{
			for (int j=0; j<10; j++)
			{
				komorkaPlanszy[i][j].setType("puste");
				komorkaPlanszy[i][j].ustawObrazekTlaDlaMnie();
			}
		}

	}


	

}
