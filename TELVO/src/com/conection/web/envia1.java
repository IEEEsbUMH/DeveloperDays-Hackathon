package com.conection.web;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;



import com.example.bicelche.fim;
import com.example.bicelche.principal;



import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;


public class envia1 extends Activity{
	 String user;
	 String pass;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		 Log.i("estamos processando","classe envia");
	       
	       try{
	        new processa().execute();
	       }catch(Exception e){Log.i("","sd"+e);}
	     
}




class processa extends AsyncTask<String, String, String> {
	
	
	
	String resposta;
	@Override
	
	protected String doInBackground(String... params) {
		 Bundle b = getIntent().getExtras();  
	        String[] value = b.getStringArray("chave");
	       Log.i("pegou dados","dados sao"+value[0]);
	       Log.i("pegou dados","dados sao"+value[1]);
	      
		Log.i("estamos processando","classe envia");
		String url="http://www.telvo.hol.es/ieee/logar.php";
		ArrayList<NameValuePair>parametrosPost= new ArrayList<NameValuePair>();
	
	parametrosPost.add(new BasicNameValuePair("usuario",value[0].toString()));
	parametrosPost.add(new BasicNameValuePair("senha",value[1].toString()));
	String respostaRetornada = null;
	Log.i("erro","as");
	
	try{
		Log.i("erro","entrou no try");
		respostaRetornada = ConectionHttpClient.executaHttpPost(url, parametrosPost);
		
		 resposta = respostaRetornada.toString();
		 resposta=resposta.replaceAll("\\s","");
		Log.i("logar", "resposta = "+resposta);
		startActivity(new Intent(envia1.this, fim.class));
	
	}
	
	catch(Exception erro){
	//	Toast.makeText(MainActivity.this, "Erro>"+erro,Toast.LENGTH_LONG).show();
		Log.i("","eror"+erro);
	}


		return resposta;
	}
	// TODO Auto-generated method stub
	protected void onPostExecute(String resposta){
		String teste="1";
		if(resposta.equals("1")){
			  
		Toast.makeText(envia1.this, "alquilado con éxito bicicleta!"+resposta+".",Toast.LENGTH_LONG).show();
		Toast.makeText(envia1.this, "alquilado con éxito bicicleta!"+resposta+".",Toast.LENGTH_LONG).show();
		}
		else  {
			Log.i("","ver"+resposta+".");
			Toast.makeText(envia1.this, "no hay tal cantidad de vacantes libres en este momento.",Toast.LENGTH_LONG).show();
			Toast.makeText(envia1.this, "no hay tal cantidad de vacantes libres en este momento.",Toast.LENGTH_LONG).show();
		}
		
		
	}
	
}
}