package com.ddieeeumh.actimel.ilibici;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ddieeeumh.actimel.ilibici.db.AppDataContext;
import com.ddieeeumh.actimel.ilibici.db.Usuario;
import com.mobandme.ada.Entity;
import com.mobandme.ada.exceptions.AdaFrameworkException;

public class NuevoUsuario extends Activity{
	EditText user;
	EditText pass;
	Button aceptar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
		
		user = (EditText) findViewById(R.id.user);
		pass = (EditText) findViewById(R.id.pass);
	}

	public void hacerUsuario(View v) {
		if(getIntent().getExtras().getString("MODE").equals("CREAR")){
			try {
				AppDataContext dataContext = new AppDataContext(this);
				dataContext.userDao.fill();
				Usuario u = new Usuario();
				u.setNombre(user.getText().toString());
				u.setPass(pass.getText().toString());
				u.setStatus(Entity.STATUS_NEW);
				
				boolean existe = false;
				
				for(int i=0; i<dataContext.userDao.size(); i++){
					if(u.getNombre().equals(dataContext.userDao.get(i).getNombre())){
						existe = true;
					}
				}
				
				if(!existe){
					dataContext.userDao.add(u);
					dataContext.userDao.save();
					finish();
				}
				else{
					Toast.makeText(this, getResources().getString(R.string.existe), Toast.LENGTH_LONG).show();
				}
			} catch (AdaFrameworkException e) {
				Log.e("ADA", e.getMessage());
			}
		}
		if(getIntent().getExtras().getString("MODE").equals("VALIDAR")){
			SharedPreferences settings = getSharedPreferences("SETTINGS", 0);
			if(!settings.getBoolean("Already_logged_in", false)){
				try {
					AppDataContext dataContext = new AppDataContext(this);
					dataContext.userDao.fill();
					Usuario u = new Usuario();
					u.setNombre(user.getText().toString());
					u.setPass(pass.getText().toString());
					
					boolean existe = false;
					
					for(int i=0; i<dataContext.userDao.size(); i++){
						if(u.getNombre().equals(dataContext.userDao.get(i).getNombre())){
							if(u.getPass().equals(dataContext.userDao.get(i).getPass())){
								existe = true;
							}
						}
					}
					
					if(existe){
						settings.edit().putBoolean("Already_logged_in", true).commit();
						Intent intent = new Intent(this, ListaPuestos.class);
						Bundle bundle = new Bundle();
						bundle.putString("PICK", getIntent().getExtras().getString("PICK"));
						intent.putExtras(bundle);
						startActivity(intent);
					}
					else{
						Toast.makeText(this, getResources().getString(R.string.wrong), Toast.LENGTH_LONG).show();
					}
				} catch (AdaFrameworkException e) {
					Log.e("ADA", e.getMessage());
				}
			}
		}
	}
}
