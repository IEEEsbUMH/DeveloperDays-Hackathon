package com.ddieeeumh.actimel.ilibici.db;

import com.mobandme.ada.Entity;
import com.mobandme.ada.annotations.Table;
import com.mobandme.ada.annotations.TableField;

@Table(name = "puestos")
public class Puestos extends Entity{
	@TableField(name="nombre", datatype=DATATYPE_STRING, required=true)
	private String nombre = "";
	@TableField(name="bicis", datatype=DATATYPE_INTEGER, required=true)
	private int bicis = 0;
	@TableField(name="latitud", datatype=DATATYPE_DOUBLE, required=true)
	private double latitud = 0;
	@TableField(name="longitud", datatype=DATATYPE_DOUBLE, required=true)
	private double longitud = 0;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getBicis() {
		return bicis;
	}
	public void setBicis(int bicis) {
		this.bicis = bicis;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public String toString(){
		return(nombre + " " + bicis + "/4");
	}
}
