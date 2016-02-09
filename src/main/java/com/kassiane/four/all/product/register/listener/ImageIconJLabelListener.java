package com.kassiane.four.all.product.register.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.kassiane.four.all.product.register.action.ProductEdittionAction;

public class ImageIconJLabelListener implements MouseListener {

    private final ProductEdittionAction productEdittionAction;

    public ImageIconJLabelListener(final ProductEdittionAction productEdittionAction) {
        this.productEdittionAction = productEdittionAction;
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        this.productEdittionAction.chooseNewImageIcon();
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(final MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(final MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
