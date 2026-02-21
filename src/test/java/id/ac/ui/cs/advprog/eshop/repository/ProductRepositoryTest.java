package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }

    @Test
    void testCreateAndFind() {
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        productRepository.create(product);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindByIdNotFound_WhenListIsNotEmpty() {
        productRepository.create(product);

        Product foundProduct = productRepository.findById("invalid-id");
        assertNull(foundProduct);
    }

    @Test
    void testCreateWithoutId() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertNotNull(product.getProductId());
        assertFalse(product.getProductId().isEmpty());
    }

    @Test
    void testEditValid() {
        productRepository.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId(product.getProductId());
        editedProduct.setProductName("Sampo Cap Bambang New");
        editedProduct.setProductQuantity(200);
        productRepository.edit(editedProduct);

        Product foundProduct = productRepository.findById(product.getProductId());
        assertNotNull(foundProduct);
        assertEquals("Sampo Cap Bambang New", foundProduct.getProductName());
        assertEquals(200, foundProduct.getProductQuantity());
    }

    @Test
    void testEditInvalid_ProductNotFound() {
        productRepository.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId("invalid-id");
        editedProduct.setProductName("Sampo Cap Bambang New");
        editedProduct.setProductQuantity(200);
        Product result = productRepository.edit(editedProduct);

        assertNull(result);

        Product foundOriProduct = productRepository.findById(product.getProductId());
        assertNotNull(foundOriProduct);
        assertEquals("Sampo Cap Bambang", foundOriProduct.getProductName());
        assertEquals(100, foundOriProduct.getProductQuantity());
    }

    @Test
    void testDeleteValid() {
        productRepository.create(product);

        Product deletedProduct = productRepository.delete(product.getProductId());
        assertNotNull(deletedProduct);

        Product foundProduct = productRepository.findById(product.getProductId());
        assertNull(foundProduct);
    }

    @Test
    void testDeleteInvalid_ProductNotFound() {
        Product result = productRepository.delete("invalid-id");
        assertNull(result);
    }
}