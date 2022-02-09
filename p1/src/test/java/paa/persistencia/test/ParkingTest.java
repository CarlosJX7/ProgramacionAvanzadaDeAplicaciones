package paa.persistencia.test;

import org.junit.Test;


import paa.persistencia.ParkingTO;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//Añadido
import static org.junit.Assert.assertNotNull;

public class ParkingTest {

	@Test
	public void compruebaEquals() {
		ParkingTO parking1 = new ParkingTO();
		ParkingTO parking2 = new ParkingTO();
		parking1.setCodigo(1L);
		parking2.setCodigo(1L);
		
		assertTrue(parking1.equals(parking2));
		parking2.setCodigo(2L);
		assertFalse(parking1.equals(parking2));
		
		assertEquals(parking1,parking2);//NO LA PIDEN EN LA PRACTICA
		
	}
	
}
