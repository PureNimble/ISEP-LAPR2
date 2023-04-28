package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Photos {
    private String url;

    public Photos(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photos photos = (Photos) o;
        return url.equals(photos.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
