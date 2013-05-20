package com.ddieeeumh.actimel.ilibici;

import java.util.Vector;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ddieeeumh.actimel.ilibici.db.AppDataContext;
import com.ddieeeumh.actimel.ilibici.db.Puestos;
import com.mobandme.ada.Entity;
import com.mobandme.ada.exceptions.AdaFrameworkException;

public class ListaPuestos extends ListActivity{
	Vector<String> lista;
	private AppDataContext dataContext;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lugares_cercanos);

		try {
			dataContext = new AppDataContext(this);
			dataContext.puestoDao.fill();
			// dataContext.userDao.fill();
		} catch (AdaFrameworkException e) {
			Log.e("ADA", e.getMessage());
		}
		
		lista = new Vector<String>();
		
		for(int i=0; i<dataContext.puestoDao.size(); i++){
			lista.add(dataContext.puestoDao.get(i).toString());
		}
		
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, lista));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if(getIntent().getExtras().getString("PICK").equals("SOLTAR")){
			if(dataContext.puestoDao.get(position).getBicis()!=4){
				dataContext.puestoDao.get(position).setBicis(dataContext.puestoDao.get(position).getBicis()+1);
				dataContext.puestoDao.get(position).setStatus(Entity.STATUS_UPDATED);
				try {
					dataContext.puestoDao.save();
				} catch (AdaFrameworkException e) {
					Log.e("ADA", e.getMessage());
				}
				finish();
			}
			else{
				Toast.makeText(this, getResources().getString(R.string.nocaben), Toast.LENGTH_LONG).show();
			}
		}
		if(getIntent().getExtras().getString("PICK").equals("COGER")){
			if(dataContext.puestoDao.get(position).getBicis()!=0){
				dataContext.puestoDao.get(position).setBicis(dataContext.puestoDao.get(position).getBicis()-1);
				dataContext.puestoDao.get(position).setStatus(Entity.STATUS_UPDATED);
				try {
					dataContext.puestoDao.save();
				} catch (AdaFrameworkException e) {
					Log.e("ADA", e.getMessage());
				}
				finish();
			}
			else{
				Toast.makeText(this, getResources().getString(R.string.nohay), Toast.LENGTH_LONG).show();
			}
		}
	}
}
