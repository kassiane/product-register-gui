package com.kassiane.four.all.product.register.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;

import com.kassiane.four.all.product.register.dto.ProductDTO;
import com.kassiane.four.all.product.register.dto.ProductTableModel;
import com.kassiane.four.all.product.register.service.ProductService;
import com.kassiane.four.all.product.register.service.domain.Product;

public class ProductRegisterView {
    public static final int IMAGE_HEIGHT = 100;

    private final String title = "Cadastro de Produtos";

    private final JButton newProduct = new JButton("Novo Produto");
    private final ProductTableModel model;

    private JTable productsTable = new JTable();

    public ProductRegisterView(final ProductService productService, final ImageIcon defaultImageIcon) {
        this.model = new ProductTableModel(ProductRegisterView.IMAGE_HEIGHT, defaultImageIcon);
        this.productsTable = this.createProductsTable(productService);

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final double width = screenSize.getWidth();
        final double height = screenSize.getHeight();

        final JFrame f = new JFrame(this.title);
        f.setSize((int) width, (int) height);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Container mainPane = f.getContentPane();
        final GroupLayout groupLayout = new GroupLayout(mainPane);
        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        final Container productsPane = new JScrollPane(this.productsTable);
        this.setHorizontalGroup(this.newProduct, groupLayout, productsPane);
        this.setVerticalGroup(this.newProduct, groupLayout, productsPane);
        mainPane.setLayout(groupLayout);

        f.setVisible(true);
    }

    private void setVerticalGroup(final JButton newProduct, final GroupLayout groupLayout, final Container productsPane) {
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup().addComponent(newProduct))
                .addGroup(groupLayout.createParallelGroup().addComponent(productsPane))

        );
    }

    private void setHorizontalGroup(final JButton newProduct, final GroupLayout groupLayout, final Container productsPane) {
        groupLayout.setHorizontalGroup(groupLayout
                .createParallelGroup()
                .addGroup(
                        groupLayout
                                .createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE).addComponent(newProduct))
                .addGroup(groupLayout.createSequentialGroup().addComponent(productsPane)));
    }

    private JTable createProductsTable(final ProductService productService) {
        final JTable productsTable = new JTable(0, 3);
        productsTable.setRowHeight(ProductRegisterView.IMAGE_HEIGHT);
        productsTable.setModel(this.model);

        this.addAllProductsToTableModel(productService);

        return productsTable;
    }

    public void addAllProductsToTableModel(final ProductService productService) {
        final List<Product> products = productService.listProducts();
        this.model.addProductListToTableModel(products);

    }

    public void addRow(final ProductDTO product) {
        this.model.addProductRow(product);
    }

    public JButton getNewProduct() {
        return this.newProduct;
    }

}
