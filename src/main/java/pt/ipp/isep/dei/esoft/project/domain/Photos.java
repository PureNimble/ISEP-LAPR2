package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * The `Photos` class represents a photo with a URL.
 */
public class Photos implements Serializable {
    /**
     * The URL of the photo.
     */
    private List<String> url;

    /**
     * Constructs a new `Photos` object with the specified URL.
     *
     * @param url the URL of the photo
     */
    public Photos(List<String> url) {
        this.url = url;
    }

    /**
     * Instantiates a new Photos.
     *
     * @param url the url
     */
    public Photos(String url) {
        this.url = List.of(url);
    }

    /**
     * Returns the URL of the photo.
     *
     * @return the URL of the photo
     */
    public List<String> getUrl() {
        return url;
    }

    /**
     * Sets the URL of the photo.
     *
     * @param url the URL of the photo
     */
    public void setUrl(List<String> url) {
        this.url = url;
    }

    /**
     * Add url.
     *
     * @param url the url
     */
    public void addUrl(String url){
        this.url.add(url);
    }

    /**
     * Compares this `Photos` object to another object to see if they are equal.
     *
     * @param o the other object to compare to
     * @return `true` if the objects are equal, `false` otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photos photos = (Photos) o;
        return url.equals(photos.url);
    }

    /**
     * Returns the hash code of this `Photos` object.
     *
     * @return the hash code of this `Photos` object
     */
    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}