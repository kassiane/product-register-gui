package com.kassiane.four.all.product.register.mapper;

import javax.swing.ImageIcon;

import com.kassiane.four.all.product.register.dto.ProductDTO;
import com.kassiane.four.all.product.register.service.domain.Product;

public class ProductDTOToProductMapper {

    public Product mapToProduct(final ProductDTO productDTO) {
        final String name = productDTO.getProductName();
        final float price = Float.valueOf(productDTO.getProductPrice().replace(',', '.'));
        final ImageIcon imageIcon = productDTO.getImageIcon();

        final Product newProduct = new Product(0, name, price, imageIcon);

        return newProduct;
    }
}
