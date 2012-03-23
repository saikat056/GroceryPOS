import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;


/**
 * Created by IntelliJ IDEA.
 * User: saikat
 * Date: 3/16/12
 * Time: 2:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class GetOneFreeScheme extends Scheme{
    private static Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();

    // constructor
    public GetOneFreeScheme(){
    }

    // Update the hash table that records
    // already purchased items
    public int updateTable(String id, int numToBePurchasedItems, int numBuyItems){
        int numAlreadyPurchasedItems = 0;
        if(hashtable.contains(id)){
            numAlreadyPurchasedItems = hashtable.get(id).intValue();
        }

        int numFreeItems = 0;
        if(numBuyItems > 1)
        {
            for(int i = 0; i < numToBePurchasedItems; i++){
                numAlreadyPurchasedItems = (numAlreadyPurchasedItems + 1)%(numBuyItems+1);
                if(numAlreadyPurchasedItems == 0){
                    numFreeItems++;
                }
            }
        }
        hashtable.put(id, new Integer(numAlreadyPurchasedItems));
        return numFreeItems;
    }

    // Process whether an item should be considered for
    // Buy N, Get 1 free scheme
    protected Pair<String,Double> handler(double netUnitPrice, double totalAmount, double totalPrice, String receipt, String[] catalogValues){
        if(catalogValues[ITEM_UNIT].equals("unit")){
            int numFreeItems = updateTable(catalogValues[ITEM_ID],(int) totalAmount,
                    Integer.valueOf(catalogValues[ITEM_GET_ONE_FREE].trim()).intValue());

            if(numFreeItems > 0){
                double previousPrice = totalPrice;
                totalPrice -= numFreeItems*netUnitPrice;
                receipt += printInReceiptFormat("", "Buy 'N' Get 1 Free",
                        ""+twoDecimalsFormat(totalPrice - previousPrice));
            }
        }

        if(next != null)
            return next.handler(netUnitPrice, totalAmount, totalPrice, receipt,catalogValues);
        else{
            receipt += printNetPrice(totalPrice);
            return new Pair<String, Double>(receipt,new Double(totalPrice));
        }
    }
}
