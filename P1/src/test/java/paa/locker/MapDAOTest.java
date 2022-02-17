package paa.locker;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

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

        }
        @Test(expected = DAOException.class)
        public void testNotDelete(){
            Locker locker1 = new Locker();
            LockerMapDAO map = new LockerMapDAO();
            map.delete(locker1);
        }

        @Test
        public void testFind(){
            Locker locker1 = new Locker();
            LockerMapDAO map = new LockerMapDAO();
            locker1.setCode(1L);
            map.create(locker1);
            assertNotNull(map.find(1L));
            assertNull(map.find(2L));
        }

        @Test //(expected = DAOException.class)
        public void testCreate(){
            Locker locker1 = new Locker();
            LockerMapDAO map = new LockerMapDAO();

            locker1.setCode(1L);
            map.create(locker1);
            assertSame(map.find(1L), locker1);
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

}