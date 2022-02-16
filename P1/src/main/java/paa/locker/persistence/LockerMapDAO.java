package paa.locker.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LockerMapDAO implements DAO<Locker, Long>{

    private Map<Long, Locker> mapa;

    public LockerMapDAO(){
        mapa = new HashMap<Long, Locker>();
    }

    @Override
    public Locker find(Long id) {
        return (mapa.get(id));
    }

    @Override
    public List<Locker> findAll() {
        List<Locker> lista = new ArrayList<Locker>();
        for (Map.Entry<Long, Locker> e: mapa.entrySet()) {
            lista.add(e.getValue());
        }
        return lista;
    }

    @Override
    public Locker create(Locker t) {
        mapa.put(t.getCode(), t);
        return t;
    }

    @Override
    public Locker update(Locker t) {
        if(!mapa.containsKey(t.getCode())){
            mapa.put(t.getCode(), t);
        }
        return t;
    }

    @Override
    public void delete(Locker t) {
        mapa.remove(t.getCode());        
    }


}