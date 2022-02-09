package paa.persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapParkingDAO implements DAO<ParkingTO, Long>{

	private Map<Long, ParkingTO> mapa; 
	
	public MapParkingDAO () {
		mapa  = new HashMap<Long, ParkingTO>();
	}
	@Override
	public ParkingTO find(Long id) {
		return mapa.get(id);
	}

	@Override
	public List<ParkingTO> findAll() {
		List<ParkingTO> resultado = new ArrayList<ParkingTO>(); 
		for(Map.Entry<Long, ParkingTO> entry : mapa.entrySet()) {
			resultado.add(entry.getValue()); 
		}
		return resultado;
	}

	@Override
	public ParkingTO create(ParkingTO t) {

		//COmprobamos si la clave ya existe en el mapa
		if (mapa.containsKey(t.getCodigo())) {
			throw new DAOException("La clave ya existe en el mapa.");
		}
		mapa.put(t.getCodigo(), t);
		return t;
	}

	@Override
	public ParkingTO update(ParkingTO t) {

		if (!mapa.containsKey(t.getCodigo())) {
			throw new DAOException("La clave no existe en el mapa.");
		}
		mapa.put(t.getCodigo(), t);
		return t;
	}

	@Override
	public void delete(ParkingTO t) {
		if (!mapa.containsKey(t.getCodigo())) {
			throw new DAOException("Elemento no encontrado. ");
		}
		mapa.remove(t.getCodigo()); 		
	}

	

}
