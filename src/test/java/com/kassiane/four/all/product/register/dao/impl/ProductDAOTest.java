package com.kassiane.four.all.product.register.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kassiane.four.all.product.register.service.dao.ProductDAO;
import com.kassiane.four.all.product.register.service.domain.Product;

public class ProductDAOTest {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    private static ProductDAO productDAO;
    private static ApplicationContext context;

    @BeforeClass
    public static void setUp() {
        context = new ClassPathXmlApplicationContext("spring-Database.xml");

        final ProductDAOTest testProductDAOImpl = (ProductDAOTest) context.getBean("productDAOTest");
        productDAO = (ProductDAO) context.getBean("productDAO");
        testProductDAOImpl.deleteAllProducts();

    }

    @AfterClass
    public static void endUp() {

    }

    public void setDataSource(final DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(this.dataSource);

    }

    private void deleteAllProducts() {
        final String query = "delete from Product;";
        this.jdbcTemplateObject.update(query);
    }

    @Test
    public void SelectAllProductsSuccessfully() {
        final List<Product> products = productDAO.listProducts();
        if (products.size() == 0) {
            System.out.println("No products found.");
        }

    }
}
