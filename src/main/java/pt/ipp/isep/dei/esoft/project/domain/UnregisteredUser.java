package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class UnregisteredUser {

    private int cookies;

    private static final int COOKIES_POR_OMISSAO = 0;

    public UnregisteredUser(int cookies) {
        this.cookies = cookies;
    }

    public UnregisteredUser() {
        this.cookies = COOKIES_POR_OMISSAO;
    }

    public int getCookies() {
        return cookies;
    }

    public void setCookies(int cookies) {
        this.cookies = cookies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnregisteredUser)) return false;
        UnregisteredUser that = (UnregisteredUser) o;
        return cookies == that.cookies;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cookies);
    }

    public UnregisteredUser clone() {
        return new UnregisteredUser(this.cookies);
    }
}
