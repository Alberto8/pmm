package com.example.bdcentrosprofesores;

public class Personal {

	private String codCentro;
	private String dni;
	private String apellido;
	private String funcion;
	private String salario;


	public Personal(String cod, String dn, String ape, String fun, String sal)
	{
		codCentro =cod;
		dni =dn;
		apellido = ape;
		funcion =fun;
		salario =sal;

	}
	
	public String getCodCentro() {
		return codCentro;
	}

	public void setCodCentro(String codCentro) {
		this.codCentro = codCentro;
	}

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

}

