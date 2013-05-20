package com.ddieeeumh.losdragones.biciumh;

import java.io.Serializable;

public class FichaUsuario implements Serializable{
	
	private String nombre, apellido, dni;
	
	
	public FichaUsuario(String nombre, String apellido, String DNI){
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = DNI;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
}
