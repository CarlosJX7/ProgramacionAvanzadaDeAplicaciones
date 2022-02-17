package paa.locker.persistence;

import java.util.Objects;

public class Locker {
    // Complete siguiendo las instrucciones del enunciado
        private Long code;
        private String name;
        private String address;
        private int largeCompartments;
        private int smallCompartments;
        private double longitude;
        private double latitude;
        public Locker() {
 
        }
        // Métodos getters/setters a completar
        // Métodos equals/hashCode a completar
        public Long getCode() {
            return code;
        }
        public double getLatitude() {
            return latitude;
        }
        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }
        public double getLongitude() {
            return longitude;
        }
        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
        public int getSmallCompartments() {
            return smallCompartments;
        }
        public void setSmallCompartments(int smallCompartments) {
            this.smallCompartments = smallCompartments;
        }
        public int getLargeCompartments() {
            return largeCompartments;
        }
        public void setLargeCompartments(int largeCompartments) {
            this.largeCompartments = largeCompartments;
        }
        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setCode(Long code) {
            this.code = code;
        }
		@Override
		public int hashCode() {
			return Objects.hash(code);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Locker other = (Locker) obj;
			return Objects.equals(code, other.code);
		}

        
        
}
