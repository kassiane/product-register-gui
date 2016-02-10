package com.kassiane.four.all.product.register.listener;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.kassiane.four.all.product.register.dto.ProductDTO;
import com.kassiane.four.all.product.register.mapper.ProductDTOToProductMapper;
import com.kassiane.four.all.product.register.screen.data.checker.ProductDTODataChecker;
import com.kassiane.four.all.product.register.service.ProductService;
import com.kassiane.four.all.product.register.service.domain.Product;
import com.kassiane.four.all.product.register.view.ProductRegisterView;

public class ProductTableListener implements TableModelListener {

    private final ProductRegisterView productRegisterView;
    private final ProductService productService;

    public ProductTableListener(final ProductRegisterView productRegisterView, final ProductService productService) {
        this.productRegisterView = productRegisterView;
        this.productService = productService;
    }

    @Override
    public void tableChanged(final TableModelEvent e) {
        final int selectedRow = e.getFirstRow();

        final long productSelectedId = (long) this.productRegisterView.getProductsTable().getModel().getValueAt(selectedRow, 3);

        final String name = (String) this.productRegisterView.getProductsTable().getModel().getValueAt(selectedRow, 1);
        final String price = (String) this.productRegisterView.getProductsTable().getModel().getValueAt(selectedRow, 2);

        final ProductDTO productToUpdate = new ProductDTO(productSelectedId, name, price, null, null);
        final ProductDTODataChecker productDataChecker = new ProductDTODataChecker(productToUpdate);
        try {
            productDataChecker.checkProduct();
        } catch (final IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(this.productRegisterView.getProductsTable(), exception.getMessage(), "Erro!",
                    JOptionPane.ERROR_MESSAGE);
        }

        final ProductDTOToProductMapper productDTOToProductMapper = new ProductDTOToProductMapper();
        final Product newProduct = productDTOToProductMapper.mapToProduct(productToUpdate);

        this.productService.updateProduct(newProduct);
    }

}
