package com.example.pum2z1;

import java.io.Serializable;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
@SuppressWarnings("serial")
public class Pole extends ImageView implements Serializable
{
	private int wspX,wspY,idStatku;
	private boolean shooted;
	private String type; // puste, statek, nieosiagalne
	

	public boolean isShooted()
	{
		return shooted;
	}


	public void setShooted(boolean shooted)
	{
		this.shooted = shooted;
	}


	public String getType()
	{
		return type;
	}


	public void setType(String type)
	{
		this.type = type;
	}


	public int getWspX()
	{
		return wspX;
	}


	public void setWspX(int wspX)
	{
		this.wspX = wspX;
	}


	public int getWspY()
	{
		return wspY;
	}


	public void setWspY(int wspY)
	{
		this.wspY = wspY;
	}


	public Pole(final Activity context)
	{
		super(context);
	}


	public int getIdStatku()
	{
		return idStatku;
	}


	public void setIdStatku(int idStatku)
	{
		this.idStatku = idStatku;
	}
	
	public void ustawObrazekTlaDlaPrzeciwnika()
	{
		if (!isShooted())
		{
		if (getType().equals("puste") | getType().equals("nieosiagalne")) { this.setBackgroundResource(R.drawable.woda); } 
		else if (getType().equals("statek")) { this.setBackgroundResource(R.drawable.woda); } 
		}
		else
		{
			if (getType().equals("puste") | getType().equals("nieosiagalne")) { this.setBackgroundResource(R.drawable.pudlo);} 

		}
	}
	
	public void ustawObrazekTlaDlaMnie()
	{
		if (!isShooted())
		{
		if (getType().equals("puste") | getType().equals("nieosiagalne")) { this.setBackgroundResource(R.drawable.woda); } 
		else if (getType().equals("statek")) { this.setBackgroundResource(R.drawable.statek); } 
		}
		else
		{
			if (getType().equals("puste") | getType().equals("nieosiagalne")) { this.setBackgroundResource(R.drawable.pudlo); } 
		}
	}
	
	
	public void ustawZatopionyObrazek()
	{
		this.setBackgroundResource(R.drawable.statek_trafiony_zatopiony);
	}
	
	public void ustawAnimacjeTrafionyNiezatopiony()
	{
        setBackgroundResource(R.anim.anim_traf_niezatopiony);
        post(new Runnable() 
        {
        	@Override
        	public void run()
        	{
        		AnimationDrawable Animacja = (AnimationDrawable) getBackground();
        		Animacja.start();
        	}
        }
         
    ); 
	}
	
	public void ustawWoda()
	{
		this.setBackgroundResource(R.drawable.woda);
	}
	
	public void ustaWodaPudlo()
	{
		this.setBackgroundResource(R.drawable.pudlo);
	}

}
