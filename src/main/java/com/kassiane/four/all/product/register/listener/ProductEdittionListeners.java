package com.kassiane.four.all.product.register.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.kassiane.four.all.product.register.action.ProductEdittionAction;
import com.kassiane.four.all.product.register.service.ProductService;
import com.kassiane.four.all.product.register.view.ProductEdittionView;

public class ProductEdittionListeners {

    private final ProductEdittionView productEdittionView;
    ProductEdittionAction productEdittionAction;

    public ProductEdittionListeners(final ProductEdittionView productEdittion, final ProductService productService) {
        this.productEdittionView = productEdittion;
        this.productEdittionAction = new ProductEdittionAction(this.productEdittionView, productService);
    }

    public void addCancelButtonListener() {
        this.productEdittionView.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                ProductEdittionListeners.this.productEdittionView.getJDialog().dispose();
                ProductEdittionListeners.this.productEdittionView.clearProductDTO();
            }
        });
    }

    public void addImageIconJLabelListener() {
        this.productEdittionView.getImageIconJLabel().addMouseListener(new ImageIconJLabelListener(this.productEdittionAction));
    }

    public void addConfirmButtonListener() {
        this.productEdittionView.getConfirmButton().addActionListener(
                new ConfirmButtonActionListener(this.productEdittionAction, this.productEdittionView));

    }

    public void addProductNameTextFieldListener() {
        this.productEdittionView.getProductNameTextField().addFocusListener(
                new ProductNameTextFieldListener(this.productEdittionView));
    }

    public void addProductPriceTextFieldListener() {
        this.productEdittionView.getProductPriceTextField().addFocusListener(
                new ProductPriceTextFieldListener(this.productEdittionView));
    }

    public void addListeners() {
        this.addCancelButtonListener();
        this.addConfirmButtonListener();
        this.addImageIconJLabelListener();
        this.addProductPriceTextFieldListener();
        this.addProductNameTextFieldListener();
    }
}
