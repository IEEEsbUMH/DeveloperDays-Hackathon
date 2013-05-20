package com.JorgeBM.hackaton;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Button botonEscuela, botonEstacion, botonEliminar;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		botonEscuela=(Button)findViewById(R.id.Button1);
		botonEstacion = (Button)findViewById(R.id.Button2);
		botonEliminar = (Button)findViewById(R.id.Button3);
		
	
		botonEscuela.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View view){
			
				
				
			}
		});
	}
	
	
	public void lanzarActividad1(){
		Intent i=new Intent(this,Activity3.class);
		startActivity(i);

		
		botonEstacion.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View view){	
				
			}
		});
	}
		public void lanzarActividad2(){
			Intent i=new Intent(this,Activity4.class);
			startActivity(i);
		
		botonEliminar.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View view){
			
				
				
			}
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
