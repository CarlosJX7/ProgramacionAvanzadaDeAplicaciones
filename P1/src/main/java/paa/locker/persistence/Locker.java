package paa.locker.persistence;

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
            final int prime = 31;
            int result = 1;
            result = prime * result + ((address == null) ? 0 : address.hashCode());
            result = prime * result + ((code == null) ? 0 : code.hashCode());
            result = prime * result + largeCompartments;
            long temp;
            temp = Double.doubleToLongBits(latitude);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(longitude);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            result = prime * result + smallCompartments;
            return result;
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
            if (address == null) {
                if (other.address != null)
                    return false;
            } else if (!address.equals(other.address))
                return false;
            if (code == null) {
                if (other.code != null)
                    return false;
            } else if (!code.equals(other.code))
                return false;
            if (largeCompartments != other.largeCompartments)
                return false;
            if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
                return false;
            if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
                return false;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            if (smallCompartments != other.smallCompartments)
                return false;
            return true;
        }

        
}
