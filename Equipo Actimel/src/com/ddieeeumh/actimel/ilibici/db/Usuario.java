package com.ddieeeumh.actimel.ilibici.db;

import com.mobandme.ada.Entity;
import com.mobandme.ada.annotations.Table;
import com.mobandme.ada.annotations.TableField;

@Table(name = "usuarios")
public class Usuario extends Entity{
	@TableField(name="nombre", datatype=DATATYPE_STRING, required=true)
	private String nombre = "";
	@TableField(name="pass", datatype=DATATYPE_STRING, required=true)
	private String pass = "";
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}
