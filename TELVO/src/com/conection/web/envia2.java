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


public class envia2 extends Activity{
	 String user;
	 String pass;
	 String str="";
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
	        String value = b.getString("chave");
	       Log.i("pegou dados","dados sao"+value);
	      
	      
		Log.i("estamos processando","classe envia");
		String url="http://www.telvo.hol.es/ieee/devolver.php";
		ArrayList<NameValuePair>parametrosPost= new ArrayList<NameValuePair>();
	
	parametrosPost.add(new BasicNameValuePair("senha",value.toString()));

	String respostaRetornada = null;
	Log.i("erro","as");
	
	try{
		Log.i("erro","entrou no try");
		respostaRetornada = ConectionHttpClient.executaHttpPost(url, parametrosPost);
		
		 resposta = respostaRetornada.toString();
		 resposta=resposta.replaceAll("\\s","");
		Log.i("logar", "resposta = "+resposta);
		//for(int i = 0;i<resposta.length();i++){
		//	System.out.println(resposta.charAt(i));
		//	Toast.makeText(envia2.this, "puede devolver la bicicleta en los puntos."+i,Toast.LENGTH_LONG).show();
		//}
		startActivity(new Intent(envia2.this, fim.class));
	
	}
	
	catch(Exception erro){
	//	Toast.makeText(MainActivity.this, "Erro>"+erro,Toast.LENGTH_LONG).show();
		Log.i("","eror"+erro);
	}


		return resposta;
	}
	// TODO Auto-generated method stub
	protected void onPostExecute(String resposta){
		
		for(int i = 0;i<resposta.length();i++){
			System.out.println(resposta.charAt(i));
			str=str+"puede devolver la bicicleta en los puntos "+(i+1)+"\n";
			
		}
		
		Toast.makeText(envia2.this, str,Toast.LENGTH_LONG).show();
		
		
	}

}
}