package com.kassiane.four.all.product.register.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.kassiane.four.all.product.register.controller.ProductEdittionController;
import com.kassiane.four.all.product.register.view.ProductEdittion;
import com.kassiane.four.all.product.register.view.ProductRegister;

public class ProductRegisterListeners {

    private final ProductRegister productRegister;
    private final ProductEdittionController productEdittionController;

    public ProductRegisterListeners(final ProductEdittionController productEdittionController,
            final ProductRegister productRegister) {

        this.productEdittionController = productEdittionController;
        this.productRegister = productRegister;
    }

    private void addNewProductButtonListener() {
        final ProductEdittionController finalProductEdittionController = this.productEdittionController;
        this.productRegister.getNewProduct().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                final ProductEdittion productEdittion = finalProductEdittionController.getProductEdittion();
                productEdittion.setVisible(true);
            }
        });
    }

    private void addProductEdittionListener() {
        this.productEdittionController.getProductEdittion().getConfirmButton()
                .addPropertyChangeListener(new PropertyChangeListener() {

                    @Override
                    public void propertyChange(final PropertyChangeEvent evt) {
                        if (evt.getPropertyName().equals(ProductEdittionListeners.ADD_NEW_PRODUCT)) {
                            System.out.println("Vai atualizar.........");
                            ProductRegisterListeners.this.productRegister
                    .addRow(ProductRegisterListeners.this.productEdittionController.getProductEdittion()
                            .getNewProduct());
                        }
                    }
                });
    }

    public void controllerControl(final ProductEdittionController productEdittionController) {
        this.addNewProductButtonListener();
        this.addProductEdittionListener();
    }

    public ProductRegister getProductRegister() {
        return this.productRegister;
    }
}
