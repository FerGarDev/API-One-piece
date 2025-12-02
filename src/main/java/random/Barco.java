package random;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Barco {
	@JsonIgnore
	private String id;
	private String name;
	private String type;
	private String roman_name;
	private Tripulacion crew;
	private Personajes character_captain;
	private Fruta fruit;

	// Clase de Barco, con todos los datos que usaremos de la API
	public Barco() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRoman_name() {
		return roman_name;
	}

	public void setRoman_name(String roman_name) {
		this.roman_name = roman_name;
	}

	public Tripulacion getCrew() {
		return crew;
	}

	public void setCrew(Tripulacion crew) {
		this.crew = crew;
	}

	public Personajes getCharacter_captain() {
		return character_captain;
	}

	public void setCharacter_captain(Personajes character_captain) {
		this.character_captain = character_captain;
	}

	public Fruta getFruit() {
		return fruit;
	}

	public void setFruit(Fruta fruta) {
		this.fruit = fruta;
	}

	public String toString() {
		String capitanNombre = "No hay";
		String nombreFruta = "No tiene";
		String nombreTripulacion = "No es de ninguna tripulacion";

		// Para no tener errores el dato se da por hecho que no se tiene, luego se
		// comprueba si no es null y en tal caso se sustituira por un dato existente
		if (crew != null) {
			nombreTripulacion = crew.getName();
		}

		if (character_captain != null) {
			capitanNombre = character_captain.getName();
			if (character_captain.getFruit() != null) {
				nombreFruta = character_captain.getFruit().getName();
			}
		}

		return "Nombre: " + name + " || Tipo: " + type + " || Nombre romano: " + roman_name
				+ " || Tripulacion a la que pertecene: " + nombreTripulacion + " || Capitan del barco: " + capitanNombre
				+ " || Fruta del capitan: " + nombreFruta;
	}

}
