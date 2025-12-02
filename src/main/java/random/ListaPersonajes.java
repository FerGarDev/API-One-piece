package random;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListaPersonajes {
	private Personajes[] personajes;

	// Debido a que viene sin nombre los datos de la API se recive el array de la
	// API y con el JSonCreator hacemos que Jackson use este contructor para crear
	// los objetos
	@JsonCreator
	public ListaPersonajes(Personajes[] personajes) {
		this.personajes = personajes;
	}

	public Personajes[] getPersonajes() {
		return personajes;
	}

	public void setPersonajes(Personajes[] personajes) {
		this.personajes = personajes;
	}

}
