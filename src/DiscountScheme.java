/**
 * Created by IntelliJ IDEA.
 * User: saikat
 * Date: 3/15/12
 * Time: 9:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class DiscountScheme extends Scheme{
    public DiscountScheme(){
    }

    protected Pair<String,Double> handler(double netUnitPrice, double totalAmount, double totalPrice, String receipt, String[] catalogValues){
        double discount =  (1 - (Double.valueOf(catalogValues[ITEM_DISCOUNT].trim()).doubleValue())/100);
        discount = twoDecimalsFormat(discount);
        totalPrice = twoDecimalsFormat(totalPrice);
        double prePrice = totalPrice;

        netUnitPrice *= discount;
        totalPrice *= discount;

        receipt += printInReceiptFormat("", (100 - (discount*100))+"% discount",
                ""+twoDecimalsFormat(totalPrice - prePrice));

        if(next != null)
            return next.handler(netUnitPrice, totalAmount, totalPrice, receipt,catalogValues);
        else{
            receipt += printNetPrice(totalPrice);
            return new Pair<String, Double>(receipt,new Double(totalPrice));
        }
    }
}
