package com.example.exame;

public class Destino {
	String zona;
	String continente;
	int precio;
	
	public Destino(String zon, String cont, int pre){
		zona = zon;
		continente = cont;
		precio = pre;
		
	}
	
	public String getZona(){
		return zona;
	}
	
	public String getContinente(){
		return continente;
	}
	
	public int getPrecio(){
		return precio;
	}
	
}