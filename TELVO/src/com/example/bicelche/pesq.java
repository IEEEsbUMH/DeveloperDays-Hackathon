package com.example.bicelche;

import java.util.ArrayList;
import java.util.List;

import com.conection.web.envia1;


import android.R.id;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;


import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import android.widget.Spinner;


public class pesq extends Activity  {
	
	
	String ponto="",value="";
	ListView pontos;
	Button consultar;
	Spinner local,quantidades;
	List<String> listaruas= new ArrayList<String>();
	ArrayAdapter<String> adaptador= null;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.pesquisa);
	
	consultar=(Button)findViewById(R.id.pesquisa);
	pontos=(ListView)findViewById(R.id.calle);
	local=(Spinner)findViewById(R.id.puntos);
	quantidades=(Spinner)findViewById(R.id.bike);
	
	try{
	   adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listaruas);
		
		adaptador.add("PUNTO 1 \n Calle San Juan \n PLAZAS TOTAL:10");	
		adaptador.add("PUNTO 2 \n Calle francis \n PLAZAS TOTAL:10");
		adaptador.add("PUNTO 3 \n Calle netstat  \n PLAZAS TOTAL:10");
	
		pontos.setAdapter(adaptador);
		
	}catch(Exception e){Log.i("s","erro");}

	consultar.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stubtr
			try{
			ponto=local.getSelectedItem().toString();
			value=quantidades.getSelectedItem().toString();
			Log.i("pegou valores","value"+value);
			String[] values={ponto,value};
			
			
  Intent intent = new Intent(pesq.this, envia1.class);  
	          
	          Bundle b = new Bundle();  
	          
	          b.putStringArray("chave", values);
	            
	          intent.putExtras(b);  
	         
	          startActivity(intent);  
			}
			catch(Exception e ){Log.i("ssss","obs"+e);}
	 		 
		}	
	}); 
	
	
}
}
