package com.kassiane.four.all.product.register.screen.data.checker;

public class ProductEdittionDataChecker {

    private final String price;

    public ProductEdittionDataChecker(final String name, final String price) {
        this.price = price;
    }

    public boolean checkName() {
        return false;
    }

    public float checkPrice() {
        float priceFloat;
        try {
            priceFloat = Float.valueOf(this.price);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("O formato do preço informado é inválido.", e);
        }
        return priceFloat;
    }

}
