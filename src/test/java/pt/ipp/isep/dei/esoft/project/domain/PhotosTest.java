package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhotosTest {

    @Test
    void getUrl() {
        Photos p = new Photos("https://example.com/imagee.jpg");
        assertEquals("https://example.com/imagee.jpg", p.getUrl());
    }

    @Test
    void setUrl() {
        Photos p = new Photos("https://example.com/imagee.jpg");
        p.setUrl("https://example.com/imagee2.jpg");
        assertEquals("https://example.com/imagee2.jpg", p.getUrl());
    }

    @Test
    void testEquals() {
        Photos p1 = new Photos("https://example.com/image.jpg");
        Photos p2 = new Photos("https://example.com/image.jpg");
        Photos p3 = new Photos("https://example.com/image2.jpg");

        //for the same object
        assertEquals(p1,p1);
        //for equal objects
        assertEquals(p1, p2);
        //for different objects
        assertNotEquals(p1, p3);
    }

    @Test
    void testHashCode() {
        Photos p1 = new Photos("https://example.com/imagee.jpg");
        Photos p2 = new Photos("https://example.com/imagee.jpg");
        Photos p3 = new Photos("https://example.com/imagee2.jpg");

        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1.hashCode(), p3.hashCode());
    }
}