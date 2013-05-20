package com.example.bicelche;





import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class inicio extends Activity implements Runnable {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inicio);
		
		Handler handler = new Handler();
		handler.postDelayed(this,3000);
	}
	@Override
	public void run() {
		startActivity(new Intent(this, login.class));
		finish();
		
	}

}
