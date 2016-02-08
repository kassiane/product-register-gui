package com.kassiane.four.all.product.register.controller;

import com.kassiane.four.all.product.register.listener.ProductEdittionListeners;
import com.kassiane.four.all.product.register.view.ProductEdittion;

public class ProductEdittionController {

    private final ProductEdittion productEdittion;
    private final ProductEdittionListeners productEdittionListener;

    public ProductEdittionController(final ProductEdittion productEdittion) {
        this.productEdittion = productEdittion;

        this.productEdittionListener = new ProductEdittionListeners(productEdittion);
        this.productEdittionListener.addListeners();
    }

    public ProductEdittion getProductEdittion() {
        return this.productEdittion;
    }
}
