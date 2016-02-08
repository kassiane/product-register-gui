package com.kassiane.four.all.product.register.view;

import java.awt.Container;
import java.awt.Dialog.ModalityType;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.kassiane.four.all.product.register.controller.ProductEdittionController;
import com.kassiane.four.all.product.register.dao.ProductDAO;
import com.kassiane.four.all.product.register.domain.Product;
import com.kassiane.four.all.product.register.image.util.DefaultImage;

public class ProductEdittion {

    private static final String TITLE = "Edição de Produto";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 350;
    private static final int IMAGE_HEIGHT = 200;
    private static final JButton CONFIRM_BUTTON = new JButton("Confirmar");
    private static final JButton CANCEL_BUTTON = new JButton("Cancelar");
    private static final JLabel PRODUCT_NAME_LABEL = new JLabel("Nome do Produto");
    private static final JLabel PRICE_LABEL = new JLabel("Preço");
    private static final JLabel IMAGE_LABEL = new JLabel("Imagem");

    private final ProductDAO productDAO;
    private final JTextField productNameTextArea;
    private final JTextField productPriceTextArea;
    private final JLabel imageIconJLabel;
    private final JDialog jDialog;

    private File imageFile;
    private ImageIcon defaultImage;
    private ProductEdittionController productEdittionController;
    private Product newProduct;

    public ProductEdittion(final ProductDAO productDAO) throws IOException {
        this.jDialog = new JDialog();
        this.productDAO = productDAO;
        this.productNameTextArea = new JTextField();
        this.productPriceTextArea = new JTextField();
        this.imageIconJLabel = new JLabel();
        this.imageFile = null;
        this.newProduct = null;
        this.setDefaultImageIcon();

        this.jDialog.setSize(WIDTH, HEIGHT);
        this.jDialog.setTitle(TITLE);
        this.jDialog.setLocationRelativeTo(null);
        this.jDialog.setModalityType(ModalityType.APPLICATION_MODAL);

        this.addProductEdditionComponents();
    }

    private void setDefaultImageIcon() throws IOException {
        this.defaultImage = new DefaultImage().getDefaultImageIcon(IMAGE_HEIGHT);
        this.imageIconJLabel.setIcon(this.defaultImage);
    }

    public void addProductEdditionComponents() {
        final Container panel = new JPanel();
        final GroupLayout groupLayout = new GroupLayout(panel);
        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        this.setHorizontalGroup(groupLayout);
        this.setVerticalGroup(groupLayout);

        panel.setLayout(groupLayout);
        this.jDialog.setContentPane(panel);
    }

    private void setVerticalGroup(final GroupLayout groupLayout) {
        final SequentialGroup sequentialGroup = groupLayout.createSequentialGroup();
        sequentialGroup
                .addGroup(
                        groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(PRODUCT_NAME_LABEL)
                                .addComponent(this.productNameTextArea))
                .addGroup(
                        groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(PRICE_LABEL)
                                .addComponent(this.productPriceTextArea))
                .addGroup(
                        groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(IMAGE_LABEL)
                                .addComponent(this.imageIconJLabel))
                .addGroup(
                        groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(CONFIRM_BUTTON)
                                .addComponent(CANCEL_BUTTON));

        groupLayout.setVerticalGroup(sequentialGroup);
    }

    private void setHorizontalGroup(final GroupLayout groupLayout) {
        final SequentialGroup sequentialGroup = groupLayout.createSequentialGroup();
        sequentialGroup.addGroup(
                groupLayout.createParallelGroup().addComponent(PRODUCT_NAME_LABEL).addComponent(PRICE_LABEL)
                        .addComponent(IMAGE_LABEL).addComponent(CONFIRM_BUTTON)).addGroup(
                groupLayout.createParallelGroup().addComponent(this.productNameTextArea).addComponent(this.productPriceTextArea)
                        .addComponent(this.imageIconJLabel).addComponent(CANCEL_BUTTON));

        groupLayout.setHorizontalGroup(sequentialGroup);
    }

    public JTextField getProductNameTextArea() {
        return this.productNameTextArea;
    }

    public JTextField getProductPriceTextArea() {
        return this.productPriceTextArea;
    }

    public JLabel getImageIconJLabel() {
        return this.imageIconJLabel;
    }

    public JButton getCancelButton() {
        return CANCEL_BUTTON;
    }

    public JButton getConfirmButton() {
        return CONFIRM_BUTTON;
    }

    public JDialog getJDialog() {
        return this.jDialog;
    }

    public static String getTitle() {
        return TITLE;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public static int getImageHeight() {
        return IMAGE_HEIGHT;
    }

    public static JLabel getProductNameLabel() {
        return PRODUCT_NAME_LABEL;
    }

    public static JLabel getPriceLabel() {
        return PRICE_LABEL;
    }

    public static JLabel getImageLabel() {
        return IMAGE_LABEL;
    }

    public ProductDAO getProductDAO() {
        return this.productDAO;
    }

    public File getImageFile() {
        return this.imageFile;
    }

    public void setImageFile(final File file) {
        this.imageFile = file;
    }

    public ImageIcon getDefaultImage() {
        return this.defaultImage;
    }

    public JDialog getjDialog() {
        return this.jDialog;
    }

    public void setVisible(final boolean visible) {
        this.jDialog.setVisible(visible);
    }

    public void setController(final ProductEdittionController productEdittionController) {
        this.productEdittionController = productEdittionController;
    }

    public void setNewProduct(final Product product) {
        this.newProduct = product;
    }

    public Product getNewProduct() {
        return this.newProduct;
    }

    public ProductEdittionController getController() {
        return this.productEdittionController;
    }

}
