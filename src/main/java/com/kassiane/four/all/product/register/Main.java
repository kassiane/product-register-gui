package com.kassiane.four.all.product.register;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kassiane.four.all.product.register.controller.ProductEdittionController;
import com.kassiane.four.all.product.register.image.util.DefaultImage;
import com.kassiane.four.all.product.register.listener.ProductRegisterListeners;
import com.kassiane.four.all.product.register.service.ProductService;
import com.kassiane.four.all.product.register.service.dao.ProductDAO;
import com.kassiane.four.all.product.register.view.ProductEdittionView;
import com.kassiane.four.all.product.register.view.ProductRegisterView;

public class Main extends JFrame {

    private static final long serialVersionUID = 7111965847615977120L;

    private static ApplicationContext context;

    public static void main(final String[] args) {

        context = new ClassPathXmlApplicationContext("spring-Database.xml");
        final ProductDAO productDAO = (ProductDAO) context.getBean("productDAOImpl");

        final ProductService productService = new ProductService(productDAO);

        final ImageIcon defaultProductEdittionImageIcon = new DefaultImage()
                .getDefaultImageIcon(ProductEdittionView.IMAGE_HEIGHT);
        final ImageIcon defaultProductRegisterImageIcon = new DefaultImage()
                .getDefaultImageIcon(ProductRegisterView.IMAGE_HEIGHT);

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                final ProductEdittionView productEdittionView = new ProductEdittionView(defaultProductEdittionImageIcon);
                final ProductEdittionController productEdittionController = new ProductEdittionController(productEdittionView,
                        productService);
                final ProductRegisterView productRegisterView = new ProductRegisterView(productService,
                        defaultProductRegisterImageIcon);
                final ProductRegisterListeners productRegisterListeners = new ProductRegisterListeners(productEdittionController,
                        productRegisterView, productService);
                productRegisterListeners.addListeners();
            }

        });

    }
}