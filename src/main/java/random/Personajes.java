package random;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Personajes {
	@JsonIgnore
	private String id;
	private String name;
	private String size;
	private String age;
	private String bounty;
	private String job;
	private String status;
	private Tripulacion crew;
	private Fruta fruit;

	public Personajes() {
		super();
	}

	public Tripulacion getCrew() {
		return crew;
	}

	public void setCrew(Tripulacion crew) {
		this.crew = crew;
	}

	public Fruta getFruit() {
		return fruit;
	}

	public void setFruit(Fruta fruit) {
		this.fruit = fruit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBounty() {
		return bounty;
	}

	public void setBounty(String bounty) {
		this.bounty = bounty;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {

		String nombreTripulacion = "No tiene";
		String nombreFruta = "No tiene";
		if (crew != null) {
			nombreTripulacion = crew.getName();
		}
		if (fruit != null) {
			nombreFruta = fruit.getName();
		}
		return "Nombre: " + name + " || Altura: " + size + " || Edad: " + age + " || Recompensa por cabeza: " + bounty
				+ " || Trabajo: " + job + " || Estado: " + status + " || Tripulacion: " + nombreTripulacion
				+ " || Fruta del diablo: " + nombreFruta;

	}

}
