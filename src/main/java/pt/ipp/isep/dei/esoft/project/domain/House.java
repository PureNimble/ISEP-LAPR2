package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class House extends Residence {
    private String basement;

    private String inhabitableLoft;

    private String sunExposure;

    public House(int area,int distanceFromCityCenter,int numberOfBedrooms, int numberOfBathrooms, int parkingSpaces, String availableEquipment,String basement, String inhabitableLoft, String sunExposure) {
        super(area,distanceFromCityCenter,numberOfBedrooms,numberOfBathrooms,parkingSpaces,availableEquipment);
        this.basement = basement;
        this.inhabitableLoft = inhabitableLoft;
        this.sunExposure = sunExposure;
    }


    public String toString(){
        return String.format(super.toString()+
                "Basement: %s\n" +
                "Inhabitable Loft: %s\n" +
                "Sun Exposure: %s\n",basement,inhabitableLoft,sunExposure);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return basement.equals(house.basement) && inhabitableLoft.equals(house.inhabitableLoft) && sunExposure.equals(house.sunExposure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basement, inhabitableLoft, sunExposure);
    }
}
