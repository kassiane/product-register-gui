package com.kassiane.four.all.product.register.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.kassiane.four.all.product.register.action.ProductEdittionAction;
import com.kassiane.four.all.product.register.controller.ProductEdittionController;
import com.kassiane.four.all.product.register.view.ProductRegisterView;

public class ConfirmButtonPropertyChangeListener implements PropertyChangeListener {

    private final ProductRegisterView productRegisterView;
    private final ProductEdittionController productEdittionController;

    public ConfirmButtonPropertyChangeListener(final ProductRegisterView productRegisterView,
            final ProductEdittionController productEdittionController) {
        this.productRegisterView = productRegisterView;
        this.productEdittionController = productEdittionController;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(ProductEdittionAction.ADD_NEW_PRODUCT)) {
            this.productRegisterView.addRow(this.productEdittionController.getProductEdittion().getProductModel());
        }
    }
}
