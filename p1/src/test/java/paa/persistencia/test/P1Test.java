package paa.persistencia.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import paa.persistencia.DAOException;
import paa.persistencia.MapParkingDAO;
import paa.persistencia.ParkingTO;

public class P1Test {

	ParkingTO parking1 = new ParkingTO();
	ParkingTO parking2 = new ParkingTO();
	ParkingTO parking3 = new ParkingTO();

	/*@Before
	public void preparacion () {
		mapaParking = new MapParkingDAO(); 
	}*/

	//	TEST CREATE
	@Test
	public void testCreateOK() {
		
		MapParkingDAO mapaParking = new MapParkingDAO(); 
		parking1.setCodigo(1L);
		parking2.setCodigo(2L);

		//Comprobamos que el metodo create no devuelve null
		//Eso quiere decir que el elemento se añade correctamente
		assertNotNull(mapaParking.create(parking1));
		assertNotNull(mapaParking.create(parking2));		

	}
	
	@Test (expected = DAOException.class)
	public void testCreateNOK()throws DAOException {
		MapParkingDAO mapaParking = new MapParkingDAO(); 
		parking1.setCodigo(1L);
				
		assertNotNull(mapaParking.create(parking1));	
		//Añadimos un elemento repetido
		mapaParking.create(parking1);
		fail("Deberia saltar la excepcion DAOException");

	}
	
	@Test
	public void testCreate() {
	 try {
	
		 MapParkingDAO mapaParking = new MapParkingDAO(); 
		parking1.setCodigo(1L);
					
		assertNotNull(mapaParking.create(parking1));	
		//Añadimos un elemento repetido
		mapaParking.create(parking1);
		fail("Deberia saltar la excepcion DAOException");
	 
	 } catch (DAOException e) {
		 // funciona correctamente
	 }
	}

	
	//TEST UPDATE
	@Test
	public void testUpdate() {
 
		MapParkingDAO mapaParking = new MapParkingDAO(); 
		parking1.setCodigo(1L);
		parking1.setNombre("PARKING1");

		parking2.setCodigo(2L);
		parking2.setNombre("PARKING2");
		
		parking3.setCodigo(2L); //Asignamos el mismo codigo para que el valor asociado a la clave
								// del mapa se actualice por el objeto parking3
		parking3.setNombre("PARKING3");

		//Comprobamos que el metodo create no devuelve null
		//Eso quiere decir que el elemento se añade correctamente
		assertNotNull(mapaParking.create(parking1));
		assertNotNull(mapaParking.create(parking2));
		
		//Actualizamos el objeto añadido en el mapa bajo el nombre parking2
		assertNotNull (mapaParking.update(parking3));
		
		//Para asegurar que la actualizacion ha tenido efecto
		ParkingTO test = mapaParking.find(parking3.getCodigo());
		assertEquals("PARKING3", test.getNombre()); 
		
		//cOMPROBAMOS QUE EL METODO UPDATE LANZA UNA EXCEPCION SI LA CLAVE PASADA POR PARAMETRO NO EXISTE
		ParkingTO parking4 = new ParkingTO();
		try {
			parking4.setCodigo(4L);
			mapaParking.update(parking4);
			fail("Deberia saltar la excepcion DAOException");
		}catch(DAOException e) {
			
		}
		
	}
	
	@Test (expected = DAOException.class)
	public void testUpdateOKNOK2() {
 
		MapParkingDAO mapaParking = new MapParkingDAO(); 
		parking1.setCodigo(1L);
		parking1.setNombre("PARKING1");

		parking2.setCodigo(2L);
		parking2.setNombre("PARKING2");
		
		parking3.setCodigo(2L); //Asignamos el mismo codigo para que el valor asociado a la clave
								// del mapa se actualice por el objeto parking3
		parking3.setNombre("PARKING3");

		//Comprobamos que el metodo create no devuelve null
		//Eso quiere decir que el elemento se añade correctamente
		assertNotNull(mapaParking.create(parking1));
		assertNotNull(mapaParking.create(parking2));
		
		//Actualizamos el objeto añadido en el mapa bajo el nombre parking2
		assertNotNull (mapaParking.update(parking3));
		
		//Para asegurar que la actualizacion ha tenido efecto
		ParkingTO test = mapaParking.find(parking3.getCodigo());
		assertEquals("PARKING3", test.getNombre()); 
		
		//cOMPROBAMOS QUE EL METODO UPDATE LANZA UNA EXCEPCION SI LA CLAVE PASADA POR PARAMETRO NO EXISTE
		ParkingTO parking4 = new ParkingTO();
	
		parking4.setCodigo(4L);
		mapaParking.update(parking4);
		fail("Deberia saltar la excepcion DAOException");
		
	}
	
	//TEST DELETE:	public void delete(ParkingTO t) 
	@Test (expected = DAOException.class)
	public void testDelete() {
 
		MapParkingDAO mapaParking = new MapParkingDAO(); 
		parking1.setCodigo(1L);
		parking1.setNombre("PARKING1");

		parking2.setCodigo(2L);
		parking2.setNombre("PARKING2");
		
		parking3.setCodigo(3L); 
		parking3.setNombre("PARKING3");
		
		assertNotNull(mapaParking.create(parking1));
		assertNotNull(mapaParking.create(parking2));
	
		//Intento borrar un elemento cuya clave existe. 
		mapaParking.delete(parking2); //Se elimina del mapa correctamente
	
		//Para comporbar que se ha borrado correctamente: 
		ParkingTO test = mapaParking.find(parking2.getCodigo());
		assertNull(test); //Lo correcto es que devuelva Null
		
		//Intentamos borrar una elemento cuya clave no existe en el mapa
		mapaParking.delete(parking3);
		fail("Deberia saltar la excepcion DAOException");


	}
	
	
	//Test metodo find : public ParkingTO find(Long id) {

	@Test 
	public void testFind() {
		
		MapParkingDAO mapaParking = new MapParkingDAO(); 
		parking1.setCodigo(1L);
		parking1.setNombre("PARKING1");

		parking2.setCodigo(2L);
		parking2.setNombre("PARKING2");
		
		parking3.setCodigo(3L); 
		parking3.setNombre("PARKING3");
		
		assertNotNull(mapaParking.create(parking1));
		assertNotNull(mapaParking.create(parking2));
		
		//Compueba si existe el parking 2 en el mapa (con el id 2L), si existe devuelve el objeto asociado a su clave. 
		assertNotNull(mapaParking.find(parking2.getCodigo())); 
		//Buscamos un objeto asociado a una clave que no existe en el mapa
		assertNull(mapaParking.find(parking3.getCodigo())); 

	}
	
	@Test 
	public void testFindAll() {
		
		MapParkingDAO mapaParking = new MapParkingDAO(); 
		parking1.setCodigo(1L);
		parking1.setNombre("PARKING1");

		parking2.setCodigo(2L);
		parking2.setNombre("PARKING2");
		
		parking3.setCodigo(3L); 
		parking3.setNombre("PARKING3");
		
		assertNotNull(mapaParking.create(parking1));
		assertNotNull(mapaParking.create(parking2));
		assertNotNull(mapaParking.create(parking3));
		
		List<ParkingTO> listado = mapaParking.findAll(); 
		assertNotNull(listado); 
		assertEquals(listado.size(), 3); 
	}

}
