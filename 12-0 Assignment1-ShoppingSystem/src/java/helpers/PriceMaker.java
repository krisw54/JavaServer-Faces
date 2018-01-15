package helpers;

import java.text.DecimalFormat;

/**
 *
 * @author Kris
 */
public final class PriceMaker {

    public PriceMaker() {

    }

    public double setDoubleToCurrency(double num) {
        DecimalFormat df = new DecimalFormat("#.00");
        String theprice = df.format(num);
        return Double.parseDouble(theprice);
    }
}
