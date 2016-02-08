package com.kassiane.four.all.product.register.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.kassiane.four.all.product.register.action.ProductEdittionAction;
import com.kassiane.four.all.product.register.view.ProductEdittion;

public class ProductEdittionListeners {

    public static final String ADD_NEW_PRODUCT = "Add new Product";

    private final ProductEdittion productEdittion;
    ProductEdittionAction productEdittionAction;

    public ProductEdittionListeners(final ProductEdittion productEdittion) {
        this.productEdittion = productEdittion;
        this.productEdittionAction = new ProductEdittionAction(this.productEdittion);
    }

    public void addCancelButtonListener() {
        this.productEdittion.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                ProductEdittionListeners.this.productEdittion.getjDialog().dispose();
            }
        });
    }

    public void addImageIconJLabelListener() {
        this.productEdittion.getImageIconJLabel().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                ProductEdittionListeners.this.productEdittionAction.chooseNewImageIcon();
            }

            @Override
            public void mousePressed(final MouseEvent e) {
            }

            @Override
            public void mouseReleased(final MouseEvent e) {
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
            }

            @Override
            public void mouseExited(final MouseEvent e) {
            }
        });
    }

    public void addConfirmButtonListener() {
        this.productEdittion.getConfirmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                ProductEdittionListeners.this.productEdittion.getConfirmButton().firePropertyChange(ADD_NEW_PRODUCT, false, true);
                ProductEdittionListeners.this.productEdittion.getjDialog().dispose();

            }
        });
    }

    public ActionListener[] getCancelButtonListener() {
        return this.productEdittion.getCancelButton().getListeners(ActionListener.class);
    }

    public ActionListener[] getConfirmButtonListener() {
        return this.productEdittion.getConfirmButton().getListeners(ActionListener.class);
    }

    public MouseListener[] getImageIconJLabelListener() {
        return this.productEdittion.getCancelButton().getListeners(MouseListener.class);
    }

    public void addListeners() {
        this.addCancelButtonListener();
        this.addConfirmButtonListener();
        this.addImageIconJLabelListener();
    }
}
