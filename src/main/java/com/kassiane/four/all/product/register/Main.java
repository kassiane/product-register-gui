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

    public static void main(final String[] args) {

        final ApplicationContext context = new ClassPathXmlApplicationContext("spring-Database.xml");
        final ProductDAO productDAO = (ProductDAO) context.getBean("productDAOImpl");

        final ProductService productService = new ProductService(productDAO);

        final ImageIcon defaultProductEdittionImageIcon = new DefaultImage()
                .getDefaultImageIcon(ProductEdittionView.IMAGE_HEIGHT);

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                final ProductEdittionView productEdittion = new ProductEdittionView(defaultProductEdittionImageIcon);
                final ProductEdittionController productEdittionController = new ProductEdittionController(productEdittion,
                        productService);
                final ImageIcon defaultProductRegisterImageIcon = new DefaultImage()
                        .getDefaultImageIcon(ProductRegisterView.IMAGE_HEIGHT);
                final ProductRegisterView productRegisterMainFrame = new ProductRegisterView(productService,
                        defaultProductRegisterImageIcon);
                final ProductRegisterListeners productRegisterMainFramecontroller = new ProductRegisterListeners(
                        productEdittionController, productRegisterMainFrame);
                productRegisterMainFramecontroller.controllerControl(productEdittionController);
            }

        });

    }
}