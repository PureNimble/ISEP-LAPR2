package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**

 The TypeOfBusiness class represents the type of business.
 */
public class TypeOfBusiness {

    /**

     The type of business.
     */
    private String typeOfBusiness;
    /**

     Creates an instance of TypeOfBusiness with the specified type of business.
     @param typeOfBusiness the type of business.
     */
    public TypeOfBusiness(String typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }

    /**
     *  Returns the string representation of the Type of Business.
     *
     * @return the format string about the type of business
     */
    public String toString(){
        return String.format(typeOfBusiness);
    }


    /**

     Returns the type of business.
     @return the type of business.
     */
    public String getTypeOfBusiness() {
        return typeOfBusiness;
    }
    /**

     Indicates whether some other object is "equal to" this one.
     @param o the reference object with which to compare.
     @return true if this object is the same as the o argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeOfBusiness that = (TypeOfBusiness) o;
        return typeOfBusiness.equals(that.typeOfBusiness);
    }
    /**

     Returns a hash code value for the object.
     @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(typeOfBusiness);
    }
    /**

     Returns a clone of this TypeOfBusiness instance.
     @return a clone of this TypeOfBusiness instance.
     */
    public TypeOfBusiness clone() {
        return new TypeOfBusiness(this.typeOfBusiness);
    }
}
