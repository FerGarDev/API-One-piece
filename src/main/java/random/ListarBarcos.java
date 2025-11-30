package random;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListarBarcos {
	private Barco[] barco;

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
