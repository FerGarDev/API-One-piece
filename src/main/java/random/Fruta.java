package random;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fruta {
	@JsonIgnore
	private String id;
	private String name;
	private String roman_name;
	private String type;

	// Clase de Frutas, con todos los datos que usaremos de la API
	private Fruta() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoman_name() {
		return roman_name;
	}

	public void setRoman_name(String roman_name) {
		this.roman_name = roman_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String toString() {
		return "Nombre: " + name + " || Nombre romano: " + roman_name + " || Tipo: " + type;
	}

}
