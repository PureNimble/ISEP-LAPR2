package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class City {
        private String city;

        public City(String city) {
            this.city = city;
        }

        public String getCity() {
            return city;
        }

        public String toString(){
            return String.format(city);
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            pt.ipp.isep.dei.esoft.project.domain.City city1 = (pt.ipp.isep.dei.esoft.project.domain.City) o;
            return city.equals(city1.city);
        }

        @Override
        public int hashCode() {
            return Objects.hash(city);
        }

        public pt.ipp.isep.dei.esoft.project.domain.City clone(){

            return new pt.ipp.isep.dei.esoft.project.domain.City(this.city);

        }

}
