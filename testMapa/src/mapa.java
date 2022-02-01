import java.util.HashMap;
public class mapa 
{
    public static void main (String[] args)
    {
        HashMap<String, Empleado> personal = new HashMap<>();

        personal.put("125", new Empleado("Juan"));
        personal.put("126", new Empleado("Ana"));
        personal.put("127", new Empleado("Antonio"));
        personal.put("128", new Empleado("Sandra"));
        System.out.println(personal);
        personal.remove("127");
        System.out.println(personal);
    }

}

class Empleado
{
    public Empleado(String n)
    {
        nombre = n;
        sueldo = 2000;
    }

    public String toString()
    {
        return "nombre =" + nombre +", sueldo = " + sueldo;
    }
    private String nombre;
    private double sueldo;
}