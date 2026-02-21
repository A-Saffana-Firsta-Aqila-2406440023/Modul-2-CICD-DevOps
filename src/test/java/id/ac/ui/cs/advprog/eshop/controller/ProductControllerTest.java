package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService service;

    private Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPostSuccess() throws Exception {
        mockMvc.perform(post("/product/create")
                        .param("productName", "Sampo Cap Bambang")
                        .param("productQuantity", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(service, times(1)).create(any(Product.class));
    }

    @Test
    void testCreateProductPostFailed() throws Exception {
        mockMvc.perform(post("/product/create")
                        .param("productName", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"));

        verify(service, times(0)).create(any(Product.class));
    }

    @Test
    void testEditProductPage() throws Exception {
        when(service.findById(product.getProductId())).thenReturn(product);

        mockMvc.perform(get("/product/edit/" + product.getProductId()))
                .andExpect(status().isOk())
                .andExpect(view().name("editProduct"))
                .andExpect(model().attribute("product", product));

        verify(service, times(1)).findById(product.getProductId());
    }

    @Test
    void testEditProductPostSuccess() throws Exception {
        mockMvc.perform(post("/product/edit")
                        .param("productId", product.getProductId())
                        .param("productName", "Sampo Cap Bambang New")
                        .param("productQuantity", "200"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(service, times(1)).edit(any(Product.class));
    }

    @Test
    void testEditProductPostFailed() throws Exception {
        mockMvc.perform(post("/product/edit")
                        .param("productName", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("editProduct"));

        verify(service, times(0)).edit(any(Product.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(get("/product/delete/" + product.getProductId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(service, times(1)).delete(product.getProductId());
    }

    @Test
    void testProductListPage() throws Exception {
        List<Product> allProducts = new ArrayList<>();
        allProducts.add(product);
        when(service.findAll()).thenReturn(allProducts);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attribute("products", allProducts));

        verify(service, times(1)).findAll();
    }
}