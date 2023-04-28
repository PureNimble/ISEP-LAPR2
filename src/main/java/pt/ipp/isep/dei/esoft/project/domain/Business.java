package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Business {
    private double price;

    public Business(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String toString(){
        return String.format("%s",price);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Business business = (Business) o;
        return price == business.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
