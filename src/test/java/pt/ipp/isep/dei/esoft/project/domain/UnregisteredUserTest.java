package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnregisteredUserTest {

    //Test Constructor with cookies
    @Test
    public void testConstructor() {
        UnregisteredUser user = new UnregisteredUser(10);
        assertEquals(10, user.getCookies());
    }

    //Test Constructor with default cookies
    @Test
    public void testConstructorWithDefaultCookies() {
        UnregisteredUser user = new UnregisteredUser();
        assertEquals(0, user.getCookies());
    }

    @Test
    void getCookies() {
        UnregisteredUser user1 = new UnregisteredUser(10);
        assertEquals(10, user1.getCookies());

        UnregisteredUser user2 = new UnregisteredUser();
        assertEquals(0, user2.getCookies());

        UnregisteredUser user3 = new UnregisteredUser(-5);
        assertEquals(-5, user3.getCookies());
    }

    @Test
    void setCookies() {
        UnregisteredUser user = new UnregisteredUser();
        user.setCookies(5);
        assertEquals(5, user.getCookies());
    }

    //for the same object
    @Test
    void testEquals() {
        UnregisteredUser user = new UnregisteredUser(10);
        assertEquals(user, user);
    }

    //for equal objects
    @Test
    void testEquals2(){
        UnregisteredUser user1 = new UnregisteredUser(10);
        UnregisteredUser user2 = new UnregisteredUser(10);
        assertEquals(user1, user2);
    }

    //for diffrent objects
    @Test
    void testEquals3(){
        UnregisteredUser user1 = new UnregisteredUser(10);
        UnregisteredUser user2 = new UnregisteredUser(5);
        assertNotEquals(user1, user2);
    }

    @Test
    void testHashCode() {
        UnregisteredUser user1 = new UnregisteredUser(10);
        UnregisteredUser user2 = new UnregisteredUser(10);
        assertEquals(user1.hashCode(), user2.hashCode());

        UnregisteredUser user3 = new UnregisteredUser(5);
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }

    @Test
    void testClone() {
        UnregisteredUser user1 = new UnregisteredUser(10);
        UnregisteredUser user2 = user1.clone();
        assertTrue(user1 != user2);
        assertEquals(user1.getCookies(), user2.getCookies());
    }
}