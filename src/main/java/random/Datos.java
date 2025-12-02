package random;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Datos {

	public void recompensas() {
		ArrayList<Long> listaRecompensas = new ArrayList<Long>();
		Map<Long, String> listaRecompensasPersonaje = new HashMap<Long, String>();
		ObjectMapper om = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.api-onepiece.com/v2/characters/en"))
				.build();
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			ListaPersonajes datos = om.readValue(response.body(), ListaPersonajes.class);
			Personajes[] usuarios = datos.getPersonajes();
			for (Personajes u : usuarios) {
				if (u.getBounty() != null && !u.getBounty().equals("")) {

					String temp = u.getBounty();
					temp = temp.replace(".", "");
					long recompensa = Long.parseLong(temp);
					listaRecompensasPersonaje.put(recompensa, u.getName());
					if (listaRecompensas.size() < 10) {
						listaRecompensas.add(recompensa);
					} else {
						Collections.sort(listaRecompensas);
						if (listaRecompensas.get(0) < recompensa) {
							listaRecompensas.remove(0);
							listaRecompensas.add(recompensa);
						}
					}
				}

			}
			if (!listaRecompensas.isEmpty()) {
				System.out.println();
				System.out.println("=== TOP 10 RECOMPENSAS ===\n");
				for (int i = listaRecompensas.size() - 1; i >= 0; i--) {
					// %n hace de salto de linea, %-40s el nombre ocupa 40 caracteres y se pone a la
					// izquierda, %15d la recompensa ocupa 15 caracteres y se pone a la derecha
					System.out.printf("%-32s %10d%n", listaRecompensasPersonaje.get(listaRecompensas.get(i)),
							listaRecompensas.get(i));

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void tiposFrutas() {
		ObjectMapper om = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.api-onepiece.com/v2/fruits/en"))
				.build();
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			ListarFrutas datos = om.readValue(response.body(), ListarFrutas.class);
			Fruta[] usuarios = datos.getFrutas();
			System.out.println();
			int contadorTotal = 0;
			int contadorParamecia = 0;
			int contadorZoan = 0;
			int contadorLogia = 0;
			int contadorZoanMitica = 0;
			int contadorZoanAntigua = 0;
			int contadorSmile = 0;
			for (Fruta u : usuarios) {
				contadorTotal++;
				switch (u.getType()) {
				case "Paramecia":
					contadorParamecia++;
					break;
				case "Zoan":
					contadorZoan++;
					break;
				case "Logia":
					contadorLogia++;
					break;
				case "Smile":
					contadorSmile++;
					break;
				case "Zoan Mythique":
					contadorZoanMitica++;
					break;
				case "Zoan Antique":
					contadorZoanAntigua++;
					break;
				}
			}
			System.out.println("=== DISTRIBUCIÓN DE FRUTAS ===\n");
			System.out.printf("%-30s %5d%n", "Total de frutas", contadorTotal);
			System.out.printf("%-30s %5d%n", "Total de Paramecias", contadorParamecia);
			System.out.printf("%-30s %5d%n", "Total de Zoans", contadorZoan);
			System.out.printf("%-30s %5d%n", "Total de Logias", contadorLogia);
			System.out.printf("%-30s %5d%n", "Total de Zoans Miticas", contadorZoanMitica);
			System.out.printf("%-30s %5d%n", "Total de Zoans Prehistoricas", contadorZoanAntigua);
			System.out.printf("%-30s %5d%n", "Total de Smiles", contadorSmile);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void promedio() {
		ObjectMapper om = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.api-onepiece.com/v2/crews/en"))
				.build();
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			ListarTripulacion datos = om.readValue(response.body(), ListarTripulacion.class);
			Tripulacion[] usuarios = datos.getTripulaciones();
			int maximo = 0;
			String nombreMax = null;
			int minimo = 999;
			String nombreMin = null;
			int total = 0;
			int cantidad = 0;
			int temp = 0;
			for (Tripulacion u : usuarios) {
				if (u.getNumber() != null && !u.getNumber().equals("") && !u.getNumber().equals("inconnu")) {
					if (u.getNumber().equals("> 85")) {
						temp = 85;
					} else {
						temp = Integer.parseInt(u.getNumber());

					}
					cantidad++;
					total = total + temp;
					if (maximo < temp) {
						maximo = temp;
						nombreMax = u.getName();
					}
					if (minimo > temp) {
						minimo = temp;
						nombreMin = u.getName();
					}
				}
			}
			total = total / cantidad;
			System.out.println();
			System.out.println("=== PROMEDIO TRIPULANTES ===\n");
			System.out.printf("%-10s %1d%n", "Promedio", total);
			System.out.printf("%-10s %-25s %10d%n", "Maximo", nombreMax, maximo);
			System.out.printf("%-10s %-25s %10d%n", "Minimo", nombreMin, minimo);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
