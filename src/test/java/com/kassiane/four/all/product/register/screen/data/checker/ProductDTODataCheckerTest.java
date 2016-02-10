package com.kassiane.four.all.product.register.screen.data.checker;

import org.junit.Test;

import com.kassiane.four.all.product.register.dto.ProductDTO;
import com.kassiane.four.all.product.register.screen.data.checker.ProductDTODataChecker;

public class ProductDTODataCheckerTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnExceptionOnEmptyProductName() {
        final ProductDTO productDTO = new ProductDTO(0, "", "1.23", null, null);

        final ProductDTODataChecker productDTODataChecker = new ProductDTODataChecker(productDTO);
        productDTODataChecker.checkProduct();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnExceptionOnEmptyProductPrice() {
        final ProductDTO productDTO = new ProductDTO(0, "test", "", null, null);

        final ProductDTODataChecker productDTODataChecker = new ProductDTODataChecker(productDTO);
        productDTODataChecker.checkProduct();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnExceptionOnProductPriceIsZero() {
        final ProductDTO productDTO = new ProductDTO(0, "test", "0", null, null);

        final ProductDTODataChecker productDTODataChecker = new ProductDTODataChecker(productDTO);
        productDTODataChecker.checkProduct();
    }
}
