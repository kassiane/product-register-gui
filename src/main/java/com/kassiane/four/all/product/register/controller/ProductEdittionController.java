package com.kassiane.four.all.product.register.controller;

import com.kassiane.four.all.product.register.dto.ProductDTO;
import com.kassiane.four.all.product.register.listener.ProductEdittionListeners;
import com.kassiane.four.all.product.register.service.ProductService;
import com.kassiane.four.all.product.register.view.ProductEdittionView;

public class ProductEdittionController {

    private final ProductEdittionView productEdittion;
    private final ProductEdittionListeners productEdittionListener;
    private final ProductDTO productModel;

    public ProductEdittionController(final ProductEdittionView productEdittion, final ProductService productService) {
        this.productEdittion = productEdittion;

        this.productEdittionListener = new ProductEdittionListeners(productEdittion, productService);
        this.productEdittionListener.addListeners();
        this.productModel = new ProductDTO("", "", this.productEdittion.getDefaultImage(), null);
    }

    public ProductEdittionView getProductEdittion() {
        return this.productEdittion;
    }

    public ProductDTO getProductModel() {
        return this.productModel;
    }
}
