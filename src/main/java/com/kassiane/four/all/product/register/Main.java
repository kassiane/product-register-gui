package com.kassiane.four.all.product.register;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kassiane.four.all.product.register.controller.ProductEdittionController;
import com.kassiane.four.all.product.register.image.util.DefaultImage;
import com.kassiane.four.all.product.register.listener.ProductRegisterListeners;
import com.kassiane.four.all.product.register.service.ProductService;
import com.kassiane.four.all.product.register.view.ProductEdittionView;
import com.kassiane.four.all.product.register.view.ProductRegisterView;

@SpringBootApplication
public class Main extends JFrame {

    private static final long serialVersionUID = 7111965847615977120L;

    private static ProductService productService;

    public static void main(final String[] args) {

        SpringApplication.run(Main.class, args);

        final ImageIcon defaultProductEdittionImageIcon = new DefaultImage()
                .getDefaultImageIcon(ProductEdittionView.IMAGE_HEIGHT);
        final ImageIcon defaultProductRegisterImageIcon = new DefaultImage()
                .getDefaultImageIcon(ProductRegisterView.IMAGE_HEIGHT);

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                final ProductEdittionView productEdittionView = new ProductEdittionView(defaultProductEdittionImageIcon);
                final ProductEdittionController productEdittionController = new ProductEdittionController(productEdittionView,
                        Main.productService);
                final ProductRegisterView productRegisterView = new ProductRegisterView(Main.productService,
                        defaultProductRegisterImageIcon);
                final ProductRegisterListeners productRegisterListeners = new ProductRegisterListeners(productEdittionController,
                        productRegisterView, Main.productService);
                productRegisterListeners.addListeners();
            }

        });

    }

    @Autowired
    public void setProductService(final ProductService productService) {
        Main.productService = productService;
    }
}