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

    /*
    Devuelve el elemento con el codigo dado o null si no existe
    */
    @Override
    public Locker find(Long id) {
        return mapa.get(id);
    }

    /*
    Devuelve una lista con todos los elementos almacenados.
    */
    @Override
    public List<Locker> findAll() {
        //List<Locker> lista = new ArrayList<Locker>(mapa.values());
        return new ArrayList<Locker>(mapa.values());
    }

    /*
    Añade un nuevo elemento. Si el elemento ya existe, lanzara la excepcion
    DAOException. Devuelve el elemento creado.
    */
    @Override
    public Locker create(Locker t) {
        
        if(!mapa.containsKey(t.getCode())){
            mapa.put(t.getCode(), t);
            return t;  
        }else{
            throw new DAOException("El elemento ya existe");
        }
    }

    /*
    Actualiza un elemento de los almacenados. Si el elemento no existe lanzara 
    la excepcion DAOException. Devuelve el elemento actualizado.
    */
    @Override
    public Locker update(Locker t) {
        if(mapa.containsKey(t.getCode())){
            mapa.put(t.getCode(), t);
            return t;
        }else{
            throw new DAOException("El elemento no existe");
        }
        
    }

    /*
    Borra el elemento indicado de los elementos almacenados. Si el elemento no existe
    lanzara la excepcion DAOException.
    */
    @Override
    public void delete(Locker t) {
        if(mapa.containsKey(t.getCode())){
            mapa.remove(t.getCode());    
        }else{
            throw new DAOException("El elemento no existe");
        }
    
    }

}