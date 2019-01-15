package application.util;

import java.util.ArrayList;
import java.util.List;

import application.modelo.PersonaModelo;

public class Persistencia {

	private static List<PersonaModelo> lista = null;

	private Persistencia() {
	}

	public static List<PersonaModelo> getList() {
		if (lista == null) {
			lista = new ArrayList();
		}
		return lista;
	}

}
