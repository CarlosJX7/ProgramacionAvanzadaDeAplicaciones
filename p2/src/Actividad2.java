import java.util.*;
import java.io.*;

public class Actividad2 {

	public static void main(String[] args) throws IOException {

		int numero;
		Scanner sc = new Scanner (System.in);
		
		
		FileWriter fichero = new FileWriter ("C:/Users/Carlos/eclipse-workspace/p2/salida.txt"); 
		PrintWriter modificador = new PrintWriter(fichero);
		
		System.out.println("Introducir el numero a calcular");
		
		numero = Integer.parseInt(sc.nextLine());
		
		for (int i= 3; i <= numero; i++) {
			
			if(esPrimo(i) == true)
				modificador.println(i);
			
		}
		
		sc.close();
		modificador.close();
		
	}
	
	private static boolean esPrimo (int numero){
		int contador;
		boolean esNumeroPrimo;
		
		esNumeroPrimo = ( numero % 2 ) != 0;
		
		if (numero == 2)
			esNumeroPrimo = true;
	
		contador = 3;
		while ( esNumeroPrimo && contador < numero) {
			esNumeroPrimo = ( numero % contador ) != 0;
			contador = contador + 2;
		}
		return esNumeroPrimo;
	}

}
