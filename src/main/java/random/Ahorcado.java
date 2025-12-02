package random;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Ahorcado {

	public void juegoAhoracado(String nombrePersonaje) {

		Scanner sc = new Scanner(System.in);
		System.out.println();
		boolean partidaTerminada = false;

		// Se pone la palabra en un array de chars
		String palabraAleatoria = nombrePersonaje;
		char[] letras = palabraAleatoria.toCharArray();

		int limiteFallos = 6;

		// Se declara y rellena el array que mostrara un _ por letra siempre que esta
		// sea una letra y no otro cacracter
		char[] letrasAcertadas = new char[letras.length];
		for (int i = 0; i < letras.length; i++) {
			if (letras[i] >= 65 && letras[i] <= 90 || letras[i] >= 97 && letras[i] <= 122) {
				letrasAcertadas[i] = '_';
			} else {
				letrasAcertadas[i] = letras[i];
			}
		}

		// Set de letras usuadas para mostrarlas bien
		Set<Character> letrasUsadas = new HashSet<>();

		while (!partidaTerminada) {

			String input;
			char letra = 0;

			ahorcado(limiteFallos);

			for (char c : letrasAcertadas) {
				System.out.print(c + " ");
			}

			// Se imprimen las letras usadas
			System.out.println("\n");
			System.out.print("Letras usadas: ");
			System.out.println(letrasUsadas);

			// Aqui verificaremos que la entrada de la letra es correcta
			boolean seguirVerificacion = true;
			while (seguirVerificacion) {
				try {
					boolean actualizarVerificacion = true;
					System.out.print("Indique una letra: ");
					input = sc.nextLine().toLowerCase();
					if (input.length() > 1) {
						System.out.println("Debes introducir solo un carácter.");
						actualizarVerificacion = false;
					}

					letra = input.charAt(0);

					if (letra < 97 || letra > 122) {
						System.out.println("Debes introducir una letra válida.");
						actualizarVerificacion = false;
					}

					if (letrasUsadas.contains(letra)) {
						System.out.println("La letra " + letra + " ya ha sido usada.");
						actualizarVerificacion = false;
					}
					if (actualizarVerificacion) {
						seguirVerificacion = false;
					}
				} catch (StringIndexOutOfBoundsException e) {
					System.out.println("Debes de introducir algo");
				}
			}

			letrasUsadas.add(letra);

			boolean acertada = false;

			for (int i = 0; i < letras.length; i++) {
				char letraMayus = Character.toUpperCase(letra);
				if (letras[i] == letra) {
					letrasAcertadas[i] = letra;
					acertada = true;
				} else if (letras[i] == letraMayus) {
					letrasAcertadas[i] = letraMayus;
					acertada = true;
				}
			}

			if (!acertada) {
				limiteFallos--;
				System.out.println("Esa letra no está en la palabra");
				System.out.println("Fallos restantes: " + limiteFallos);
			}

			if (String.valueOf(letrasAcertadas).equalsIgnoreCase(String.valueOf(letras))) {
				System.out.println("¡Ganaste! La palabra era: " + palabraAleatoria.toString());
				partidaTerminada = true;
			}

			if (limiteFallos == 0) {
				System.out.println("Has perdido. La palabra era: " + palabraAleatoria.toString());
				partidaTerminada = true;
			}
		}
	}

	public static void ahorcado(int limiteFallos) {
		// Salida para mustrar el muñeco y mostrar visualmente cuantos intentos quedan
		switch (limiteFallos) {
		case 6:
			System.out.println("  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========");
			break;
		case 5:
			System.out.println("  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========");
			break;
		case 4:
			System.out.println("  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========");
			break;
		case 3:
			System.out.println("  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========");
			break;
		case 2:
			System.out.println("  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========");
			break;
		case 1:
			System.out.println("  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========");
			break;
		case 0:
			System.out.println("  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n=========");
			break;
		}
	}
}