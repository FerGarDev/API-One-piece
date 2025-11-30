package random;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListarFrutas {
	private Fruta[] frutas;

	@JsonCreator
	public ListarFrutas(Fruta[] frutas) {
		this.frutas = frutas;
	}

	public Fruta[] getFrutas() {
		return frutas;
	}

	public void setFrutas(Fruta[] frutas) {
		this.frutas = frutas;
	}

}
