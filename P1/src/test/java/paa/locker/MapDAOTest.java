package paa.locker;

import paa.locker.persistence.Locker;
import paa.locker.persistence.LockerMapDAO;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class MapDAOTest {
    @Test
    public void testCreate(){
        Locker Locker1 = new Locker();
        LockerMapDAO mapaLocker = new LockerMapDAO();
        Locker1.setCode((long) 1);
        assertNotNull(mapaLocker.create(Locker1));
        mapaLocker.create(Locker1);
    }
}
