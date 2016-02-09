package com.kassiane.four.all.product.register.listener;

import com.kassiane.four.all.product.register.controller.ProductEdittionController;
import com.kassiane.four.all.product.register.view.ProductRegisterView;

public class ProductRegisterListeners {

    private final ProductRegisterView productRegisterView;
    private final ProductEdittionController productEdittionController;

    public ProductRegisterListeners(final ProductEdittionController productEdittionController,
            final ProductRegisterView productRegister) {

        this.productEdittionController = productEdittionController;
        this.productRegisterView = productRegister;
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

    public void controllerControl(final ProductEdittionController productEdittionController) {
        this.addNewProductButtonListener();
        this.addProductEdittionListener();
    }

    public ProductRegisterView getProductRegister() {
        return this.productRegisterView;
    }
}
