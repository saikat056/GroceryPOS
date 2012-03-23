import java.text.DecimalFormat;

/**
 * Created by IntelliJ IDEA.
 * User: saikat
 * Date: 3/16/12
 * Time: 3:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class GlobalDataStore {
    // Number of metadata fields per line in catalog file
    public final int META_DATA_SIZE = 3;

    // Number of attributes per line in the catalog file
    public final int NUM_ATTR_CATALOG = 3;

    // Number of attributes per line in the purchased item list
    public final int NUM_ATTR_PURCHASE = 2;

    // Indexing in catalog file
    public final int ITEM_ID = 0;
    public final int ITEM_NAME = 1;
    public final int ITEM_UNIT =2;
    public final int ITEM_PRICE = 3;
    public final int ITEM_DISCOUNT = 4;
    public final int ITEM_GET_ONE_FREE = 5;
    
    // Indexing in purchased item list file
    public final int PURCHASED_ITEM_ID = 0;
    public final int PURCHASED_ITEM_AMOUNT = 1;

    // Print the data in receipt format
    public String printInReceiptFormat(String first, String second, String third){
        return String.format("%-10s  %-20s  %-20s\n",first,second,third);
    }

    // Produce the number in a two decimal place format
    public double twoDecimalsFormat(double d){
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    // Check whether the value can be converted to integer
    // without loss of information
    public boolean isInteger(double d){
        return ((d - (int)d) == 0.0);
    }

}
