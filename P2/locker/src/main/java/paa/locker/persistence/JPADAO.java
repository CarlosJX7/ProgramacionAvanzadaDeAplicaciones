package paa.locker.persistence;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
//import javax.persistence.TypedQuery;

import paa.locker.business.ParcelServiceException;

public class JPADAO<T, K> implements DAO<T, K> {
    protected EntityManager em;
    protected Class<T> clazz;

    public JPADAO(EntityManager em, Class<T> entityClass) {
        this.clazz = entityClass;
        this.em = em;
    }

    @Override
    public T find(K id) {
    	return em.find(clazz, id);
    }

    @Override
    public T create(T t) {
        try {
        	em.persist(t);//hace una entidad persistente y gestionada
        	em.flush();//sincroniza el contexto de persistencia con la base de datos
        	em.refresh(t);//actualiza el estado de la entidad con los valores
        	return t;
        }catch(ParcelServiceException ex) {
	    	throw new ParcelServiceException("La entidad ya existe", ex);
	    }
    }

    @Override
    public T update(T t) {
        return (T) em.merge(t);//incorpora una entidad al contexto de persistencia
    }//haciendola gestionada. Devuelve una referencia de la entidad gestionada

    @Override
    public void delete(T t) {
        t = em.merge(t);//incorpora una entidad al contexto de persistencia
        em.remove(t);//elimina una entidad
        em.flush();//sincroniza el contexto de persistencia con la base de datos subyacente
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<T> findAll() {
        // Complete este método, que debe listar todos los objetos de la clase T
        // Necesitará hacer consultas a la base datos mediante una TypedQuery, bien
        // empleando una sentencia JPQL o una CriteriaQuery
    	List<T> lista = null;
    	Query q = em.createQuery("select t from " + clazz.getName()+ " t");
    	lista = q.getResultList();
    	return lista;
    }
}
