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

	public void recompensas(ListaPersonajes datos) {
		// Se declara un array de recompensas y otro del la recompensa con el personaje
		ArrayList<Long> listaRecompensas = new ArrayList<Long>();
		Map<Long, String> listaRecompensasPersonaje = new HashMap<Long, String>();

		Personajes[] usuarios = datos.getPersonajes();
		for (Personajes u : usuarios) {
			// Primero se confirma que el dato exista
			if (u.getBounty() != null && !u.getBounty().equals("")) {

				// Se pasa el dato string a un long
				String temp = u.getBounty();
				temp = temp.replace(".", "");
				long recompensa = Long.parseLong(temp);
				// Se añade al mapa junto con su nombre y a la lista si todavia no tiene 10
				// espacios
				listaRecompensasPersonaje.put(recompensa, u.getName());
				if (listaRecompensas.size() < 10) {
					listaRecompensas.add(recompensa);
				} else {
					// Si ya tiene 10 se ordenara con collections, para comporar el primero, el mas
					// bajo, y en caso de ser menor ser borrado
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
			// Gracias al map podremos imprimir los resultados junto con el nombre que le
			// corresponde a cada recompensa
			for (int i = listaRecompensas.size() - 1; i >= 0; i--) {
				// %n hace de salto de linea, %-40s el nombre ocupa 40 caracteres y se pone a la
				// izquierda, %15d la recompensa ocupa 15 caracteres y se pone a la derecha
				System.out.printf("%-32s %10d%n", listaRecompensasPersonaje.get(listaRecompensas.get(i)),
						listaRecompensas.get(i));

			}
		}

	}

	public void tiposFrutas(ListarFrutas datos) {

		Fruta[] usuarios = datos.getFrutas();
		System.out.println();
		// Se pone un contador total y un contador para cada dato
		int contadorTotal = 0;
		int contadorParamecia = 0;
		int contadorZoan = 0;
		int contadorLogia = 0;
		int contadorZoanMitica = 0;
		int contadorZoanAntigua = 0;
		int contadorSmile = 0;
		for (Fruta u : usuarios) {
			// El total siempre se sumara y gracias al switch se sumara del tipo
			// correspondiente
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
		// Se imprimen los resultados con el printf
		System.out.println("=== DISTRIBUCIÓN DE FRUTAS ===\n");
		System.out.printf("%-30s %5d%n", "Total de frutas", contadorTotal);
		System.out.printf("%-30s %5d%n", "Total de Paramecias", contadorParamecia);
		System.out.printf("%-30s %5d%n", "Total de Zoans", contadorZoan);
		System.out.printf("%-30s %5d%n", "Total de Logias", contadorLogia);
		System.out.printf("%-30s %5d%n", "Total de Zoans Miticas", contadorZoanMitica);
		System.out.printf("%-30s %5d%n", "Total de Zoans Prehistoricas", contadorZoanAntigua);
		System.out.printf("%-30s %5d%n", "Total de Smiles", contadorSmile);

	}

	public void promedio(ListarTripulacion datos) {
		Tripulacion[] usuarios = datos.getTripulaciones();
		// Se determinan los variables a usar
		int maximo = 0;
		String nombreMax = null;
		int minimo = 999;
		String nombreMin = null;
		int total = 0;
		int cantidad = 0;
		int temp = 0;
		for (Tripulacion u : usuarios) {
			// Se confirma que no haya errores en el dato
			if (u.getNumber() != null && !u.getNumber().equals("") && !u.getNumber().equals("inconnu")) {
				// Existe un dato especifico de la API que sale mal, este if es para tal caso
				if (u.getNumber().equals("> 85")) {
					temp = 85;
				} else {
					// Se pasa a int
					temp = Integer.parseInt(u.getNumber());

				}
				// Se actulizan los datos de maximo y minimo si se cumplen las condiciones y los
				// datos de total y cantidad
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
		// Se actuliza correctamente el dato de total y se imprime con printf
		total = total / cantidad;
		System.out.println();
		System.out.println("=== PROMEDIO TRIPULANTES ===\n");
		System.out.printf("%-10s %1d%n", "Promedio", total);
		System.out.printf("%-10s %-25s %10d%n", "Maximo", nombreMax, maximo);
		System.out.printf("%-10s %-25s %10d%n", "Minimo", nombreMin, minimo);

	}

	// El ultimo dato sera la cantida de barcos por tripulacion
	public void barcosPorTripulacion(ListarBarcos datos, ListarTripulacion datos2) {
		Barco[] usuarios = datos.getBarco();

		Tripulacion[] usuarios2 = datos2.getTripulaciones();

		// Declaro un map para almacenar la crew con la cantidad de barcos
		Map<String, Integer> barcosTripulacion = new HashMap<String, Integer>();
		System.out.println();
		System.out.println("=== CANTIDAD DE BARCOS POR TRIPULACION ===\n");
		// Se hacen dos bucle revisando todos los barcos en cada tripulacion, si estos
		// coinciden se mete al mapa
		for (Tripulacion u : usuarios2) {
			for (Barco u2 : usuarios) {
				if (u2.getCrew() != null && u.getName().equals(u2.getCrew().getName())) {
					// Si ua existe el dato se suma uno, sino se pone con 1
					if (!barcosTripulacion.containsKey(u.getName())) {
						barcosTripulacion.put(u.getName(), 1);
					} else {
						barcosTripulacion.put(u.getName(), barcosTripulacion.get(u.getName()) + 1);
					}
				}
			}
		}
		// Se imprimen mediante crear una lista de las keys y se imprime con print f
		ArrayList<String> keys = new ArrayList<>(barcosTripulacion.keySet());
		for (int i = 0; i < barcosTripulacion.size(); i++) {
			System.out.printf("%-30s %5d%n", keys.get(i), barcosTripulacion.get(keys.get(i)));
		}

	}

}
