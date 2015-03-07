package com.example.pum2z1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("serial")
public class StatekZapisy implements Serializable
{
	private int iloscMasztow;
	private boolean zatopiony;	
	private String kierunek;
	private List<PoleZapisy> listaZajmowanychPol = new ArrayList<PoleZapisy>();
	
	public List<PoleZapisy> getListaZajmowanychPol()
	{
		return listaZajmowanychPol;
	}
	public int getIloscMasztow()
	{
		return iloscMasztow;
	}
	public void setIloscMasztow(int iloscMasztow)
	{
		this.iloscMasztow = iloscMasztow;
	}
	public boolean isZatopiony()
	{
		return zatopiony;
	}
	public void setZatopiony(boolean zatopiony)
	{
		this.zatopiony = zatopiony;
	}
	public void dodajPoleStatku(PoleZapisy pole)
	{
		this.listaZajmowanychPol.add(pole);
	}
	public String getKierunek()
	{
		return kierunek;
	}
	public void setKierunek(String kierunek)
	{
		this.kierunek = kierunek;
	}

}
