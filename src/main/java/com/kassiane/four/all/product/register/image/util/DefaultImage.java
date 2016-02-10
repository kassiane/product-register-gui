package com.kassiane.four.all.product.register.image.util;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class DefaultImage {

    private final InputStream in;
    public Image imageIcon;

    public DefaultImage() {
        this.in = this.getClass().getResourceAsStream("/images/defaultImage.png");
        try {
            this.imageIcon = ImageIO.read(this.in);
        } catch (final IOException e) {
            System.err.println("Erro parsing a imagem padrão.");
            e.printStackTrace();
        }
    }

    public ImageIcon getDefaultImageIcon(final int heigth) {
        final ImageIconResizer imageIconResizer = new ImageIconResizer();
        final ImageIcon icon = imageIconResizer.imageToImageIconResized(this.imageIcon, heigth);

        return icon;
    }
}