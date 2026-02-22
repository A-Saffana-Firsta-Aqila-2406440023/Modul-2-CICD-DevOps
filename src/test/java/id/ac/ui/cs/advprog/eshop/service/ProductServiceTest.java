package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }

    @Test
    void testCreate() {
        when(productRepository.create(product)).thenReturn(product);
        Product result = productService.create(product);
        assertEquals(product.getProductId(), result.getProductId());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testEdit() {
        when(productRepository.edit(product)).thenReturn(product);
        Product result = productService.edit(product);
        assertEquals(product.getProductId(), result.getProductId());
        verify(productRepository, times(1)).edit(product);
    }

    @Test
    void testDelete() {
        when(productRepository.delete(product.getProductId())).thenReturn(product);
        Product result = productService.delete(product.getProductId());
        assertEquals(product.getProductId(), result.getProductId());
        verify(productRepository, times(1)).delete(product.getProductId());
    }

    @Test
    void testFindById() {
        when(productRepository.findById(product.getProductId())).thenReturn(product);
        Product result = productService.findById(product.getProductId());
        assertEquals(product.getProductId(), result.getProductId());
        verify(productRepository, times(1)).findById(product.getProductId());
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Iterator<Product> iterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(product.getProductId(), result.get(0).getProductId());
        verify(productRepository, times(1)).findAll();
    }
}