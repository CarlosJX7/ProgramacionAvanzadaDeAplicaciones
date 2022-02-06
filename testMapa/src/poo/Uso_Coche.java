package poo;

public class Uso_Coche
{
    public static void main (String[] args)
    {
        Coche miCoche = new Coche();
        miCoche.establece_color("verde");
        System.out.println(miCoche.dime_color());
        System.out.println(miCoche.dime_datos_generales());
    }
}