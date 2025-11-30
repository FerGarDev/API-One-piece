package random;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tripulacion {
	@JsonIgnore
	private String id;
	private String name;
	private String status;
	private String roman_name;
	private String total_prime;
	private String number;
	private boolean is_yonko;

	public Tripulacion() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoman_name() {
		return roman_name;
	}

	public void setRoman_name(String roman_name) {
		this.roman_name = roman_name;
	}

	public String getTotal_prime() {
		return total_prime;
	}

	public void setTotal_prime(String total_prime) {
		this.total_prime = total_prime;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isIs_yonko() {
		return is_yonko;
	}

	public void setIs_yonko(boolean is_yonko) {
		this.is_yonko = is_yonko;
	}

	public String toString() {
		return "Nombre: " + name + " || Estado: " + status + " || Nombre original: " + roman_name
				+ " || Recompensa por cabeza conjunta: " + total_prime + " || Miembros: " + number + " || Son yonko: "
				+ is_yonko;
	}
}
