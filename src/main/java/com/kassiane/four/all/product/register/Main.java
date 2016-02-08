package com.kassiane.four.all.product.register;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kassiane.four.all.product.register.controller.ProductEdittionController;
import com.kassiane.four.all.product.register.dao.ProductDAO;
import com.kassiane.four.all.product.register.listener.ProductRegisterListeners;
import com.kassiane.four.all.product.register.view.ProductEdittion;
import com.kassiane.four.all.product.register.view.ProductRegister;

public class Main extends JFrame {

    public static void main(final String[] args) {

        final ApplicationContext context = new ClassPathXmlApplicationContext("spring-Database.xml");
        final ProductDAO productDAO = (ProductDAO) context.getBean("productDAOImpl");

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                ProductEdittion productEdittion = null;
                try {
                    productEdittion = new ProductEdittion(productDAO);
                } catch (final IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                final ProductEdittionController productEdittionController = new ProductEdittionController(productEdittion);

                final ProductRegister productRegisterMainFrame = new ProductRegister(productDAO);
                final ProductRegisterListeners productRegisterMainFramecontroller = new ProductRegisterListeners(
                        productEdittionController, productRegisterMainFrame);
                productRegisterMainFramecontroller.controllerControl(productEdittionController);
            }

        });

    }

}