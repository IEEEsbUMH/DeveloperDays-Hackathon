package com.example.bicelche;

import com.example.bicelche.R.botoes;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class principal extends Activity {

	ImageButton pesquisa;
	Button devolver;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        pesquisa=(ImageButton)findViewById(botoes.pesquisar);
        devolver=(Button)findViewById(R.id.devolver);
        
        pesquisa.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
	   try{
				startActivity(new Intent(principal.this,pesq.class));
	   }catch(Exception e){Log.i("erro","erro"+e);}
			}
		});
        
        
        
        devolver.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				  try{
					startActivity(new Intent(principal.this,devolver.class));
			   }catch(Exception e){Log.i("erro","erro"+e);}
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
