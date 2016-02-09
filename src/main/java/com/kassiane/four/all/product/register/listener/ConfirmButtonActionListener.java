package com.kassiane.four.all.product.register.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.kassiane.four.all.product.register.action.ProductEdittionAction;
import com.kassiane.four.all.product.register.view.ProductEdittionView;

public class ConfirmButtonActionListener implements ActionListener {

    public static final String ADD_NEW_PRODUCT = "Add new Product";

    private final ProductEdittionAction productEdittionAction;
    private final ProductEdittionView productEdittionView;

    public ConfirmButtonActionListener(final ProductEdittionAction productEdittionAction, final ProductEdittionView productEdittionView) {
        this.productEdittionAction = productEdittionAction;
        this.productEdittionView = productEdittionView;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        this.productEdittionAction.addNewProduct(this.productEdittionView.getProductModel());
        this.productEdittionView.getConfirmButton().firePropertyChange(ADD_NEW_PRODUCT, false, true);
        this.productEdittionView.getJDialog().dispose();
        this.productEdittionView.clearProductDTO();
    }

}
