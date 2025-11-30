package random;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListaPersonajes {
	private Personajes[] personajes;

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
