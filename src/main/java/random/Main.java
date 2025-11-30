package random;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Main main = new Main();
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
					System.out.println("Hola2");
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

}
