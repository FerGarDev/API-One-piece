package random;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	// Variable de lista de nombres de todos los personajes con su trabajo y archivo
	private Map<String, String> listaPersonajes;
	private static File archivo;
	// Se declara la cache para que todos los metodos sean mucho mas rapidos
	private static Map<String, ListaPersonajes> cache = new HashMap<String, ListaPersonajes>();
	private static Map<String, ListarFrutas> cache2 = new HashMap<String, ListarFrutas>();
	private static Map<String, ListarTripulacion> cache3 = new HashMap<String, ListarTripulacion>();
	private static Map<String, ListarBarcos> cache4 = new HashMap<String, ListarBarcos>();

	public Main() {
		listaPersonajes = new HashMap<String, String>();
	}

	public void elegirListado() {
		// Aqui tendremos otro menu para el apartado de lista para poder elegir que dato
		// listar
		Scanner sc = new Scanner(System.in);
		int eleccion = 0;
		Listar listar = new Listar();
		boolean terminar = false;
		System.out.println(
				"===========================================================================================================================");
		while (!terminar) {
			System.out.println("---- Listar ----");
			System.out.println("1. Personajes");
			System.out.println("2. Frutas");
			System.out.println("3. Tripulaciones");
			System.out.println("4. Barcos");
			System.out.println("5. Salir");
			System.out.print("Introduce una opcion de las disponibles: ");
			try {
				eleccion = sc.nextInt();
				switch (eleccion) {
				case 1:
					listar.listarPeronsajes(cache.get("https://api.api-onepiece.com/v2/characters/en"));
					break;
				case 2:
					listar.lisarFrutas(cache2.get("https://api.api-onepiece.com/v2/fruits/en"));
					break;
				case 3:
					listar.listarTripulaciones(cache3.get("https://api.api-onepiece.com/v2/crews/en"));
					break;
				case 4:
					listar.listarBarcos(cache4.get("https://api.api-onepiece.com/v2/boats/en"));
					break;
				case 5:
					terminar = true;
					break;
				default:
					System.out.println("Porfavor introduzca un numero correcto");
				}
				System.out.println(
						"===========================================================================================================================");
			} catch (InputMismatchException e) {
				System.err.println("Introduce un numero, no un caracter");
				System.out.println(
						"===========================================================================================================================");
				sc.next();
			}
		}
	}

	// Usamos BufferedOutputStream y escribimos el nombre junto con el salto de
	// linea
	public void anhadirListaFav(String nombre) {
		try (FileOutputStream fos = new FileOutputStream(archivo, true);
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {
			bos.write(nombre.getBytes());
			bos.write('\n');
			bos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void quitarListaFav(String nombre) {
		// Primero se guardaran los datos en una lista recorriendo el fichero, excepto
		// el que se va a quitar
		ArrayList<String> listaGuardar = new ArrayList<String>();
		try (FileReader fr = new FileReader(archivo); BufferedReader br = new BufferedReader(fr)) {
			String linea = br.readLine();
			while (linea != null) {
				if (!linea.equals(nombre)) {
					listaGuardar.add(linea);
				}
				linea = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Luego se vacia el fichero para añadir todos los datos de la lista con un
		// salto de linea
		try (FileOutputStream fos = new FileOutputStream(archivo, false);
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {
			if (!listaGuardar.isEmpty()) {
				for (int i = 0; i < listaGuardar.size(); i++) {
					bos.write(listaGuardar.get(i).getBytes());
					bos.write('\n');
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void favoritosOpciones() {
		// Aqui tendremos un menu para acceder a las opciones de los favoritos
		Scanner sc = new Scanner(System.in);
		int eleccion = 0;
		Listar listar = new Listar();
		listar.cargarFavoritos(archivo, cache.get("https://api.api-onepiece.com/v2/characters/en"));
		boolean terminar = false;
		System.out.println(
				"===========================================================================================================================");
		while (!terminar) {
			System.out.println("---- Favortios ----");
			System.out.println("1. Añadir a la lista de personajes favoritos");
			System.out.println("2. Quitar de la lista de personajes favoritos");
			System.out.println("3. Listar personajes favoritos");
			System.out.println("4. Salir");
			System.out.print("Introduce una opcion de las disponibles: ");
			try {
				eleccion = sc.nextInt();
				switch (eleccion) {
				// El primer caso mira que el nombre sea correcto, si lo esta se comprueba que
				// se pueda añadir y si se añade tambien se añade al fichero
				case 1:
					sc.nextLine();
					System.out.print("Pon el nombre EXACTO del personaje que quieras añadir: ");
					String nombre = sc.nextLine();
					ArrayList<String> keys = new ArrayList<>(listaPersonajes.keySet());
					if (keys.contains(nombre)) {
						if (listar.anhadirFavoritos(nombre,
								cache.get("https://api.api-onepiece.com/v2/characters/en"))) {
							anhadirListaFav(nombre);
						}
					} else {
						System.out.println("No existe ese personaje");
					}
					break;
				// El segundo caso comprueba que el dato se pueda quitar de manera correcta de
				// la lista y si se quita se quitara del fichero tambien
				case 2:
					sc.nextLine();
					System.out.print("Pon el nombre EXACTO del personaje que quieres quitar: ");
					String nombre2 = sc.nextLine();
					if (listar.quitarFavoritos(nombre2)) {
						quitarListaFav(nombre2);
					}
					break;
				case 3:
					listar.listarFavoritos();
					break;
				case 4:
					terminar = true;
					break;
				default:
					System.out.println("Porfavor introduzca un numero correcto");
					break;
				}
				System.out.println(
						"===========================================================================================================================");
			} catch (InputMismatchException e) {
				System.err.println("Introduce un numero, no un caracter");
				System.out.println(
						"===========================================================================================================================");
				sc.next();
			}
		}
	}

	// Se vacia abriendo el fichero con false asi dejandolo vacio
	public void vaciar() {
		try (FileOutputStream fos = new FileOutputStream(archivo, false)) {
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Se revisara el fichero damo siendo que si contiene contenido el cual no sera
	// usado por la app se vaciara
	public boolean comprobar() {
		Scanner sc = new Scanner(System.in);
		try (FileReader fr = new FileReader(archivo); BufferedReader fw = new BufferedReader(fr)) {
			String linea = fw.readLine();
			// Se comprueba con un contains de la lista
			ArrayList<String> keys = new ArrayList<>(listaPersonajes.keySet());
			if (!keys.contains(linea) && linea != null) {
				System.out.println("ERROR, El archivo no es procesable debido a que contiene contenido no desea");
				System.out.print("Ponga BORRAR si no tiene problema en que se borre su contenido: ");
				String borrar = sc.nextLine();
				if (borrar.equals("BORRAR")) {
					vaciar();
					System.out.println("Archivo vaciado correctamente");
					return true;
				} else {
					return false;
				}
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void datosSeleccion() {
		// Aqui se contara con un menu para acceder a las opciones de los datos que se
		// pueden listar
		Scanner sc = new Scanner(System.in);
		Datos datos = new Datos();
		int eleccion = 0;
		boolean terminar = false;
		System.out.println(
				"===========================================================================================================================");
		while (!terminar) {
			System.out.println("---- Datos interesantes ----");
			System.out.println("1. Top 10 personajes con mayor recompensa");
			System.out.println("2. Distribucion de los tipos de frutas del diablo");
			System.out.println("3. Promedio de personas por tripulacion");
			System.out.println("4. Barcos por tripulacion");
			System.out.println("5. Salir");
			System.out.print("Introduce una opcion de las disponibles: ");
			try {
				eleccion = sc.nextInt();
				switch (eleccion) {
				case 1:
					datos.recompensas(cache.get("https://api.api-onepiece.com/v2/characters/en"));
					break;
				case 2:
					datos.tiposFrutas(cache2.get("https://api.api-onepiece.com/v2/fruits/en"));
					break;
				case 3:
					datos.promedio(cache3.get("https://api.api-onepiece.com/v2/crews/en"));
					break;
				case 4:
					datos.barcosPorTripulacion(cache4.get("https://api.api-onepiece.com/v2/boats/en"),
							cache3.get("https://api.api-onepiece.com/v2/crews/en"));
					break;
				case 5:
					terminar = true;
					break;
				default:
					System.out.println("Porfavor introduzca un numero correcto");
					break;
				}
				System.out.println(
						"===========================================================================================================================");
			} catch (InputMismatchException e) {
				System.err.println("Introduce un numero, no un caracter");
				System.out.println(
						"===========================================================================================================================");
				sc.next();
			}
		}
	}

	public void juegos() {
		// Aqui se contara con un menu para acceder a los diferentes juegos
		Scanner sc = new Scanner(System.in);
		Ahorcado ahorcado = new Ahorcado();
		int eleccion = 0;
		boolean terminar = false;
		// Se pregunta si se jugara o no con favoritos
		System.out.println(
				"===========================================================================================================================");
		System.out.print("Si quieres jugar con tus personajes favoritos, ponga 1: ");
		String siLista = sc.nextLine();
		while (!terminar) {
			System.out.println("---- Ahorcado ----");
			System.out.println("1. Jugar");
			System.out.println("2. Salir");
			System.out.print("Introduce una opcion de las disponibles: ");
			try {
				eleccion = sc.nextInt();
				switch (eleccion) {
				case 1:
					// Si se juga con favoritos se usara su lista, se desordenara y useremos el
					// primero
					if (siLista.equals("1")) {
						Listar temp = new Listar();
						ArrayList<Personajes> listaFavTemp = temp.cargarFavoritosJuegos(archivo,
								cache.get("https://api.api-onepiece.com/v2/characters/en"));
						// En caso de estar vacia no se podra jugar
						if (listaFavTemp.isEmpty()) {
							System.out.println("No tienes nadie en la lista de favs, no puedes jugar");
						} else {
							// Acedemos a todo de nuevo para poder conseguir el trabajo del personaje
							int random = (int) (Math.random() * listaFavTemp.size());
							Personajes personaje = listaFavTemp.get(random);
							if (personaje.getJob() == null || personaje.getJob().equals("")) {
								ahorcado.juegoAhoracado(personaje.getName(), "No hace nada");
							} else {
								ahorcado.juegoAhoracado(personaje.getName(), personaje.getJob());
							}
						}
						// Si se juega con todos los personajes se usara la lista de todos los
						// personajes
					} else {
						int random = (int) (Math.random() * listaPersonajes.size());
						ArrayList<String> keys = new ArrayList<>(listaPersonajes.keySet());
						ahorcado.juegoAhoracado(keys.get(random), listaPersonajes.get(keys.get(random)));
					}
					break;
				case 2:
					terminar = true;
					break;
				default:
					System.out.println("Porfavor introduzca un numero correcto");
					break;
				}
				System.out.println(
						"===========================================================================================================================");
			} catch (InputMismatchException e) {
				System.err.println("Introuce un numero, no un caracter");
				System.out.println(
						"===========================================================================================================================");
				sc.next();
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Main main = new Main();
		// Revisa si hay conexion para ver si se ejecuta la aplicacion
		if (main.cargarLista() || main.cargarLista2() || main.cargarLista3() || main.cargarLista4()) {
			System.out.println(
					"Hay un problema con la conexion a la API, no es posible conectarse por tanto no es posible conectarse a la aplicacion");
		} else {
			// Se pide la ruta del fichero o el fichero
			System.out.println(
					"Ponga la ruta del fichero que quiere usar, en caso de poner un archivo con contenido no relacionado con el programa se vaciara su contenido: ");
			System.out.println("NO PONGA ARCHIVOS IMPORTANTES PORQUE SERAN VACIADOS");
			boolean valido = false;
			while (!valido) {
				try {
					String ruta = sc.nextLine();
					archivo = new File(ruta);
					// En caso de no existir se crea
					if (archivo.createNewFile()) {
						System.out.println("Archivo creado");
						valido = true;
					} else {
						// Sis ya existe se pregunta si se quiere vaciar
						System.out.println("Archivo ya existente");
						System.out.print("Si quiere vaciar el archivo ponga 1: ");
						String eleccion = sc.nextLine();
						if (eleccion.equals("1")) {
							main.vaciar();
							System.out.println("Archivo vaciado exitosamente");
							valido = true;
						} else {
							if (main.comprobar()) {
								System.out.println("Archivo cargado existosamente");
								valido = true;
							} else {
								System.out.println("Ponga otro archivo:");
							}
						}
					}
				} catch (IOException e) {
					System.out.println("No es valido ese fichero, vuelva a ponerlo: ");
				}
			}
			// El menu sera tratado con un switch con las 4 opciones diferentes
			int eleccion = 0;
			boolean terminar = false;
			System.out.println();
			while (!terminar) {
				System.out.println("==== APLICACION ONE PIECE ====");
				System.out.println("1. Listar");
				System.out.println("2. Favortios");
				System.out.println("3. Datos interesantes");
				System.out.println("4. Jugar ahorcado");
				System.out.println("5. Salir");
				System.out.print("Introduce una opcion de las disponibles: ");
				try {
					eleccion = sc.nextInt();
					switch (eleccion) {
					case 1:
						main.elegirListado();
						break;
					case 2:
						main.favoritosOpciones();
						break;
					case 3:
						main.datosSeleccion();
						break;
					case 4:
						main.juegos();
						break;
					case 5:
						System.out.println();
						terminar = true;
						break;
					default:
						System.out.println("Porfavor introduzca un numero correcto");
						System.out.println(
								"===========================================================================================================================");
						break;
					}
				} catch (InputMismatchException e) {
					System.err.println("Introuce un numero, no un caracter");
					System.out.println(
							"===========================================================================================================================");
					sc.next();
				}
			}
			System.out.println("Hasta la proxima");
		}

	}

	// Este metodo accede a la API cargando cada personaje en una lista, se usara
	// siempre al cargar la aplicacion
	public boolean cargarLista() {
		ObjectMapper om = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.api-onepiece.com/v2/characters/en"))
				.build();
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			ListaPersonajes datos = om.readValue(response.body(), ListaPersonajes.class);
			// Se añaden los datos a la cache a la cual estaremos siempre accediendo para
			// ahorra tiempo
			cache.put("https://api.api-onepiece.com/v2/characters/en", datos);
			Personajes[] usuarios = datos.getPersonajes();
			for (Personajes u : usuarios) {
				// Si es null o no hay nada tendra valor por defecto
				if (u.getJob() == null || u.getJob().equals("")) {
					listaPersonajes.put(u.getName(), "No hace nada");
				} else {
					listaPersonajes.put(u.getName(), u.getJob());
				}
			}
			return false;
			// Si salta un error se devolvera true
		} catch (IOException e) {
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return true;
		}
	}

	public boolean cargarLista2() {
		ObjectMapper om = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.api-onepiece.com/v2/fruits/en"))
				.build();
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			ListarFrutas datos = om.readValue(response.body(), ListarFrutas.class);
			cache2.put("https://api.api-onepiece.com/v2/fruits/en", datos);
			return false;
		} catch (IOException e) {
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return true;
		}
	}

	public boolean cargarLista3() {
		ObjectMapper om = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.api-onepiece.com/v2/crews/en"))
				.build();
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			ListarTripulacion datos = om.readValue(response.body(), ListarTripulacion.class);
			cache3.put("https://api.api-onepiece.com/v2/crews/en", datos);
			return false;
		} catch (IOException e) {
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return true;
		}
	}

	public boolean cargarLista4() {
		ObjectMapper om = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.api-onepiece.com/v2/boats/en"))
				.build();
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			ListarBarcos datos = om.readValue(response.body(), ListarBarcos.class);
			cache4.put("https://api.api-onepiece.com/v2/boats/en", datos);
			return false;
		} catch (IOException e) {
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return true;
		}
	}

}
