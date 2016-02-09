package com.kassiane.four.all.product.register.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.kassiane.four.all.product.register.controller.ProductEdittionController;

public class NewProductButtonListener implements ActionListener {

    ProductEdittionController productEdittionController;

    public NewProductButtonListener(final ProductEdittionController productEdittionController) {
        this.productEdittionController = productEdittionController;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        this.productEdittionController.getProductEdittion().setVisible(true);
    }
}
