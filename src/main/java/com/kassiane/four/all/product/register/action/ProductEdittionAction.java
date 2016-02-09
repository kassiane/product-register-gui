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
        final int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            final String productName = this.productEdittionView.getProductModel().getProductName();
            final String productPrice = this.productEdittionView.getProductModel().getProductPrice();
            final File imageFile = fileChooser.getSelectedFile();
            ImageIcon imageIcon = this.productEdittionView.getProductModel().getImageIcon();

            try {
                final Image image = ImageIO.read(imageFile);
                final ImageIconResizer imageIconResizer = new ImageIconResizer();
                imageIcon = imageIconResizer.imageToImageIconResized(image, ProductEdittionView.IMAGE_HEIGHT);
            } catch (final IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            final ProductDTO productModel = new ProductDTO(productName, productPrice, imageIcon, imageFile);
            this.productEdittionView.setProductModel(productModel);
            this.productEdittionView.setFields(productModel);
        }
    }

    public void addNewProduct(final ProductDTO productDTO) {
        final ProductDTODataChecker productDTODataChecker = new ProductDTODataChecker(productDTO);

        try {
            final ProductDTO checkedProductDTO = productDTODataChecker.checkedProductDTO();
            this.productEdittionView.setProductModel(checkedProductDTO);

            final ProductDTOToProductMapper productDTOToProductMapper = new ProductDTOToProductMapper();
            final Product product = productDTOToProductMapper.mapToProduct(checkedProductDTO);
            this.productService.addProduct(product, this.productEdittionView.getProductModel().getImageFile());

            this.productEdittionView.getConfirmButton().firePropertyChange(ADD_NEW_PRODUCT, false, true);
            this.productEdittionView.getJDialog().dispose();
            this.productEdittionView.clearProductDTO();
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(this.productEdittionView.getJDialog(), e.getMessage(), "Erro!",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (final IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this.productEdittionView.getJDialog(), e.getMessage(), "Erro!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
