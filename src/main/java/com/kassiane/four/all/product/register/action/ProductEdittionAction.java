package com.kassiane.four.all.product.register.action;

import java.awt.Image;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.springframework.dao.DataAccessException;

import com.kassiane.four.all.product.register.domain.Product;
import com.kassiane.four.all.product.register.image.util.ImageIconResizer;
import com.kassiane.four.all.product.register.screen.data.checker.ProductEdittionDataChecker;
import com.kassiane.four.all.product.register.service.ProductService;
import com.kassiane.four.all.product.register.view.ProductEdittion;

public class ProductEdittionAction {

    private final ProductEdittion productEdittion;

    public ProductEdittionAction(final ProductEdittion productEdittion) {
        this.productEdittion = productEdittion;
    }

    public void chooseNewImageIcon() {
        final FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        final int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.productEdittion.setImageFile(fileChooser.getSelectedFile());
            try {
                final Image image = ImageIO.read(this.productEdittion.getImageFile());
                final ImageIconResizer imageIconResizer = new ImageIconResizer();
                final Icon imageIcon = imageIconResizer.imageToImageIconResized(image, ProductEdittion.getImageHeight());
                this.productEdittion.getImageIconJLabel().setIcon(imageIcon);
            } catch (final IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    public Product addNewProduct() {
        final int id = 0;
        final String name = this.productEdittion.getProductNameTextArea().getText();
        final String priceString = this.productEdittion.getProductPriceTextArea().getText();

        final ProductService productRegister = new ProductService(this.productEdittion.getProductDAO());
        final ProductEdittionDataChecker productEdittionDataChecker = new ProductEdittionDataChecker(name, priceString);
        Product product = null;
        try {
            final float price = productEdittionDataChecker.checkPrice();
            product = new Product(id, name, price, (ImageIcon) this.productEdittion.getImageIconJLabel().getIcon());
            productRegister.addProduct(product, this.productEdittion.getImageFile());
            this.productEdittion.setNewProduct(product);
            System.out.println(product.toString());

        } catch (DataAccessException | SQLException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this.productEdittion.getjDialog(), e.getMessage(), "Formato inválido",
                    JOptionPane.ERROR_MESSAGE);
        }
        return product;
    }

}
