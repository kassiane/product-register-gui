package com.kassiane.four.all.product.register.dto;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import com.kassiane.four.all.product.register.image.util.ImageIconResizer;
import com.kassiane.four.all.product.register.service.domain.Product;
import com.kassiane.four.all.product.register.view.ProductRegisterView;

public class ProductTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;

    private static final String[] COLUMN_NAMES = { "Imagem", "Nome", "Preço", "ID" };

    public int imageHight;
    public ImageIcon defaultImage;

    public ProductTableModel(final int imageHight, final ImageIcon defaultImage) {
        super(COLUMN_NAMES, 0);
        this.imageHight = imageHight;
        this.defaultImage = defaultImage;
    }

    @Override
    public String getColumnName(final int column) {
        return ProductTableModel.COLUMN_NAMES[column];
    };

    @Override
    public Class<?> getColumnClass(final int columnIndex) {
        if (columnIndex == 0) {
            return ImageIcon.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(final int rowIndex, final int columnIndex) {
        if (columnIndex == 0)
            return false;
        return true;
    }

    public void addProductRow(final ProductDTO productDTO) {
        final ImageIconResizer imageIconResizer = new ImageIconResizer();
        final ImageIcon resized = imageIconResizer.resizeImageIcon(productDTO.getImageIcon(), ProductRegisterView.IMAGE_HEIGHT);
        this.addRow(new Object[] { resized, productDTO.getProductName(), productDTO.getProductPrice(), productDTO.getProductId() });
    }

    public void addProductListToTableModel(final List<Product> products) {

        if (products != null) {
            for (final Product product : products) {
                final long id = product.getId();
                final String name = product.getName();
                final String price = String.valueOf(product.getPrice());
                ImageIcon imageIcon = null;
                if (product.getIcon() != null) {
                    final ImageIconResizer imageIconResizer = new ImageIconResizer();
                    imageIcon = imageIconResizer.resizeImageIcon(product.getIcon(), ProductRegisterView.IMAGE_HEIGHT);
                } else {
                    imageIcon = this.defaultImage;
                }

                this.addRow(new Object[] { imageIcon, name, price, id });
            }
        }
    }
}
