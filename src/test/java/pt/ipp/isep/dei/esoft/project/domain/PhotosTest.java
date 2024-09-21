package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class PhotosTest {

    @Test
    void getUrl_singleUrl() {
        Photos p = new Photos("https://example.com/image.jpg");
        assertEquals(Collections.singletonList("https://example.com/image.jpg"), p.getUrl());
    }

    @Test
    void getUrl_multipleUrls() {
        Photos p = new Photos(Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg"));
        assertEquals(Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg"), p.getUrl());
    }

    @Test
    void setUrl_singleUrl() {
        Photos p = new Photos("https://example.com/image.jpg");
        p.setUrl(Collections.singletonList("https://example.com/image2.jpg"));
        assertEquals(Collections.singletonList("https://example.com/image2.jpg"), p.getUrl());
    }

    @Test
    void setUrl_multipleUrls() {
        Photos p = new Photos("https://example.com/image.jpg");
        p.setUrl(Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg"));
        assertEquals(Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg"), p.getUrl());
    }

    @Test
    void testEquals() {
        Photos p1 = new Photos("https://example.com/image.jpg");
        Photos p2 = new Photos("https://example.com/image.jpg");
        Photos p3 = new Photos("https://example.com/image2.jpg");

        // For the same object
        assertEquals(p1, p1);
        // For equal objects
        assertEquals(p1, p2);
        // For different objects
        assertNotEquals(p1, p3);
    }

    @Test
    void testHashCode() {
        Photos p1 = new Photos("https://example.com/image.jpg");
        Photos p2 = new Photos("https://example.com/image.jpg");
        Photos p3 = new Photos("https://example.com/image2.jpg");

        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1.hashCode(), p3.hashCode());
    }

//    @Test
//    void addUrl() {
//        Photos p = new Photos("https://example.com/image1.jpg");
//        p.addUrl("https://example.com/image2.jpg");
//        assertEquals(Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg"), p.getUrl());
//    }
}