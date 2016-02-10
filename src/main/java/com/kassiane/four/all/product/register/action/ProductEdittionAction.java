package com.kassiane.four.all.product.register.action;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.kassiane.four.all.product.register.dto.ProductDTO;
import com.kassiane.four.all.product.register.image.util.ImageIconResizer;
import com.kassiane.four.all.product.register.mapper.ProductDTOToProductMapper;
import com.kassiane.four.all.product.register.screen.data.checker.ProductDTODataChecker;
import com.kassiane.four.all.product.register.service.ProductService;
import com.kassiane.four.all.product.register.service.domain.Product;
import com.kassiane.four.all.product.register.view.ProductEdittionView;

public class ProductEdittionAction {

    private final ProductEdittionView productEdittionView;
    private final ProductService productService;
    public static final String ADD_NEW_PRODUCT = "Add new Product";

    public ProductEdittionAction(final ProductEdittionView productEdittion, final ProductService productService) {
        this.productEdittionView = productEdittion;
        this.productService = productService;
    }

    public void chooseNewImageIcon() {
        final FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        final int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            final long productId = this.productEdittionView.getProductModel().getProductId();
            final String productName = this.productEdittionView.getProductModel().getProductName();
            final String productPrice = this.productEdittionView.getProductModel().getProductPrice();
            final File imageFile = fileChooser.getSelectedFile();
            ImageIcon imageIcon = this.productEdittionView.getProductModel().getImageIcon();

            try {
                final Image image = ImageIO.read(imageFile);
                final ImageIconResizer imageIconResizer = new ImageIconResizer();
                imageIcon = imageIconResizer.imageToImageIconResized(image, ProductEdittionView.IMAGE_HEIGHT);
            } catch (final IOException e) {
                JOptionPane.showMessageDialog(this.productEdittionView.getJDialog(), "Erro processando a imagem.", "Erro!",
                        JOptionPane.ERROR_MESSAGE);
            }
            final ProductDTO productModel = new ProductDTO(productId, productName, productPrice, imageIcon, imageFile);
            this.productEdittionView.setProductModel(productModel);
            this.productEdittionView.setFields(productModel);
        }
    }

    public void addNewProduct(final ProductDTO productDTO) {
        final ProductDTODataChecker productDTODataChecker = new ProductDTODataChecker(productDTO);

        try {

            productDTODataChecker.checkProduct();

            final ProductDTOToProductMapper productDTOToProductMapper = new ProductDTOToProductMapper();
            final Product product = productDTOToProductMapper.mapToProduct(productDTO);

            final long productId = this.productService.addProduct(product, this.productEdittionView.getProductModel()
                    .getImageFile());
            final String productName = productDTO.getProductName();
            final String productPrice = productDTO.getProductPrice();
            final File imageFile = productDTO.getImageFile();
            final ImageIcon imageIcon = productDTO.getImageIcon();

            final ProductDTO newProduct = new ProductDTO(productId, productName, productPrice, imageIcon, imageFile);
            this.productEdittionView.setProductModel(newProduct);

            this.productEdittionView.getConfirmButton().firePropertyChange(ADD_NEW_PRODUCT, false, true);
            this.productEdittionView.getJDialog().dispose();
            this.productEdittionView.clearProductDTO();
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(this.productEdittionView.getJDialog(), e.getMessage(), "Erro!",
                    JOptionPane.ERROR_MESSAGE);
        } catch (final IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this.productEdittionView.getJDialog(), e.getMessage(), "Erro!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
