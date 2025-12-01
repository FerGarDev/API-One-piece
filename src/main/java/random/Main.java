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
import java.util.InputMismatchException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	private ArrayList<String> listaPersonajes;
	private static File archivo;

	public Main() {
		listaPersonajes = new ArrayList<String>();
	}

	public void elegirListado() {
		Scanner sc = new Scanner(System.in);
		int eleccion = 0;
		Listar listar = new Listar();
		boolean terminar = false;
		System.out.println();
		while (!terminar) {
			System.out.println("Elige el dato que quieras listar");
			System.out.println("1. Personajes");
			System.out.println("2. Frutas");
			System.out.println("3. Tripulaciones");
			System.out.println("4. Barcos");
			System.out.println("5. Salir");
			System.out.print("Introuce una opcion de las disponibles: ");
			try {
				eleccion = sc.nextInt();
				switch (eleccion) {
				case 1:
					listar.listarPeronsajes();
					break;
				case 2:
					listar.lisarFrutas();
					break;
				case 3:
					listar.listarTripulaciones();
					break;
				case 4:
					listar.listarBarcos();
					break;
				case 5:
					terminar = true;
					break;
				default:
					System.out.println("Porfavor introduzca un numero correcto");
				}
				System.out.println();
			} catch (InputMismatchException e) {
				System.err.println("Introuce un numero, no un caracter\n");
				sc.next();
			}
		}
	}

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
		ArrayList<String> listaGuardar = new ArrayList<String>();
		try (FileReader fr = new FileReader(archivo); BufferedReader br = new BufferedReader(fr)) {
			String linea = br.readLine();
			while (linea != null) {
				if(!linea.equals(nombre)) {
					listaGuardar.add(linea);
				}
				linea = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (FileOutputStream fos = new FileOutputStream(archivo, false);
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {
			if(!listaGuardar.isEmpty()) {
				for(int i = 0; i < listaGuardar.size(); i++) {
					bos.write(listaGuardar.get(i).getBytes());
					bos.write('\n');
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void favoritosOpciones() {
		Scanner sc = new Scanner(System.in);
		int eleccion = 0;
		Listar listar = new Listar();
		listar.cargarFavoritos(archivo);
		boolean terminar = false;
		System.out.println();
		while (!terminar) {
			System.out.println("Anhade o lista personajes favoritos");
			System.out.println("1. Anhadir a la lista de personajes favoritos");
			System.out.println("2. Quitar de la lista de personajes favoritos");
			System.out.println("3. Listar personajes favoritos");
			System.out.println("4. Salir");
			System.out.print("Introuce una opcion de las disponibles: ");
			try {
				eleccion = sc.nextInt();
				switch (eleccion) {
				case 1:
					sc.nextLine();
					System.out.print("Pon el nombre EXACTO del personaje que quieras anhadir: ");
					String nombre = sc.nextLine();
					if (listaPersonajes.contains(nombre)) {
						if (listar.anhadirFavoritos(nombre)) {
							anhadirListaFav(nombre);
						}
					} else {
						System.out.println("No existe ese personaje");
					}
					break;
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
				}
				System.out.println();
			} catch (InputMismatchException e) {
				System.err.println("Introuce un numero, no un caracter\n");
				sc.next();
			}
		}
	}

	public void vaciar() {
		try (FileOutputStream fos = new FileOutputStream(archivo, false)) {
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void comprobar() {
		try (FileReader fr = new FileReader(archivo); BufferedReader fw = new BufferedReader(fr)) {
			String linea = fw.readLine();
			if (!listaPersonajes.contains(linea) && linea != null) {
				System.out.println(
						"ERROR, El archivo no es procesable debido a que contiene contenido no desea, se vaciara automaticamente");
				vaciar();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Main main = new Main();
		main.cargarLista();
		System.out.println(
				"Ponga la ruta del fichero que quiere usar, en caso de poner un archivo con contenido no relacionado con el programa se vaciara su contenido: ");
		String ruta = sc.nextLine();
		archivo = new File(ruta);
		try {
			if (archivo.createNewFile()) {
				System.out.println("Archivo creado");
			} else {
				System.out.println("Archivo ya existente");
				System.out.print("Si quiere vaciar el archivo ponga 1: ");
				String eleccion = sc.nextLine();
				if (eleccion.equals("1")) {
					main.vaciar();
				} else {
					main.comprobar();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		int eleccion = 0;
		boolean terminar = false;
		while (!terminar) {
			System.out.println("==== APLICACION ONE PIECE ====");
			System.out.println("1. Listar");
			System.out.println("2. Favortios");
			System.out.println("3. Datos");
			System.out.println("4. Juegos");
			System.out.println("5. Salir");
			System.out.print("Introuce una opcion de las disponibles: ");
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
					System.out.println("Hasta la proxima");
					break;
				case 4:
					System.out.println("Hola4");
					break;
				case 5:
					System.out.println("Hasta la proxima");
					terminar = true;
					break;
				default:
					System.out.println("Porfavor introduzca un numero correcto");
				}
				System.out.println();
			} catch (InputMismatchException e) {
				System.err.println("Introuce un numero, no un caracter\n");
				sc.next();
			}
		}

	}

	public void cargarLista() {
		ObjectMapper om = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.api-onepiece.com/v2/characters/en"))
				.build();
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			ListaPersonajes datos = om.readValue(response.body(), ListaPersonajes.class);
			Personajes[] usuarios = datos.getPersonajes();
			for (Personajes u : usuarios) {
				listaPersonajes.add(u.getName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
