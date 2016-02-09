package com.kassiane.four.all.product.register.view;

import java.awt.Container;
import java.awt.Dialog.ModalityType;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.kassiane.four.all.product.register.dto.ProductDTO;

public class ProductEdittionView {

    public static final int IMAGE_HEIGHT = 200;

    private static final String TITLE = "Edição de Produto";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 350;

    private final JButton confirmButton = new JButton("Confirmar");
    private final JButton cancelButton = new JButton("Cancelar");
    private final JLabel productNameLabel = new JLabel("Nome do Produto");
    private final JLabel priceLabel = new JLabel("Preço");
    private final JLabel imageLabel = new JLabel("Imagem");
    private final JTextField productNameTextField = new JTextField();
    private final JTextField productPriceTextField = new JTextField();
    private final JLabel imageIconJLabel = new JLabel();
    private final JDialog jDialog = new JDialog();
    private final ImageIcon defaultImage;

    private ProductDTO productModel;

    public ProductEdittionView(final ImageIcon defaultImage) {
        this.defaultImage = defaultImage;
        this.productModel = this.clearProductDTO();
        this.setFields(this.productModel);

        this.jDialog.setSize(WIDTH, HEIGHT);
        this.jDialog.setTitle(TITLE);
        this.jDialog.setLocationRelativeTo(null);
        this.jDialog.setModalityType(ModalityType.APPLICATION_MODAL);
        this.addProductEdditionComponents();
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
                        groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.productNameLabel)
                                .addComponent(this.productNameTextField))
                .addGroup(
                        groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.priceLabel)
                                .addComponent(this.productPriceTextField))
                .addGroup(
                        groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.imageLabel)
                                .addComponent(this.imageIconJLabel))
                .addGroup(
                        groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.confirmButton)
                                .addComponent(this.cancelButton));

        groupLayout.setVerticalGroup(sequentialGroup);
    }

    private void setHorizontalGroup(final GroupLayout groupLayout) {
        final SequentialGroup sequentialGroup = groupLayout.createSequentialGroup();
        sequentialGroup.addGroup(
                groupLayout.createParallelGroup().addComponent(this.productNameLabel).addComponent(this.priceLabel)
                        .addComponent(this.imageLabel).addComponent(this.confirmButton)).addGroup(
                groupLayout.createParallelGroup().addComponent(this.productNameTextField)
                        .addComponent(this.productPriceTextField).addComponent(this.imageIconJLabel)
                        .addComponent(this.cancelButton));

        groupLayout.setHorizontalGroup(sequentialGroup);
    }

    public JButton getCancelButton() {
        return this.cancelButton;
    }

    public JDialog getJDialog() {
        return this.jDialog;
    }

    public JLabel getImageIconJLabel() {
        return this.imageIconJLabel;
    }

    public JButton getConfirmButton() {
        return this.confirmButton;
    }

    public JTextField getProductNameTextField() {
        return this.productNameTextField;
    }

    public JTextField getProductPriceTextField() {
        return this.productPriceTextField;
    }

    public ImageIcon getDefaultImage() {
        return this.defaultImage;
    }

    public ProductDTO getProductModel() {
        return this.productModel;
    }

    public void setProductModel(final ProductDTO productDTO) {
        this.productModel = productDTO;
    }

    public void setVisible(final boolean visible) {
        this.jDialog.setVisible(visible);
    }

    public void setFields(final ProductDTO productModel) {
        this.productNameTextField.setText(productModel.getProductName());
        this.productPriceTextField.setText(productModel.getProductPrice());
        this.imageIconJLabel.setIcon(productModel.getImageIcon());
    }

    public ProductDTO clearProductDTO() {
        this.productModel = new ProductDTO("", "", this.defaultImage, null);
        this.setFields(this.productModel);
        return this.productModel;
    }

}
