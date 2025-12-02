package random;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Listar {

	private ArrayList<Personajes> listaFavoritos;

	public Listar() {
		listaFavoritos = new ArrayList<Personajes>();
	}

	// Los cuatro metodos de listado funcionaran igual siendo cada uno para un dato
	// a listar
	public void listarPeronsajes() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> personajesLista = new ArrayList<String>();
		ObjectMapper om = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.api-onepiece.com/v2/characters/en"))
				.build();
		try {
			// Se preguntara el si quieres que salgan los datos listados o no
			System.out.print("Si quieres los resultados paginados ponga 1: ");
			String decision = sc.nextLine();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			ListaPersonajes datos = om.readValue(response.body(), ListaPersonajes.class);
			Personajes[] usuarios = datos.getPersonajes();

			System.out.println("Personajes:\n");
			if (decision.equals("1")) {
				boolean fin = false;
				int contador = 0;
				// Primero se añadiran todos los peronajes a la lista hasta que esten todos
				while (!fin) {
					Personajes u = usuarios[contador];
					personajesLista.add(u.toString());
					contador++;
					if (contador == usuarios.length) {
						fin = true;
					}
				}
				int elegirPagina = 0;
				// Seguimos con un for que imprime hasta 5 datos
				for (int i = 0; i < personajesLista.size(); i++) {
					System.out.println(personajesLista.get(i));
					if ((i + 1) % 5 == 0 && i != 0) {
						// una vez impreso 5 se cumple el if y pregunta por opciones
						boolean seguir = true;
						do {
							System.out.println("Salir (0) || Siguiente pagina (1) || Pagina anterior (2)");
							try {
								elegirPagina = sc.nextInt();
								switch (elegirPagina) {
								case 0:
									// En la primera se saldra y no se listara mas
									i = personajesLista.size();
									seguir = false;
									break;
								case 1:
									// En la segunda se pasa de pagina permitiendo seguir imprimiendo
									clearConsole();
									seguir = false;
									break;
								case 2:
									// Si no hay pagina anterior se indicara, si hay se le restaran 10 valores a la
									// i asi imprimiendo la pagina anterior
									clearConsole();
									if (i == 4 || i == -1) {
										System.out.println("No hay pagina anterior");
										i = -1;
									} else {
										i = i - 10;
										seguir = false;
									}
									break;
								default:
									System.out.println("Elige un numero correcto");
								}
							} catch (InputMismatchException e) {
								System.err.println("Introuce un numero, no un caracter\n");
								sc.next();
							}
						} while (seguir);
					}
				}
				// Sino se quieren paginados se daran de manera normal
			} else {
				for (Personajes u : usuarios) {
					System.out.println(u.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void lisarFrutas() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> personajesLista = new ArrayList<String>();
		ObjectMapper om = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.api-onepiece.com/v2/fruits/en"))
				.build();
		try {
			System.out.print("Si quieres los resultados paginados ponga 1: ");
			String decision = sc.nextLine();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			ListarFrutas datos = om.readValue(response.body(), ListarFrutas.class);
			Fruta[] usuarios = datos.getFrutas();

			System.out.println("Frutas:\n");
			if (decision.equals("1")) {
				boolean fin = false;
				int contador = 0;
				while (!fin) {
					Fruta u = usuarios[contador];
					personajesLista.add(u.toString());
					contador++;
					if (contador == usuarios.length) {
						fin = true;
					}
				}
				int elegirPagina = 0;
				for (int i = 0; i < personajesLista.size(); i++) {
					System.out.println(personajesLista.get(i));
					if ((i + 1) % 5 == 0 && i != 0) {
						boolean seguir = true;
						do {
							System.out.println("Salir (0) || Siguiente pagina (1) || Pagina anterior (2)");
							try {
								elegirPagina = sc.nextInt();
								switch (elegirPagina) {
								case 0:
									i = personajesLista.size();
									seguir = false;
									break;
								case 1:
									clearConsole();
									seguir = false;
									break;
								case 2:
									clearConsole();
									if (i == 4 || i == -1) {
										System.out.println("No hay pagina anterior");
										i = -1;
									} else {
										i = i - 10;
										seguir = false;
									}
									break;
								default:
									System.out.println("Elige un numero correcto");
								}
							} catch (InputMismatchException e) {
								System.err.println("Introuce un numero, no un caracter\n");
								sc.next();
							}
						} while (seguir);
					}
				}
			} else {
				for (Fruta u : usuarios) {
					System.out.println(u.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void listarTripulaciones() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> personajesLista = new ArrayList<String>();
		ObjectMapper om = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.api-onepiece.com/v2/crews/en"))
				.build();
		try {
			System.out.print("Si quieres los resultados paginados ponga 1: ");
			String decision = sc.nextLine();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			ListarTripulacion datos = om.readValue(response.body(), ListarTripulacion.class);
			Tripulacion[] usuarios = datos.getTripulaciones();

			System.out.println("Tripulaciones:\n");
			if (decision.equals("1")) {
				boolean fin = false;
				int contador = 0;
				while (!fin) {
					Tripulacion u = usuarios[contador];
					personajesLista.add(u.toString());
					contador++;
					if (contador == usuarios.length) {
						fin = true;
					}
				}
				int elegirPagina = 0;
				for (int i = 0; i < personajesLista.size(); i++) {
					System.out.println(personajesLista.get(i));
					if ((i + 1) % 5 == 0 && i != 0) {
						boolean seguir = true;
						do {
							System.out.println("Salir (0) || Siguiente pagina (1) || Pagina anterior (2)");
							try {
								elegirPagina = sc.nextInt();
								switch (elegirPagina) {
								case 0:
									i = personajesLista.size();
									seguir = false;
									break;
								case 1:
									clearConsole();
									seguir = false;
									break;
								case 2:
									clearConsole();
									if (i == 4 || i == -1) {
										System.out.println("No hay pagina anterior");
										i = -1;
									} else {
										i = i - 10;
										seguir = false;
									}
									break;
								default:
									System.out.println("Elige un numero correcto");
								}
							} catch (InputMismatchException e) {
								System.err.println("Introuce un numero, no un caracter\n");
								sc.next();
							}
						} while (seguir);
					}
				}
			} else {
				for (Tripulacion u : usuarios) {
					System.out.println(u.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void listarBarcos() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> personajesLista = new ArrayList<String>();
		ObjectMapper om = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.api-onepiece.com/v2/boats/en"))
				.build();
		try {
			System.out.print("Si quieres los resultados paginados ponga 1: ");
			String decision = sc.nextLine();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			ListarBarcos datos = om.readValue(response.body(), ListarBarcos.class);
			Barco[] usuarios = datos.getBarco();

			System.out.println("Barcos:\n");
			if (decision.equals("1")) {
				boolean fin = false;
				int contador = 0;
				while (!fin) {
					Barco u = usuarios[contador];
					personajesLista.add(u.toString());
					contador++;
					if (contador == usuarios.length) {
						fin = true;
					}
				}
				int elegirPagina = 0;
				for (int i = 0; i < personajesLista.size(); i++) {
					System.out.println(personajesLista.get(i));
					if ((i + 1) % 5 == 0 && i != 0) {
						boolean seguir = true;
						do {
							System.out.println("Salir (0) || Siguiente pagina (1) || Pagina anterior (2)");
							try {
								elegirPagina = sc.nextInt();
								switch (elegirPagina) {
								case 0:
									i = personajesLista.size();
									seguir = false;
									break;
								case 1:
									clearConsole();
									seguir = false;
									break;
								case 2:
									clearConsole();
									if (i == 4 || i == -1) {
										System.out.println("No hay pagina anterior");
										i = -1;
									} else {
										i = i - 10;
										seguir = false;
									}
									break;
								default:
									System.out.println("Elige un numero correcto");
								}
							} catch (InputMismatchException e) {
								System.err.println("Introuce un numero, no un caracter\n");
								sc.next();
							}
						} while (seguir);
					}
				}
			} else {
				for (Barco u : usuarios) {
					System.out.println(u.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Se listan con un for
	public void listarFavoritos() {
		if (listaFavoritos.isEmpty()) {
			System.out.println("No hay nadie en la lista de favoritos");
		} else {
			for (int i = 0; i < listaFavoritos.size(); i++) {
				Personajes u = listaFavoritos.get(i);
				System.out.println(u.toString());
			}
		}
	}

	public boolean anhadirFavoritos(String nombre) {
		boolean anhadir = true;
		// Primero se revisa que el personaje este en la lista
		for (int i = 0; i < listaFavoritos.size(); i++) {
			Personajes u = listaFavoritos.get(i);
			if (u.getName().equals(nombre)) {
				System.out.println("El personaje ya esta en la lista");
				anhadir = false;
			}
		}
		if (anhadir) {
			// Si no esta se busca en la API en personaje que coincida con el nombre y se
			// añade a los favs
			ObjectMapper om = new ObjectMapper();
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://api.api-onepiece.com/v2/characters/en")).build();
			try {
				HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
				ListaPersonajes datos = om.readValue(response.body(), ListaPersonajes.class);
				Personajes[] usuarios = datos.getPersonajes();
				for (Personajes u : usuarios) {
					if (u.getName().equals(nombre)) {
						listaFavoritos.add(u);
						System.out.println("Se añadio correctamente");
						return true;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// Se recorreo la lista y se quita el que coincida
	public boolean quitarFavoritos(String nombre) {
		for (int i = 0; i < listaFavoritos.size(); i++) {
			Personajes u = listaFavoritos.get(i);
			if (u.getName().equals(nombre)) {
				listaFavoritos.remove(i);
				System.out.println("El personaje se quito");
				return true;
			}
		}
		System.out.println("Ese personaje no estaba en la lista");
		return false;
	}

	// Se imprimen 50 espacios vacios para simular la limpieza de la consola
	public static void clearConsole() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	public void cargarFavoritos(File file) {
		// Primero se pondran los personajes del fichero de favoritos en una lista
		ArrayList<String> nombrePersonaje = new ArrayList<String>();
		try (FileReader fr = new FileReader(file); BufferedReader fw = new BufferedReader(fr)) {
			String linea = fw.readLine();
			while (linea != null) {
				nombrePersonaje.add(linea);
				linea = fw.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Si la lista contiene personajes se añadiran a la lista de los favoritos, esta
		// lo hace comprobando el personaje que coincida con su nombre asi añadiendolo
		if (!nombrePersonaje.isEmpty()) {
			Set<String> nombres = new HashSet<>(nombrePersonaje);
			ObjectMapper om = new ObjectMapper();
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://api.api-onepiece.com/v2/characters/en")).build();
			try {
				HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
				ListaPersonajes datos = om.readValue(response.body(), ListaPersonajes.class);
				Personajes[] usuarios = datos.getPersonajes();
				for (Personajes u : usuarios) {
					if (nombres.contains(u.getName())) {
						listaFavoritos.add(u);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Mismo metodo que el anterior que retornara la lista de los favoritos para los
	// juegos
	public ArrayList<Personajes> cargarFavoritosJuegos(File file) {
		ArrayList<String> nombrePersonaje = new ArrayList<String>();
		try (FileReader fr = new FileReader(file); BufferedReader fw = new BufferedReader(fr)) {
			String linea = fw.readLine();
			while (linea != null) {
				nombrePersonaje.add(linea);
				linea = fw.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!nombrePersonaje.isEmpty()) {
			Set<String> nombres = new HashSet<>(nombrePersonaje);
			ObjectMapper om = new ObjectMapper();
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://api.api-onepiece.com/v2/characters/en")).build();
			try {
				HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
				ListaPersonajes datos = om.readValue(response.body(), ListaPersonajes.class);
				Personajes[] usuarios = datos.getPersonajes();
				for (Personajes u : usuarios) {
					if (nombres.contains(u.getName())) {
						listaFavoritos.add(u);
					}
				}
				return listaFavoritos;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return listaFavoritos;
	}

}
