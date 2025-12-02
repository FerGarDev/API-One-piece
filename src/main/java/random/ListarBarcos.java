package random;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListarBarcos {
	private Barco[] barco;

	// Debido a que viene sin nombre los datos de la API se recive el array de la
	// API y con el JSonCreator hacemos que Jackson use este contructor para crear
	// los objetos
	@JsonCreator
	public ListarBarcos(Barco[] barco) {
		this.barco = barco;
	}

	public Barco[] getBarco() {
		return barco;
	}

	public void setBarco(Barco[] barco) {
		this.barco = barco;
	}

}
