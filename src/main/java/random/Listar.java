package random;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Listar {

	public void listarPeronsajes() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> personajesLista = new ArrayList<String>();
		ObjectMapper om = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.api-onepiece.com/v2/characters/en"))
				.build();
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			ListaPersonajes datos = om.readValue(response.body(), ListaPersonajes.class);
			Personajes[] usuarios = datos.getPersonajes();

			System.out.println("Personajes:\n");
			boolean fin = false;
			int contador = 0;
			while (!fin) {
				Personajes u = usuarios[contador];
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
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			ListarFrutas datos = om.readValue(response.body(), ListarFrutas.class);
			Fruta[] usuarios = datos.getFrutas();

			System.out.println("Frutas:\n");
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
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			ListarTripulacion datos = om.readValue(response.body(), ListarTripulacion.class);
			Tripulacion[] usuarios = datos.getTripulaciones();

			System.out.println("Tripulaciones:\n");
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
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			ListarBarcos datos = om.readValue(response.body(), ListarBarcos.class);
			Barco[] usuarios = datos.getBarco();

			System.out.println("Barcos:\n");
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
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void clearConsole() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

}
