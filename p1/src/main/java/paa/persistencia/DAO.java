package paa.persistencia;

import java.util.List;

public interface DAO <T,K>{
	T find (K id); // id es la clave única
	List<T> findAll ();
	T create (T t);
	T update(T t);
	void delete(T t);
}
