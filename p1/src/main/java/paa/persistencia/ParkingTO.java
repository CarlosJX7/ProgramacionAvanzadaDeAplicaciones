package paa.persistencia;

public class ParkingTO 
{
	//alt+shift+s --> crea todos los getter y setter a la vez
	private Long codigo;
	private String nombre; 
	private String direccion;
	private int plazas;
	private double longitud;
	private double latitud;
	
	public ParkingTO (Long codigo,String nombre, String direccion,
			int plazas, double longitud, double latitud) {
		this.codigo =  codigo;
		this.nombre = nombre; 
		this.direccion = direccion;
		this.plazas = plazas;
		this.longitud = longitud;
		this.latitud=latitud;
		
	}
	
	public ParkingTO () {
		
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public int getPlazas() {
		return plazas;
	}
	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	
	
	@Override
	public boolean equals (Object o) {
		boolean res = false; 
		if (o != null && o instanceof ParkingTO) {
			ParkingTO parking = (ParkingTO)o;
			res = parking.codigo.equals(codigo);
		}
		return res; 
	}
	
	@Override
	public int hashCode () {return codigo.hashCode();}
	
	
}
