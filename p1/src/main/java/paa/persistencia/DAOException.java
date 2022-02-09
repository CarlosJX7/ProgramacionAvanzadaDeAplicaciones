package paa.persistencia;

public class DAOException extends RuntimeException {

	private static final long serialVersionUID = -1182843101462310624L;

	public DAOException () {
		super();
	}
	
	public DAOException (String mensaje) {
		super(mensaje);
	}
}
