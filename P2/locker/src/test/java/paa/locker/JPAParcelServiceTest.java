package paa.locker;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import paa.locker.business.JPAParcelService;
import paa.locker.persistence.Locker;

public class JPAParcelServiceTest {
	
	@Test
	public void createLockerTest() {
		JPAParcelService s = new JPAParcelService();
		Locker l = s.createLocker("prueba","direccion", 0, 0, 90, 10);
		Locker l2 = s.createLocker("prueba2", "dir2", 1, 1, 91, 11);
		assertTrue(l != null);
		assertTrue(l.getCode() != null);
		Locker l1 = s.findLocker(l.getCode());
		assertTrue(l1 != null);
		assertTrue(l1.equals(l));
		assertTrue(l1.getName().equals("prueba"));
		assertTrue(l1.getAddress().equals("direccion"));
		assertTrue(l1.getLargeCompartments() == 90);
		assertTrue(l1.getSmallCompartments() == 10);
		assertTrue(l1.getLatitude() == 0);
		assertTrue(l1.getLongitude() == 0);
		
	}
	@Test
	public void findAllLockerTest() {
		JPAParcelService s = new JPAParcelService();
		
		Locker l1 = s.createLocker("Test 1", "Direccion1", 0, 0, 200, 5);
		Locker l2 = s.createLocker("Test 2", "Direccion2", 50, 50, 50, 100);
		Locker l3 = s.createLocker("Test 3", "Direccion3", 0, -40, 25, 40);
		Locker l4 = s.createLocker("Test 4", "Direccion4", -40, 0, 12, 5);
		Locker l5 = s.createLocker("Test 5", "Direccion5", 0, 20, 10, 10);
		
		List<Locker> listaLocker = s.findAllLockers();
		assertSame(listaLocker.size(), 5);
		
	}

	@Test
	public void availableCompartmentsTest() {
		JPAParcelService service = new JPAParcelService();

		service.createLocker("Test", "Direccion", 0, 0, 5, 5);
		

		List<Locker> listaLocker =  service.findAllLockers();
		Long latestLockerCode = listaLocker.get(0).getCode();
		service.deliverParcel(latestLockerCode, 1, 1,LocalDate.now().minusDays(10));
		service.deliverParcel(latestLockerCode, 2, 1,LocalDate.now().plusDays(1));
		service.deliverParcel(latestLockerCode, 3, 10,LocalDate.now().minusDays(3));
		service.deliverParcel(latestLockerCode, 4, 10,LocalDate.now().plusDays(2));	

		 assertSame(service.availableCompartments(latestLockerCode, LocalDate.now().plusDays(1), 10), 3);
	}

	/**
	 * Para hacer el JList al metodo solo le podemos pasar una lista o un array
	 * Jlist <Locker> l = new JList();
	 * List<Locker> datos = ...Service.getAll();
	 * l.setListData(new Vector <Locker> (datos)) o l.setListData(datos.toArray(new Locker[0]));
	 */

}
