package com.kassiane.four.all.product.register.listener;

import com.kassiane.four.all.product.register.controller.ProductEdittionController;
import com.kassiane.four.all.product.register.service.ProductService;
import com.kassiane.four.all.product.register.view.ProductRegisterView;

public class ProductRegisterListeners {

    private final ProductRegisterView productRegisterView;
    private final ProductEdittionController productEdittionController;
    private final ProductService productService;

    public ProductRegisterListeners(final ProductEdittionController productEdittionController,
            final ProductRegisterView productRegister, final ProductService productService) {

        this.productEdittionController = productEdittionController;
        this.productRegisterView = productRegister;
        this.productService = productService;

        this.addListeners();
    }

    private void addNewProductButtonListener() {
        this.productRegisterView.getNewProduct().addActionListener(new NewProductButtonListener(this.productEdittionController));
    }

    private void addProductEdittionListener() {
        this.productEdittionController
                .getProductEdittion()
                .getConfirmButton()
        .addPropertyChangeListener(
                        new ConfirmButtonPropertyChangeListener(this.productRegisterView, this.productEdittionController));
    }

    private void addProductsTableListener() {
        this.productRegisterView.getProductsTable().getModel()
                .addTableModelListener(new ProductTableListener(this.productRegisterView, this.productService));
    }

    public void addListeners() {
        this.addNewProductButtonListener();
        this.addProductEdittionListener();
        this.addProductsTableListener();
    }

}
