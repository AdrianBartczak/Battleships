package com.example.pum2z1;

import java.io.Serializable;
@SuppressWarnings("serial")
public class PlanszaZapisy implements Serializable
{
	private PoleZapisy[][] komorkaPlanszy = new PoleZapisy[10][10];

	private StatekZapisy[] tablicaStatkow = new StatekZapisy[10];
	private boolean wszystkieStatkiZniszczone, strzalTrafiony;
	public PoleZapisy[][] getKomorkaPlanszy()
	{
		return komorkaPlanszy;
	}
	public void setKomorkaPlanszy(PoleZapisy[][] komorkaPlanszy)
	{
		this.komorkaPlanszy = komorkaPlanszy;
	}
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
	private boolean kolejTegoGracza;
	
	public void ustawPole(int x, int y, PoleZapisy pole)
	{		
		komorkaPlanszy[x][y] = pole;		
	}
	public void ustawStatek(int nrStatku, StatekZapisy statek)
	{
		tablicaStatkow[nrStatku] = statek;
	}
	public void ustawListeDanegoStatku(int nrStatku, PoleZapisy element)
	{
		tablicaStatkow[nrStatku].dodajPoleStatku(element);
	}
	public StatekZapisy[] getTablicaStatkow()
	{
		return tablicaStatkow;
	}
	public void setTablicaStatkow(StatekZapisy[] tablicaStatkow)
	{
		this.tablicaStatkow = tablicaStatkow;
	}
	

}
