package poo;

public class Coche 
{
    private int ruedas;
    private int largo;
    private int ancho;
    private int motor;
    private int peso_plataforma;
    private String color;
    int peso_total;
    boolean asientos_cuero, climatizador;

    public Coche()
    {
        ruedas = 4;
        largo = 2000;
        ancho = 300;
        motor = 1600;
        peso_plataforma = 500;
    }
    public String dime_largo()
    {
        return ("El largo del coche es: " + largo + " de largo");
    }

    public void establece_color(String color_coche)
    {
        this.color = color_coche;
    }

    public String dime_color()
    {
        return "el color es: " + color;
    }
    

    public String dime_datos_generales()
    {
        return ("La plataforma del vehiculo tiene " + ruedas + " ruedas " + 
        ". Mide " + largo/1000 + " metros con un ancho de " + ancho + 
        " cm y un peso de la plataforma de " + peso_plataforma + " kg");
    }
    
}
