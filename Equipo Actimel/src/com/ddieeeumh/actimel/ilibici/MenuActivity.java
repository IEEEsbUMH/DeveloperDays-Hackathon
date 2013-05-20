package com.ddieeeumh.actimel.ilibici;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MenuActivity extends Activity implements OnClickListener{
	private View cercanosBtn;
	private View pickBtn;
	private View newUserBtn;
	private View aboutBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		findviews();
	}
	
	private void findviews()
	{
		cercanosBtn = findViewById(R.id.cercanos);
		pickBtn = findViewById(R.id.pick);
		newUserBtn = findViewById(R.id.new_user);
		aboutBtn = findViewById(R.id.about);
		cercanosBtn.setOnClickListener(this);
		pickBtn.setOnClickListener(this);
		newUserBtn.setOnClickListener(this);
		aboutBtn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v)
	{
		int id = v.getId();
		
		switch(id){
			case R.id.cercanos:
				Intent intent = new Intent(this, LugaresCercanos.class);
				startActivity(intent);
				break;
			case R.id.about:
				Intent intent2 = new Intent(this, AcercaDe.class);
				startActivity(intent2);
				break;
			case R.id.new_user:
				Intent intent3 = new Intent(this, NuevoUsuario.class);
				Bundle bundle = new Bundle();
				bundle.putString("MODE", "CREAR");
				intent3.putExtras(bundle);
				startActivity(intent3);
				break;
			case R.id.pick:
				Intent intent4 = new Intent(this, CogerSoltar.class);
				startActivity(intent4);
				break;
		}
	}
}
