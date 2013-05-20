package com.ddieeeumh.actimel.ilibici;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.ddieeeumh.actimel.ilibici.db.AppDataContext;
import com.ddieeeumh.actimel.ilibici.db.Puestos;
import com.mobandme.ada.Entity;
import com.mobandme.ada.exceptions.AdaFrameworkException;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try{
			SharedPreferences settings = getSharedPreferences("SETTINGS", 0);
			if(settings.getBoolean("FIRST_TIME", true)){
				AppDataContext dataContext = new AppDataContext(this);
				// Añadimos 4 ubicaciones de bicis de ejemplo
				Puestos[] p = new Puestos[5];
				p[0] = new Puestos();
				p[0].setBicis(4);
				p[0].setNombre("Otra0");
				p[0].setLatitud(45);
				p[0].setLongitud(-45);
				p[0].setStatus(Entity.STATUS_NEW);
				dataContext.puestoDao.add(p[0]);
				dataContext.puestoDao.save();
				p[1] = new Puestos();
				p[1].setBicis(4);
				p[1].setNombre("Otra1");
				p[1].setLatitud(0);
				p[1].setLongitud(0);
				p[1].setStatus(Entity.STATUS_NEW);
				dataContext.puestoDao.add(p[1]);
				dataContext.puestoDao.save();
				p[2] = new Puestos();
				p[2].setBicis(4);
				p[2].setNombre("Otra2");
				p[2].setLatitud(20);
				p[2].setLongitud(20);
				p[2].setStatus(Entity.STATUS_NEW);
				dataContext.puestoDao.add(p[2]);
				dataContext.puestoDao.save();
				p[3] = new Puestos();
				p[3].setBicis(4);
				p[3].setNombre("Otra3");
				p[3].setLatitud(-21);
				p[3].setLongitud(-21);
				p[3].setStatus(Entity.STATUS_NEW);
				dataContext.puestoDao.add(p[3]);
				dataContext.puestoDao.save();
				p[4] = new Puestos();
				p[4].setBicis(4);
				p[4].setNombre("Universidad");
				p[4].setLatitud(38.274769);
				p[4].setLongitud(-0.686191);
				p[4].setStatus(Entity.STATUS_NEW);
				dataContext.puestoDao.add(p[4]);
				dataContext.puestoDao.save();
				
				settings.edit().putBoolean("FIRST_TIME", false).commit();
			}
		} catch(AdaFrameworkException e){
			Log.e("ADA", e.getMessage());
		}
		
		Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				Intent intent = new Intent(MainActivity.this, MenuActivity.class);
				startActivity(intent);
			}
		};
		
		WaitThread t = new WaitThread(handler);
		t.start();
	}
}

class WaitThread extends Thread{
	Handler handler;
	
	public WaitThread(Handler h){
		handler = h;
	}
	
	@Override
	public void run(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		handler.sendMessage(handler.obtainMessage());
	}
}