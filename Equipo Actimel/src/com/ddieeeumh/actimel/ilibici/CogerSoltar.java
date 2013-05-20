package com.ddieeeumh.actimel.ilibici;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class CogerSoltar extends Activity {
	SharedPreferences settings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coger_soltar);
		
		settings = getSharedPreferences("SETTINGS", 0);
	}
	
	public void coger(View v){
		if(!settings.getBoolean("Already_logged_in", false)){
			Intent intent = new Intent(this, NuevoUsuario.class);
			Bundle bundle = new Bundle();
			bundle.putString("MODE", "VALIDAR");
			bundle.putString("PICK", "COGER");
			intent.putExtras(bundle);
			startActivity(intent);
		}
		else{
			Intent intent = new Intent(this, ListaPuestos.class);
			Bundle bundle = new Bundle();
			bundle.putString("MODE", "VALIDAR");
			bundle.putString("PICK", "COGER");
			intent.putExtras(bundle);
			startActivity(intent);
		}
	}
	
	public void soltar(View v){
		if(!settings.getBoolean("Already_logged_in", false)){
			Intent intent = new Intent(this, NuevoUsuario.class);
			Bundle bundle = new Bundle();
			bundle.putString("MODE", "VALIDAR");
			bundle.putString("PICK", "SOLTAR");
			intent.putExtras(bundle);
			startActivity(intent);
		}
		else{
			Intent intent = new Intent(this, ListaPuestos.class);
			Bundle bundle = new Bundle();
			bundle.putString("MODE", "VALIDAR");
			bundle.putString("PICK", "SOLTAR");
			intent.putExtras(bundle);
			startActivity(intent);
		}
	}
}
