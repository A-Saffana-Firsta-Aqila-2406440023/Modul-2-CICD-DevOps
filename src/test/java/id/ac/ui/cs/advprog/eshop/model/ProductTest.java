package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }

    @Test
    void testGetProductId() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.product.getProductId());
    }

    @Test
    void testGetProductIdInvalid() {
        assertNotEquals("wrong-id", this.product.getProductId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Sampo Cap Bambang", this.product.getProductName());
    }

    @Test
    void testGetProductNameInvalid() {
        assertNotEquals("Sampo dove", this.product.getProductName());
    }

    @Test
    void testGetProductNameEmpty() {
        this.product.setProductName("");
        assertEquals("", this.product.getProductName());
    }

    @Test
    void testGetProductNameNull() {
        this.product.setProductName(null);
        assertNull(this.product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getProductQuantity());
    }

    @Test
    void testGetProductQuantityInvalid() {
        assertNotEquals(50, this.product.getProductQuantity());
    }

    @Test
    void testGetProductQuantityNegative() {
        this.product.setProductQuantity(-50);
        assertEquals(-50, this.product.getProductQuantity());
    }

    @Test
    void testGetProductQuantityZero() {
        this.product.setProductQuantity(0);
        assertEquals(0, this.product.getProductQuantity());
    }
}