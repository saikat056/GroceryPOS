/**
 * Created by IntelliJ IDEA.
 * User: saikat
 * Date: 3/15/12
 * Time: 8:31 PM
 * To change this template use File | Settings | File Templates.
 */
abstract class Scheme extends GlobalDataStore{
    // Next scheme in the chain
    protected Scheme next;

    // Add next scheme to the price scheme chain
    public void add(Scheme n){
        if(next != null){
            next.add(n);
        }
        else{
            next = n;
        }
    }

    public String printNetPrice(double totalPrice){
        String receipt = "";
        totalPrice = twoDecimalsFormat(totalPrice);
        receipt +=printInReceiptFormat("","","------------");
        receipt +=printInReceiptFormat("","",""+totalPrice);
        return receipt;
    }

    // Apply current price scheme and hand over the calculated value to the next
    // price scheme
    // @param netUnitPrice unit price calculated so far in the chain of schemes
    // @param totalAmount amount of items purchased
    // @param receipt receipt string formed so far
    // @param catalogValue catalog information of a purchased item
    // returns a pair of receipt and net price
    abstract protected Pair<String,Double> handler(double netUnitPrice, double totalAmount, double totalPrice, String receipt, String[] catalogValues);
}
