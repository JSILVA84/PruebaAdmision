package application.controlador;

import java.util.List;

import application.modelo.PersonaModelo;
import application.util.Persistencia;

public class PersonaControlador {

	public void registrar(String cedula, String nombre, String apellido, String ciudad, String telefono) {
		Persistencia.getList().add(new PersonaModelo(cedula, nombre, apellido, ciudad, telefono));
	}

	public List<PersonaModelo> consultar() {
		return Persistencia.getList();
	}

	public PersonaModelo consultarPorPosicion(int posicion) {
		return Persistencia.getList().get(posicion);
	}

	public void editar(int posicion, String cedula, String nombre, String apellido, String ciudad, String telefono) {
		PersonaModelo temp = new PersonaModelo(cedula, nombre, apellido, ciudad, telefono);
		Persistencia.getList().remove(posicion);
		Persistencia.getList().add(temp);
	}

	public void eliminar(int posicion) {
		Persistencia.getList().remove(posicion);
	}

}
