package com.ddieeeumh.losdragones.biciumh;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView usuarioLV;
	Button crear;
	private ArrayList<String> listaUsuarios;
	private ArrayAdapter<String> adaptadorUsuarios;
	EditText campoNombre, campoApellido, campoDNI;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		usuarioLV = (ListView)findViewById(R.id.listaUsuario);
		crear = (Button)findViewById(R.id.botonCrear);
		campoNombre = (EditText) findViewById(R.id.campoNombre);
		campoApellido = (EditText) findViewById(R.id.campoApellido);
		campoDNI = (EditText) findViewById(R.id.campoEdad);
		
		listaUsuarios = new ArrayList<String>();
		
		actualizarListView();	
		
		crear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				String nombre = campoNombre.getText().toString();
				String apellido = campoApellido.getText().toString();
				String DNI = campoDNI.getText().toString();
				
//				FichaUsuario fu = new FichaUsuario(nombre, apellido, DNI);

				// comprobar que no hay ningun campo vacio
				if (nombre.length() > 0 && apellido.length() > 0) {
					listaUsuarios.add(nombre + " " + apellido);

					// vaciamos los campos
					campoNombre.setText("");
					campoApellido.setText("");
					campoDNI.setText("");

					// quitamos el teclado
					InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
					imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,
							0);
				}
				
				boolean escrituraCorrecta = true;
				// contexto
				Context contexto = getApplicationContext();
				// escribimos en fichero
				try {
					FileOutputStream ficheroSalida = contexto.openFileOutput(
							"usuarios", Context.MODE_PRIVATE);
					ObjectOutputStream streamSalida = new ObjectOutputStream(
							ficheroSalida);
					streamSalida.writeObject(listaUsuarios);
					streamSalida.close();
				} catch (FileNotFoundException e) {
					escrituraCorrecta = false;
					e.printStackTrace();
				} catch (IOException e) {
					escrituraCorrecta = false;
					e.printStackTrace();
				}
				Toast mensaje;
				if (escrituraCorrecta) {
					mensaje = Toast.makeText(MainActivity.this,
							"Nuevo usuario creado", Toast.LENGTH_LONG);
							actualizarListView();
				} else {
					mensaje = Toast.makeText(MainActivity.this,
							"¡Error en la creación!", Toast.LENGTH_LONG);
				}
				mensaje.show();
				
				
			}
		});
	
	}
	
	private void actualizarListView(){
		
		boolean lecturaCorrecta = true;
		// contexto
		Context contexto = getApplicationContext();
//		// leer de fichero
		try {
			FileInputStream ficheroEntrada = contexto
					.openFileInput("usuarios");
			ObjectInputStream streamEntrada = new ObjectInputStream(ficheroEntrada);
			listaUsuarios = (ArrayList<String>) streamEntrada
					.readObject();
			streamEntrada.close();

		} catch (FileNotFoundException e) {
			lecturaCorrecta = false;
			e.printStackTrace();
		} catch (IOException e) {
			lecturaCorrecta = false;
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			lecturaCorrecta = false;
			e.printStackTrace();
		}
		Toast mensaje = null;

		// si la lectura no ha sido correcta, limpiamos la lista de
		// libros
		if (!lecturaCorrecta)
			mensaje = Toast.makeText(MainActivity.this, "No hay datos", Toast.LENGTH_LONG);
			listaUsuarios.clear();
		// en todos los casos, volvemos a mostrar la lista
			
			
			adaptadorUsuarios = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaUsuarios);
	         usuarioLV.setAdapter(adaptadorUsuarios); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
