package application.modelo;

import java.io.Serializable;

public class PersonaModelo implements Serializable {

	private String cedula;
	private String nombre;
	private String apellido;
	private String ciudad;
	private String telefono;

	public PersonaModelo() {

	}

	public PersonaModelo(String cedula, String nombre, String apellido, String ciudad, String telefono) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.ciudad = ciudad;
		this.telefono = telefono;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
