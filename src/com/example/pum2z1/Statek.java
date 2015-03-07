package com.example.pum2z1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("serial")
public class Statek implements Serializable
{
	private int iloscMasztow;
	private boolean zatopiony;	
	private String kierunek;
	private List<Pole> listaZajmowanychPol = new ArrayList<Pole>();
	
	public List<Pole> getListaZajmowanychPol()
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
	public void dodajPoleStatku(Pole pole)
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
	
	public boolean sprawdzCzyZatopiony()
	{
		boolean flag = true;
		for ( Pole pole : listaZajmowanychPol )
		{
			if (!pole.isShooted())
			{
				flag = false;
			}
		}
		
		if (flag) { setZatopiony(flag); zamienObrazkiNaZatopiony(); }
		return flag;
	}
	
	public void zamienObrazkiNaZatopiony()
	{
		for ( Pole pole : listaZajmowanychPol )
		{
			pole.ustawZatopionyObrazek();
		}
		
	}

}
