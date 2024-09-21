package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeOfBusinessTest {

    @Test
    void getTypeOfBusiness() {
        TypeOfBusiness type1 = new TypeOfBusiness("Sale");
        assertEquals("Sale", type1.getTypeOfBusiness());

        TypeOfBusiness type2 = new TypeOfBusiness("Rent");
        assertEquals("Rent", type2.getTypeOfBusiness());
    }

    //for the same object
    @Test
    void testEquals(){
        TypeOfBusiness type1 = new TypeOfBusiness("Sale");

        assertEquals(type1,type1);
    }

    //for equal objects
    @Test
    void testEquals2() {
        TypeOfBusiness type1 = new TypeOfBusiness("Sale");
        TypeOfBusiness type2 = new TypeOfBusiness("Sale");

        assertEquals(type1, type2);
    }

    //for different objects
    @Test
    void testEquals3(){
        TypeOfBusiness type1 = new TypeOfBusiness("Sale");
        TypeOfBusiness type2 = new TypeOfBusiness("Rent");

        assertNotEquals(type1, type2);

    }

    @Test
    void testHashCode() {
        TypeOfBusiness type1 = new TypeOfBusiness("Sale");
        TypeOfBusiness type2 = new TypeOfBusiness("Sale");
        TypeOfBusiness type3 = new TypeOfBusiness("Rent");

        assertEquals(type1.hashCode(), type2.hashCode());
        assertNotEquals(type1.hashCode(), type3.hashCode());
    }

    @Test
    void testClone() {
        TypeOfBusiness type1 = new TypeOfBusiness("Sale");
        TypeOfBusiness type2 = type1.clone();

        assertEquals(type1.getTypeOfBusiness(), type2.getTypeOfBusiness());
        assertNotSame(type1, type2);
    }
}