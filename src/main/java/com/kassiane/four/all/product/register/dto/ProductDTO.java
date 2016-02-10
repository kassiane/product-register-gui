package com.kassiane.four.all.product.register.dto;

import java.io.File;

import javax.swing.ImageIcon;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class ProductDTO {

    private final long id;
    private final String productName;
    private final String productPrice;
    private final ImageIcon imageIcon;
    private final File imageFile;

    public ProductDTO(final long id, final String productName, final String productPrice, final ImageIcon imageIcon,
            final File imageFile) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.imageIcon = imageIcon;
        this.imageFile = imageFile;
    }

    public long getProductId() {
        return this.id;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getProductPrice() {
        return this.productPrice;
    }

    public ImageIcon getImageIcon() {
        return this.imageIcon;
    }

    public File getImageFile() {
        return this.imageFile;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", this.id).add("productName", this.productName)
                .add("productPrice", this.productPrice).add("imageIcon", this.imageIcon).add("imageFile", this.imageFile)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.productName, this.productPrice, this.imageIcon, this.imageFile);
    }

    @Override
    public boolean equals(final Object object) {
        if (object instanceof ProductDTO) {
            final ProductDTO that = (ProductDTO) object;
            return Objects.equal(this.id, that.id) && Objects.equal(this.productName, that.productName)
                    && Objects.equal(this.productPrice, that.productPrice) && Objects.equal(this.imageIcon, that.imageIcon)
                    && Objects.equal(this.imageFile, that.imageFile);
        }
        return false;
    }

}
