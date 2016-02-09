package com.kassiane.four.all.product.register.screen.data.checker;

import com.kassiane.four.all.product.register.dto.ProductDTO;
import com.kassiane.four.all.product.register.formatter.PriceFormatter;

public class ProductDTODataChecker {

    public static final String INFORM_PRICE = "Informe o preço do produto.";
    public static final String INFORM_VALID_PRICE = "Informe um preço válido.";
    private final ProductDTO productModel;

    public ProductDTODataChecker(final ProductDTO productModel) {
        this.productModel = productModel;
    }

    private boolean checkName() {
        if (this.productModel.getProductName().equals(""))
            throw new IllegalArgumentException("Informe o nome do produto.");
        return true;
    }

    private boolean checkPrice() {
        if (this.productModel.getProductPrice().equals(""))
            throw new IllegalArgumentException(INFORM_PRICE);

        try {
            Float.valueOf(this.productModel.getProductPrice());
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(INFORM_VALID_PRICE, e);
        }

        if (String.valueOf(this.productModel.getProductPrice()).replace('.', '0').split("0").length == 0)
            throw new IllegalArgumentException(INFORM_PRICE);

        if (String.valueOf(this.productModel.getProductPrice()).replace(',', '0').split("0").length == 0)
            throw new IllegalArgumentException(INFORM_PRICE);

        return true;
    }

    public ProductDTO checkedProductDTO() {
        if (this.checkName() && this.checkPrice()) {
            final PriceFormatter priceFormatter = new PriceFormatter();
            final String priceString = priceFormatter.formatPrice(this.productModel.getProductPrice());
            return new ProductDTO(this.productModel.getProductName(), priceString, this.productModel.getImageIcon(),
                    this.productModel.getImageFile());
        }
        return this.productModel;
    }
}
