package com.example.pum2z1;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
@SuppressWarnings("serial")
public class GlowneActivity extends Activity implements Serializable
{
	private MediaPlayer odtwarzacz;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_glowne);
		
		final ImageView imageIntro = (ImageView) findViewById(R.id.imageView1);
		
		imageIntro.setBackgroundResource(R.anim.intro);
		imageIntro.post(new Runnable() 
        {
        	@Override
        	public void run()
        	{
        		AnimationDrawable Animacja = (AnimationDrawable) imageIntro.getBackground();
        		Animacja.start();
        	}
        }
         
    );
		
		
    	odtwarzacz = MediaPlayer.create(getApplicationContext(), R.raw.theme);
    	odtwarzacz.start();
		
	}
	
	public void nowaGra(View v)
	{
		Plansza planszaGracza1 = new Plansza();

		Plansza planszaGracza2 = new Plansza();
		
    	Intent aktivityGracz1 = new Intent(this, ActivityGracz1.class);
    	aktivityGracz1.putExtra("planszaGracza1", planszaGracza1);
    	aktivityGracz1.putExtra("planszaGracza2", planszaGracza2);
    	aktivityGracz1.putExtra("czyWczytywanaGra", "nie");
    	
    	odtwarzacz.stop();
    	startActivity(aktivityGracz1);
    	
	}
	
	public void bazaDanych(View v)
	{
		odtwarzacz.start();
    	Intent aktivityDatabase = new Intent(this, DatabaseActivity.class);    	
    	startActivity(aktivityDatabase);
    	
	}
	
	
}
