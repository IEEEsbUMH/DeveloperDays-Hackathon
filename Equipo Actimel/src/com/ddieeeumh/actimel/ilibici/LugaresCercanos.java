package com.ddieeeumh.actimel.ilibici;

import java.util.Vector;

import android.app.ListActivity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.ddieeeumh.actimel.ilibici.db.AppDataContext;
import com.ddieeeumh.actimel.ilibici.db.Puestos;
import com.mobandme.ada.exceptions.AdaFrameworkException;

public class LugaresCercanos extends ListActivity {
	Vector<String> lista;
	private AppDataContext dataContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lugares_cercanos);

		try {
			dataContext = new AppDataContext(this);
			Log.d("PRUEBA", "AQUI");
			dataContext.puestoDao.fill();
			// dataContext.userDao.fill();
		} catch (AdaFrameworkException e) {
			Log.e("ADA", e.getMessage());
		}

		LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = manager.getBestProvider(criteria, true);
		Location loc = manager.getLastKnownLocation(provider);
		
		double latitud = loc.getLatitude();
		double longitud = loc.getLongitude();

		lista = new Vector<String>();
		Puestos[] p = new Puestos[dataContext.puestoDao.size()];
		double auxdis;
		double[] d = new double[dataContext.puestoDao.size()];
		double[] d2 = new double[dataContext.puestoDao.size()]; // ARRAYS de
																// distancias

		for (int i = 0; i < dataContext.puestoDao.size(); i++) {
			p[i] = dataContext.puestoDao.get(i);
		}

		for (int i = 0; i < p.length; i++) {
			d[i] = java.lang.Math.sqrt((p[i].getLongitud()-longitud)*(p[i].getLongitud()-longitud)+(p[i].getLatitud()-latitud)*(p[i].getLatitud()-latitud));
			d2[i] = d[i];
		}
		
		for(int i=1; i<p.length; i++){
			for(int j=0; j<p.length-1; j++){
				if(d[j]>d[j+1]){
					auxdis = d[j];
					d[j] = d[j+1];
					d[j+1] = auxdis;
				}
			}
		}

		for (int i = 0; i < p.length; i++) {
			for(int j=0; j<p.length; j++){
				if(d2[j]==d[i]){
					lista.add(p[j].toString());
				}
			}
		}

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, lista));
	}
}
