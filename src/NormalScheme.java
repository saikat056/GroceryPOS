/**
 * Created by IntelliJ IDEA.
 * User: saikat
 * Date: 3/15/12
 * Time: 9:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class NormalScheme extends Scheme{
    public NormalScheme(){
    }

    protected Pair<String,Double> handler(double netUnitPrice, double totalAmount, double totalPrice, String receipt, String[] catalogValues){
        netUnitPrice = twoDecimalsFormat(netUnitPrice);
        totalPrice += netUnitPrice*totalAmount;
        receipt +=printInReceiptFormat(catalogValues[ITEM_NAME], totalAmount+" "+catalogValues[ITEM_UNIT], ""+twoDecimalsFormat(totalPrice));

        if(next != null)
            return next.handler(netUnitPrice, totalAmount, totalPrice, receipt, catalogValues);
        else{
            receipt += printNetPrice(totalPrice);
            return new Pair<String, Double>(receipt, new Double(totalPrice));
        }
    }

}
