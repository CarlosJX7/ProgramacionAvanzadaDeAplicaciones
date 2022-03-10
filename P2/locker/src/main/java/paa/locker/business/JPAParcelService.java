package paa.locker.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import paa.locker.persistence.DAOException;
import paa.locker.persistence.Locker;
import paa.locker.persistence.LockerJPADAO;
import paa.locker.persistence.Parcel;
import paa.locker.persistence.ParcelJPADAO;

public class JPAParcelService implements ParcelService {
	private static final String PERSISTENCE_UNIT_NAME = "paa";
	
	private static final double MAX_LONGITUD = 180.0;
	private static final double MIN_LONGITUD = -180.0;
	private static final double MAX_LATITUD = 90.0;
	private static final double MIN_LATITUD = -90.0;
	
	private EntityManagerFactory factory;

	private Locker locker;
	
	public JPAParcelService() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	@Override
	public Locker createLocker(String name, String address, double longitude, double latitude, int largeCompartments,
			int smallCompartments) {
		if (name == null || address == null || largeCompartments < 0 || smallCompartments < 0 || longitude < MIN_LONGITUD
				|| longitude > MAX_LONGITUD|| latitude < MIN_LATITUD || latitude > MAX_LATITUD) {
			throw new ParcelServiceException("Los datos son incorrectos");
			
		}
		
		Locker locker = null;
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		LockerJPADAO dao;
		List<Locker> LockerList = findAllLockers();
		boolean existe = false;
		
		for (Locker l :LockerList){
			if(l.getName().equals(name)){
				locker = l;
				existe = true;
			}
		}
		
		if(!existe) {
			locker = new Locker(null, name, address, longitude, latitude, largeCompartments, smallCompartments);
			
			try {
				et.begin();
				dao = new LockerJPADAO(em,Locker.class);
				locker = dao.create(locker);
				et.commit();
			}catch(DAOException a){
				if (et.isActive()) {
					et.rollback();
				}
			}catch(Exception b) {
				if (et.isActive()) {
					et.rollback();
				}
			}finally {
				em.close();
			}
		}
		return locker;
	}

	@Override
	public Locker findLocker(Long lockerCode) {
		EntityManager em = factory.createEntityManager();
		LockerJPADAO dao = new LockerJPADAO (em, Locker.class);//Para conectarnos a la bdd
		Locker locker = dao.find(lockerCode);//Devuelve null si no encuentra el elemento
		em.close();
		return locker;
	}

	@Override
	public List<Locker> findAllLockers() {
		EntityManager em = factory.createEntityManager();
		LockerJPADAO dao = new LockerJPADAO(em, Locker.class);
		
		List<Locker> listaLockers = dao.findAll();
		
		em.close();
		
		return listaLockers;
	}

	/**
	 * Devuelve el numero de compartimenos libres en el armario y fecha indicados donde se podria 
	 * alojar un paquete del peso indicado
	 * 1- Un paquete grande/pequeño en compartimento grande/pequeño
	 * 2- Peso positivo y menor que largeMaxWeight. ParcelServiceException
	 * 3- Un paquete solo puede estar maxDaysInLocker dias (entrega incluido). Pasado ese tiempo 
	 * 	 caduca y podra ser utilizado por otro envio
	 * 4- El Locker tiene que existir. ParcelServiceException
	 * */
	@Override
	public int availableCompartments(Long lockerCode, LocalDate date, float parcelWeight) {

		if (parcelWeight<0 || parcelWeight>LargeMaxWeight) {
			throw new ParcelServiceException ("Error en el tamaño del paquete");
		}

		locker = findLocker(lockerCode);
		if (locker ==  null) {
			throw new ParcelServiceException ("Armario no encontrado");
		}
		EntityManager em = factory.createEntityManager();
		ParcelJPADAO parcel = new ParcelJPADAO (em, Parcel.class);
		int disponibilidad =0;

		List<Parcel> listaParcelbyLocker = parcel.getParcelsByLockerID (lockerCode); 
		List<Parcel> parcelasPequeñas = new ArrayList<Parcel>();
		List<Parcel> parcelasGrandes = new ArrayList<Parcel>();

		for (Parcel p: listaParcelbyLocker) {
			if (p.getWeight()<= SmallMaxWeight &&
					p.getArrivalDate().isAfter(LocalDate.now().minusDays(MaxDaysInLocker+1))) {
				parcelasPequeñas.add(p);

			}
			if (p.getWeight()> SmallMaxWeight
					&& p.getArrivalDate().isAfter(LocalDate.now().minusDays(MaxDaysInLocker+1))){
				parcelasGrandes.add(p);

			}
		}
		
		if (parcelWeight<= SmallMaxWeight) {
			disponibilidad = locker.getSmallCompartments()-parcelasPequeñas.size();//cajones totales - reservados
		}else {
			disponibilidad = locker.getSmallCompartments()-parcelasGrandes.size();//cajones totales - reservados
		}
		
		return disponibilidad;
	}

	@Override
	public Parcel deliverParcel(Long lockerCode, int addressee, float weight, LocalDate arrivalDate) {
		
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction(); 		
		ParcelJPADAO dao = new ParcelJPADAO (em, Parcel.class );

		Locker locker = findLocker(lockerCode);
		if (locker == null) {
			throw new ParcelServiceException ("No se ha encontrado el locker");
		}

		int disponibilidad =  availableCompartments(lockerCode, arrivalDate, weight);
		if (disponibilidad == 0) {
			throw new ParcelServiceException ("No hay disponibilidad en el armario");
		}
		
		List<Parcel> parcelUserReservedInLocker = dao.getParcelsByAddressee (addressee, MaxDaysInLocker) ;
		if (parcelUserReservedInLocker!=null) {
			if (parcelUserReservedInLocker.size() >= MaxParcelsInLocker) {
				throw new ParcelServiceException ("Numero de reservas superado");
			}
		}
		
		List<Parcel> parcelsList = dao.getParcelsByAddressee (addressee,  MaxDaysInLocker);
		if (parcelsList!=null) {
			if(	parcelsList.size()>= MaxParcelsAnywhere) {
				throw new ParcelServiceException ("Numero de parcels superado");
			}
		}
		Parcel parcel = new Parcel(null, addressee, weight, arrivalDate, locker);
		try {
			et.begin();
			parcel=dao.create(parcel);
			et.commit();
					
		}catch (DAOException e) {
			if (et.isActive()) {
				et.rollback();
			}
		}catch (Exception e) {
			if (et.isActive()) {
				et.rollback();
			}
		}finally{
			em.close();
		}
		
		return parcel;
	}

	@Override
	public void retrieveParcel(Long parcelCode) {
		// TODO Auto-generated method stub
		
	}

}
