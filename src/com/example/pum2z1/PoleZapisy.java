package com.example.pum2z1;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PoleZapisy implements Serializable
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
	public int getIdStatku()
	{
		return idStatku;
	}
	public void setIdStatku(int idStatku)
	{
		this.idStatku = idStatku;
	}
}
