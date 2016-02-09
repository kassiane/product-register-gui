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

import org.springframework.dao.DataAccessException;

import com.kassiane.four.all.product.register.dto.ProductDTO;
import com.kassiane.four.all.product.register.image.util.ImageIconResizer;
import com.kassiane.four.all.product.register.screen.data.checker.ProductEdittionDataChecker;
import com.kassiane.four.all.product.register.service.ProductService;
import com.kassiane.four.all.product.register.service.domain.Product;
import com.kassiane.four.all.product.register.view.ProductEdittionView;

public class ProductEdittionAction {

    private final ProductEdittionView productEdittion;
    private final ProductService productService;

    public ProductEdittionAction(final ProductEdittionView productEdittion, final ProductService productService) {
        this.productEdittion = productEdittion;
        this.productService = productService;
    }

    public void chooseNewImageIcon() {
        final FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        final int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            final String productName = this.productEdittion.getProductModel().getProductName();
            final String productPrice = this.productEdittion.getProductModel().getProductPrice();
            final File imageFile = fileChooser.getSelectedFile();
            ImageIcon imageIcon = this.productEdittion.getProductModel().getImageIcon();

            try {
                final Image image = ImageIO.read(imageFile);
                final ImageIconResizer imageIconResizer = new ImageIconResizer();
                imageIcon = imageIconResizer.imageToImageIconResized(image, ProductEdittionView.IMAGE_HEIGHT);
            } catch (final IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            final ProductDTO productModel = new ProductDTO(productName, productPrice, imageIcon, imageFile);
            this.productEdittion.setProductModel(productModel);
            this.productEdittion.setFields(productModel);
        }
    }

    public void addNewProduct(final ProductDTO productDTO) {
        final String name = productDTO.getProductName();
        final String priceString = productDTO.getProductPrice();

        final ProductEdittionDataChecker productEdittionDataChecker = new ProductEdittionDataChecker(name, priceString);
        try {
            final float price = productEdittionDataChecker.checkPrice();
            final Product product = new Product(0, name, price, productDTO.getImageIcon());
            // product checker e alguem q retorne um new product
            this.productService.addProduct(product, this.productEdittion.getProductModel().getImageFile());

        } catch (DataAccessException | SQLException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this.productEdittion.getJDialog(), e.getMessage(), "Formato inválido",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
