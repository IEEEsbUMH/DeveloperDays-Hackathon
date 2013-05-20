package com.example.bicelche;


import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;

public class login extends Activity implements OnClickListener {
 Facebook fb;
 SharedPreferences sp;
 ImageView  pic, button;
 TextView nome;
 
public static String nome_face="";
public static String id_face="";
public static String idade_face="";
public static String sexo_face="";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.facebook);
	String APP_ID = getString(R.string.app_id);
	
	  
		fb = new Facebook(APP_ID);
		
		 
		
		  
		sp = getPreferences(MODE_PRIVATE);
		String access_token = sp.getString("access_token", null);
	
		long expires = sp.getLong("access_expires", 0);
	  
		if(access_token != null){
			fb.setAccessToken(access_token);
				}
		if(expires != 0){
			
			fb.setAccessExpires(expires);
		}
		
		
		
		 
	button =(ImageView) findViewById(R.id.teste);
    pic = (ImageView) findViewById(R.id.picture);
 
	button.setOnClickListener(this);

	
		updateButtonImage();}
	

		

	

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View arg0) {
		
		if(fb.isSessionValid()){
			try{
		
			fb.logout(getApplicationContext());
			updateButtonImage();
			}
			catch(MalformedURLException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
			
		}
		else{
			
		    
				fb.authorize(this,new String[]{"email","user_birthday"}, new 
						DialogListener() {
				
				@Override
				public void onFacebookError(FacebookError e) {
					Toast.makeText(login.this, "Fb error"+e,Toast.LENGTH_SHORT).show();
					
				}
				
				@Override
				public void onError(DialogError e) {
					Toast.makeText(login.this, "ON error",Toast.LENGTH_SHORT).show();
					
				}
				
				@Override
				public void onComplete(Bundle values) {
					
					Editor editor =sp.edit();
					editor.putString("access_token", fb.getAccessToken());
					editor.putLong("access_expires",fb.getAccessExpires());
					editor.commit();
					

					updateButtonImage();
					
				}
				
				@Override
				public void onCancel() {
					Toast.makeText(login.this, "ON cancel",Toast.LENGTH_SHORT).show();
					
				}
			});
			
		}
		}
	
	
	@SuppressWarnings("deprecation")
	@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			fb.authorizeCallback(requestCode, resultCode, data);
		}
		
	
	
	@SuppressWarnings("deprecation")
	private void updateButtonImage() {
		if(fb.isSessionValid()){
			button.setImageResource(R.drawable.logout_button);
			
	       try {
	    	   DB_adaptador db = new DB_adaptador(this);
				 db.open();
	    	   Cursor c= db.getRecord(1);
	    	   if(c.moveToFirst()){
	    		   startActivity(new Intent(this, principal.class));
			 		 finish();	   
	    	   }
	               else{
	            	   db.close();
	            	   new Thread(new Runnable() {
	            	        public void run() {
	            	           
	            	          
	            	  
	            //    Toast.makeText(this, "nao a dados", Toast.LENGTH_LONG).show();
	 			      
	 			       Log.i("vai entrar try", "vai entrar no try");
	 			       try{
	 			    	   Log.i("entro no try", "tentando processar try");
	 			    	
	 			         JSONObject obj = null;
	 						String jsonUser;
	 						jsonUser = fb.request("me");
	 						obj = Util.parseJson(jsonUser);
	 						
	 						 String id=obj.getString("id");
	 						  String name = obj.getString("first_name");
	 						 String Age = obj.getString("birthday");
	 						 String gender = obj.getString("gender");
	 					  
	 						 Log.i("inteeeeeeeeeeeeeerrrrrrrr", "tentando processar try");
	 						
	 						
	 				   inter(id, name, Age, gender);
	 						 
	 				 		 
	 			      
	 						
	 						//nome.setText("teste" + name+ "_sexo_"+gender+" idade:"+Age);
	 					
	 					} 
	 			       catch (FacebookError e) {
	 						// TODO Auto-generated catch block
	 						e.printStackTrace();
	 						Toast.makeText(login.this, "s"+e,Toast.LENGTH_LONG).show();
	 					} catch (JSONException e) {
	 						// TODO Auto-generated catch block
	 						e.printStackTrace();
	 						Toast.makeText(login.this, "Fb error"+e,Toast.LENGTH_LONG).show();
	 					} catch (MalformedURLException e) {
	 						// TODO Auto-generated catch block
	 						e.printStackTrace();
	 						Toast.makeText(login.this, "Fb error"+e,Toast.LENGTH_LONG).show();
	 					} catch (IOException e) {
	 						// TODO Auto-generated catch block
	 						e.printStackTrace();
	 						Toast.makeText(login.this, "Fb error"+e,Toast.LENGTH_LONG).show();
	 					}      }
	            	    }).start();
	                }//fim else
	    	   
	       }
	 			       catch(Exception e){Toast.makeText(login.this, "error"+e,Toast.LENGTH_SHORT).show();}
		
		}
	               else {
			button.setImageResource(R.drawable.teste);
		
	}

	
	}




	private  void inter(String id,String nome ,String idade ,String sexo) {
		
		Log.i("interrrrrrrr","dados estao num array");
		try{   
		 id_face=id;
		 nome_face=nome;
		idade_face=idade;
		sexo_face=sexo;
		String[] value={id_face,nome_face,idade_face,sexo_face};
	
		Log.i("passamos","dados estao num array");
	
		DB_adaptador db = new DB_adaptador(this);
	  	 db.open();
	  	     
       long la = db.insertRecord(id_face, nome_face,id_face, sexo_face); 
	  	db.close();
	  	try{
	        Log.i("vai passar para envia","passando");
	          Intent intent = new Intent(login.this, principal.class);  
	          
	          Bundle b = new Bundle();  
	          
	          b.putStringArray("chave", value);
	            
	          intent.putExtras(b);  
	           
	            
	         
	            
	       
	            
	          startActivity(intent);  
	            
	    
	 		 finish();
	  	}catch(Exception e){Log.i("------","erro"+e);}
	    }
		catch(Exception e){Toast.makeText(this, "erro"+e, Toast.LENGTH_LONG).show();}
		
	   
	}

	



}


