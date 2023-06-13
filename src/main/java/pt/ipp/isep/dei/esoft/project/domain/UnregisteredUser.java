package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * The UnregisteredUser class represents an unregistered user of a website. It holds information about the number
 * of cookies this user has.
 */
public class UnregisteredUser {
    /**
     * Represents the number of cookies.
     * The value of the cookies can be modified by the program.
     */
    private int cookies;
    /**
     * Default number of cookies when not explicitly specified.
     */
    private static final int COOKIES_POR_OMISSAO = 0;

    /**
     * Constructs an UnregisteredUser object with a specified number of cookies.
     *
     * @param cookies the number of cookies the user has
     */
    public UnregisteredUser(int cookies) {
        this.cookies = cookies;
    }

    /**
     * Constructs an UnregisteredUser object with a default number of cookies.
     */
    public UnregisteredUser() {
        this.cookies = COOKIES_POR_OMISSAO;
    }

    /**
     * Returns the number of cookies the user has.
     *
     * @return the number of cookies
     */
    public int getCookies() {
        return cookies;
    }

    /**
     * Sets the number of cookies the user has.
     *
     * @param cookies the new number of cookies
     */
    public void setCookies(int cookies) {
        this.cookies = cookies;
    }
    /**

     Checks if this UnregisteredUser object is equal to another object.
     @param o the object to compare to
     @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnregisteredUser)) return false;
        UnregisteredUser that = (UnregisteredUser) o;
        return cookies == that.cookies;
    }
    /**

     Returns a hash code for this UnregisteredUser object.
     @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(cookies);
    }
    /**

     Creates and returns a new UnregisteredUser object that is a copy of this object.
     @return a new UnregisteredUser object that is a copy of this object
     */
    public UnregisteredUser clone() {
        return new UnregisteredUser(this.cookies);
    }
}