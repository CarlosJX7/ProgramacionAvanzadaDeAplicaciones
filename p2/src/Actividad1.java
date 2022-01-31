import java.util.Scanner;
import java.io.*;

public class Actividad1 {

	public static void main(String[] args) throws FileNotFoundException {
		
		String nFichero;
		int umbral;
		Scanner sc = new Scanner (System.in);
		
		System.out.println("Escriba el valor del umbral: ");
		
		umbral = Integer.parseInt(sc.nextLine());
		
		System.out.println("Escriba el nombre del archivo: ");
		
		nFichero = sc.nextLine();
		
		FileReader fr = new FileReader (nFichero); //nombre relativo, nombre completo contiene carpeta etc
		
		Scanner sf = new Scanner(fr); //Scanner para leer del fichero
		
		//Proceso leer elemento a elemento con el next
		
		int numeroLeido;
		int numMOIU = 0;
		int contador = 0;
		
		while(sf.hasNext()) { //true si alcanza el final de fichero
			
			numeroLeido = Integer.parseInt(sf.next()); //next devuelve el elemento actual del recorrido
			if (numeroLeido >= umbral)
				numMOIU++;
			contador++;
			
		}
		
		System.out.println("El umbral es el "+umbral+".");
		System.out.println("La secuencia tiene "+contador+" numeros.");
		System.out.println("Ha "+numMOIU+" mayores o iguales que el umbral.");
		
		//cierre de ficheros
		sc.close();
		sf.close();

	}

}
