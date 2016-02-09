package com.kassiane.four.all.product.register.listener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

import javax.swing.ImageIcon;

import com.kassiane.four.all.product.register.dto.ProductDTO;
import com.kassiane.four.all.product.register.view.ProductEdittionView;

public class ProductPriceTextFieldListener implements FocusListener {

    private final ProductEdittionView productEdittionView;

    public ProductPriceTextFieldListener(final ProductEdittionView productEdittionView) {
        this.productEdittionView = productEdittionView;
    }

    @Override
    public void focusGained(final FocusEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void focusLost(final FocusEvent e) {
        final String productName = this.productEdittionView.getProductModel().getProductName();
        final String productPrice = this.productEdittionView.getProductPriceTextField().getText();
        final ImageIcon imageIcon = this.productEdittionView.getProductModel().getImageIcon();
        final File imageFile = this.productEdittionView.getProductModel().getImageFile();

        final ProductDTO productModel = new ProductDTO(productName, productPrice, imageIcon, imageFile);
        this.productEdittionView.setProductModel(productModel);
    }

}
