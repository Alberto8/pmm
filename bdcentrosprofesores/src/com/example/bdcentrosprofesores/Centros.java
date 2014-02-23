package com.example.bdcentrosprofesores;

public class Centros {

	private String codCentro;
	private String tipoCentro;
	private String nomCentro;
	private String direccion;
	private String telefono;
	private String numPlazas;

	public Centros(String cod, String tip, String nom, String dir, String tel, String num)
	{
		codCentro = cod;
		nomCentro =nom;
		direccion=dir;
		tipoCentro =tip;
		telefono =tel;
		numPlazas =num;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCodCentro() {
		return codCentro;
	}
	public void setCodCentro(String codCentro) {
		this.codCentro = codCentro;
	}
	public String getNomCentro() {
		return nomCentro;
	}
	public void setNomCentro(String nomCentro) {
		this.nomCentro = nomCentro;
	}
	public String getTipoCentro() {
		return tipoCentro;
	}
	public void setTipoCentro(String tipoCentro) {
		this.tipoCentro = tipoCentro;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getNumPlazas() {
		return numPlazas;
	}
	public void setNumPlazas(String numPlazas) {
		this.numPlazas = numPlazas;
	}

}