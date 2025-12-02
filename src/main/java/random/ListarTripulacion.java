package random;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListarTripulacion {
	private Tripulacion[] tripulaciones;

	// Debido a que viene sin nombre los datos de la API se recive el array de la
	// API y con el JSonCreator hacemos que Jackson use este contructor para crear
	// los objetos
	@JsonCreator
	public ListarTripulacion(Tripulacion[] tripulaciones) {
		this.tripulaciones = tripulaciones;
	}

	public Tripulacion[] getTripulaciones() {
		return tripulaciones;
	}

	public void setTripulaciones(Tripulacion[] tripulaciones) {
		this.tripulaciones = tripulaciones;
	}

}
