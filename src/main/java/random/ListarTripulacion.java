package random;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListarTripulacion {
	private Tripulacion[] tripulaciones;

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
