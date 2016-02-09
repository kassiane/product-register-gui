package com.kassiane.four.all.product.register.formatter;

import java.text.NumberFormat;
import java.util.Locale;

public class PriceFormatter {

    private static final Locale pt_BR = new Locale("pt", "BR");
    private final NumberFormat numberFormat = NumberFormat.getNumberInstance(pt_BR);

    public PriceFormatter() {
        // TODO Auto-generated constructor stub
    }

    public String formatPrice(final String priceGiven) {
        final float price = Float.valueOf(priceGiven);
        return this.numberFormat.format(price);
    }

    public String formatPrice(final float priceGiven) {
        return this.numberFormat.format(priceGiven);
    }
}
