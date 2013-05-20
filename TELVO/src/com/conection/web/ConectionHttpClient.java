package com.conection.web;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
//biblioteca espeficias para web 4044
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import android.util.Log;



public class ConectionHttpClient  {

public static final int http_timeout = 30* 1000;
private static HttpClient http_client;

private static HttpClient getHttpClient(){
	if(http_client == null){
		http_client = new DefaultHttpClient();
		final HttpParams httpParamns= http_client.getParams();
		HttpConnectionParams.setConnectionTimeout(httpParamns, http_timeout);
		HttpConnectionParams.setSoTimeout(httpParamns,http_timeout);
		ConnManagerParams.setTimeout(httpParamns, http_timeout);
		}
	return http_client;
}
public static String executaHttpPost(String url, ArrayList<NameValuePair> parametrosPost) throws Exception{
	BufferedReader buffer=null;
	try{
		Log.i("erro","entrou no try da classe http");
		HttpClient client= getHttpClient();
		HttpPost post= new HttpPost(url);
	 UrlEncodedFormEntity forment = new UrlEncodedFormEntity(parametrosPost);
	post.setEntity(forment);
	HttpResponse httpResponse = client.execute(post);
	buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
	StringBuffer stringBuffer = new StringBuffer("");
	String line = "";
	String LS= System.getProperty("line.separator");
	while((line=buffer.readLine())!= null){
		Log.i("erro","entrou no try da classe http entrou no while");
		stringBuffer.append(line + LS);
		
	}
	buffer.close();
	
	String resultado = stringBuffer.toString();
	return resultado;
	}finally{
		if(buffer != null){
			try{
				buffer.close();
			}catch (IOException e) {
				// TODO: handle exception
			e.printStackTrace();
			}
			
		}
		
	}

	
}
}
//Lucas Campos