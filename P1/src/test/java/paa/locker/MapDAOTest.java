package paa.locker;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.Test;

import paa.locker.persistence.DAOException;
import paa.locker.persistence.Locker;
import paa.locker.persistence.LockerMapDAO;

public class MapDAOTest {


    @Test 
    public void testDelete(){
        Locker locker1 = new Locker();
        LockerMapDAO map = new LockerMapDAO();

        locker1.setCode(1L);
        map.create(locker1);
        assertNotNull(map.find(locker1.getCode()));
        map.delete(locker1);
        assertNull(map.find(locker1.getCode()));
    }
    @Test(expected = DAOException.class)
    public void testNotDelete(){
        Locker locker1 = new Locker();
        LockerMapDAO map = new LockerMapDAO();

        locker1.setCode(1L);
        map.delete(locker1);
    }

    @Test
    public void testFind(){
        Locker locker1 = new Locker();
        LockerMapDAO map = new LockerMapDAO();

        locker1.setCode(1L);
        map.create(locker1);
        assertSame(map.find(1L).getCode(), locker1.getCode());
        assertNull(map.find(2L));
    }

    @Test
    public void testCreate(){
        Locker locker1 = new Locker();
        LockerMapDAO map = new LockerMapDAO();

        locker1.setCode(1L);
        map.create(locker1);
        assertSame(map.find(1L).getCode(), locker1.getCode());
    }

    @Test (expected = DAOException.class)
    public void testNotCreate(){
        Locker locker1 = new Locker();
        Locker locker2 = new Locker();
        LockerMapDAO map = new LockerMapDAO();

        locker1.setCode(1L);
        locker2.setCode(1L);

        map.create(locker1);
        map.create(locker2);

    }

    @Test
    public void testUpdate(){
        Locker locker1 = new Locker();
        LockerMapDAO map = new LockerMapDAO();

        locker1.setCode(1L);
        locker1.setName("inicio");
        map.create(locker1);
        locker1.setName("fin");
        map.update(locker1);

        assertSame(map.find(1L).getName(), "fin");
    }

    @Test(expected = DAOException.class)
    public void testNotUpdate(){
        Locker locker1 = new Locker();
        LockerMapDAO map = new LockerMapDAO();
        
        locker1.setCode(1L);
        map.update(locker1);
    }

    @Test
    public void testFindAll(){
        Locker locker1 = new Locker();
        Locker locker2 = new Locker();
        Locker locker3 = new Locker();
        LockerMapDAO map = new LockerMapDAO();

        locker1.setCode(1L);
        locker2.setCode(2L);
        locker3.setCode(3L);

        map.create(locker1);
        map.create(locker2);
        map.create(locker3);

        List<Locker> lista = map.findAll();
        assertEquals(locker1.getCode(), lista.get(0).getCode());
        assertEquals(locker2.getCode(), lista.get(1).getCode());
        assertEquals(locker3.getCode(), lista.get(2).getCode());
    }

}