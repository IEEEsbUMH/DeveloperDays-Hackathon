package com.example.bicelche;

import com.conection.web.envia1;
import com.conection.web.envia2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class devolver extends Activity {
	Spinner devolver;
	Button checar;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.devolver);
	
	devolver=(Spinner)findViewById(R.id.bike);
	checar=(Button)findViewById(R.id.button1);
	
	checar.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			try{
				String ponto=devolver.getSelectedItem().toString();
			
				
				String value=ponto;
				
				
	  Intent intent = new Intent(devolver.this, envia2.class);  
		          
		          Bundle b = new Bundle();  
		          
		          b.putString("chave", value);
		            
		          intent.putExtras(b);  
		         
		          startActivity(intent);  
				}
				catch(Exception e ){Log.i("ssss","obs"+e);}
		 		 
		
			
		}
	});
}
	
	
}
