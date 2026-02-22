package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EshopApplicationTests {

    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> {});
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> EshopApplication.main(new String[] {}));
    }
}