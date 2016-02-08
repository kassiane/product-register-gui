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
import javax.swing.table.DefaultTableModel;

import com.kassiane.four.all.product.register.dao.ProductDAO;
import com.kassiane.four.all.product.register.domain.Product;
import com.kassiane.four.all.product.register.image.util.DefaultImage;
import com.kassiane.four.all.product.register.image.util.ImageIconResizer;
import com.kassiane.four.all.product.register.listener.ProductRegisterListeners;

public class ProductRegister {

    private static final String TITLE = "Cadastro de Produtos";
    private final JButton newProduct = new JButton("Novo Produto");
    private static final int IMAGE_HEIGHT = 100;

    private DefaultTableModel model;
    private ImageIcon defaultImage;
    private final ProductDAO productDAO;
    private final JTable productsTable;
    private ProductRegisterListeners productRegisterController;

    public ProductRegister(final ProductDAO productDAO) {
        this.model = null;
        this.productDAO = productDAO;
        this.setDefaultImageIcon();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final double width = screenSize.getWidth();
        final double height = screenSize.getHeight();

        final JFrame f = new JFrame(TITLE);
        f.setSize((int) width, (int) height);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Container mainPane = f.getContentPane();
        final GroupLayout groupLayout = new GroupLayout(mainPane);
        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        this.productsTable = this.createProductsTable();
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

    private JTable createProductsTable() {
        final List<Product> products = this.productDAO.listProducts();
        final JTable productsTable = new JTable(0, 3);
        productsTable.setRowHeight(IMAGE_HEIGHT);

        this.model = this.changeTableModelForFirstColumnAsImageIcon();

        if (products != null) {
            for (final Product product : products) {
                final String name = product.getName();
                final String price = String.valueOf(product.getPrice());
                ImageIcon imageIcon = null;
                if (product.getIcon() != null) {
                    final ImageIconResizer imageIconResizer = new ImageIconResizer();
                    imageIcon = imageIconResizer.resizeImageIcon(product.getIcon(), IMAGE_HEIGHT);
                } else {
                    imageIcon = this.getDefaultImageIcon();
                }

                this.model.addRow(new Object[] { imageIcon, name, price });
            }
        }
        productsTable.setModel(this.model);
        return productsTable;
    }

    public void addRow(final Product product) {
        final ImageIconResizer imageIconResizer = new ImageIconResizer();
        final ImageIcon resized = imageIconResizer.resizeImageIcon(product.getIcon(), IMAGE_HEIGHT);
        this.model.addRow(new Object[] { resized, product.getName(), product.getPrice() });
    }

    private DefaultTableModel changeTableModelForFirstColumnAsImageIcon() {
        final String[] columnNames = { "Imagem", "Nome", "Preço" };
        final DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(final int column) {
                if (column == 0) {
                    return ImageIcon.class;
                }
                return super.getColumnClass(column);
            }

            @Override
            public boolean isCellEditable(final int row, final int column) {
                if (column == 0)
                    return false;
                return true;
            }

        };
        return model;
    }

    private void setDefaultImageIcon() {
        this.defaultImage = new DefaultImage().getDefaultImageIcon(IMAGE_HEIGHT);
    }

    private ImageIcon getDefaultImageIcon() {
        return this.defaultImage;
    }

    public ProductDAO getProductDAO() {
        return this.productDAO;
    }

    public JButton getNewProduct() {
        return this.newProduct;
    }

    public void setController(final ProductRegisterListeners productRegisterController) {
        this.productRegisterController = productRegisterController;
    }

    public ProductRegisterListeners getProductRegisterController() {
        return this.productRegisterController;
    }
}
