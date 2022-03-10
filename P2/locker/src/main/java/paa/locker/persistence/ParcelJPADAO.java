package paa.locker.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ParcelJPADAO extends JPADAO<Parcel, Long >{

	public ParcelJPADAO(EntityManager em, Class<Parcel> entityClass) {
		super(em, entityClass);

	}

	public List<Parcel> getParcelByWeightAndDate (Long lockerCode , double weight, int maxDaysInLocker){
				
		TypedQuery<Parcel> q = em.createQuery(
				   "SELECT p FROM Parcel p WHERE p.locker.code =:lockerCode AND p.weight=:weight", Parcel.class);
			   return q.setParameter("lockerCode", lockerCode)
					   .setParameter("weight", weight)
					   .getResultStream()
					   .filter(p -> p.getArrivalDate().isBefore(LocalDate.now().minusDays(maxDaysInLocker)))
					   .collect(Collectors.toList());
   }
   
   public List<Parcel> getParcelByWeight (Long lockerCode , double weight, int maxDaysInLocker){
		TypedQuery<Parcel> q = em.createQuery("SELECT p FROM Parcel p WHERE p.locker.code =:lockerCode AND p.weight=:weight", Parcel.class);
			   return q.setParameter("lockerCode", lockerCode).setParameter("weight", weight).getResultList();
	   
   }
   
   public List<Parcel> getParcelsByLockerID (Long lockerCode) {
		TypedQuery<Parcel> q = em.createQuery("SELECT p FROM Parcel p WHERE p.locker.code =:lockerCode", Parcel.class);
			   return q.setParameter("lockerCode", lockerCode).getResultList();
	   
   }
   
   public List<Parcel> getParcelsByLockerIDAndAddresse (Long lockerCode, int addressee) {
		TypedQuery<Parcel> q = em.createQuery("SELECT p FROM Parcel p WHERE p.locker.code =:lockerCode AND p.addressee =:addressee", Parcel.class);
			   return q.setParameter("lockerCode", lockerCode).setParameter("addressee", addressee).getResultList();
	   
   }
   
   public List<Parcel> getParcelsByAddresseeAndDate (Long lockerCode, int addressee , int maxDaysInLocker) {
		TypedQuery<Parcel> q = em.createQuery("SELECT p FROM Parcel p WHERE p.locker.code =:lockerCode AND p.addressee =:addressee", Parcel.class);
			   return q.setParameter("addressee", addressee).getResultList();
	   
   }
   
   
   public List<Parcel> getParcelsByAddressee (int addressee , int maxDaysInLocker) {
		TypedQuery<Parcel> q = em.createQuery(
				   "SELECT p FROM Parcel p WHERE p.addressee =:addressee", Parcel.class);
			   return q.setParameter("addressee", addressee).getResultStream()
					   .filter(p -> p.getArrivalDate().isAfter(LocalDate.now().minusDays(maxDaysInLocker+1)))
					   .collect(Collectors.toList());
   }

}
