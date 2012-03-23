import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: saikat
 * Date: 3/15/12
 * Time: 8:22 PM
 * To change this template use File | Settings | File Templates.
 */

public class Grocery extends GlobalDataStore{

    // Input file for catalog
    private String inCatalogFile;

    // Input file for purchased items list
    private String inPurchasedItemFile;

    // Output file for receipt
    private String receiptFile;

    // Constructor
    // @param in1 input file of catalog
    // @param in2 input file for list of purchased items
    // @param out output file to break-down of purchased items
    public Grocery(String inCatalogFile , String inPurchasedItemFile, String receiptFile){
        this.inCatalogFile = inCatalogFile;
        this.inPurchasedItemFile = inPurchasedItemFile;
        this.receiptFile = receiptFile;
    }

    // Read catalog file
    // @param input catalog file
    // returns a catalog hash-table
    public Hashtable<String, String[]> readCatalog(String input){
        // Buffer used to read price catalogs
        BufferedReader catReader = null;
        Hashtable<String, String[]> catalog = new Hashtable<String, String[]>();

        try{
            catReader = new BufferedReader(new FileReader(input));
            String line = null;

            // Read price catalog from input file to a catalog hash-table
            while ((line = catReader.readLine()) != null) {
                String[] values = line.split(",");
                if(values.length != META_DATA_SIZE + NUM_ATTR_CATALOG){
                    throw new Exception("Invalid catalog file: Number of columns per line are not equal to configured value.");
                }
                catalog.put(values[0],values);
            }

            // Close the file buffers
            catReader.close();
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return catalog;
    }

    // Read purchased item file
    // @param input purchased item file
    // returns an array-list of purchased item information
    public ArrayList<String []> readPurchasedItemList(String input){
        // Buffer used to read list of purchased items
        BufferedReader lpiReader = null;
        ArrayList<String[]> list = new ArrayList<String[]>();

        try{
            lpiReader = new BufferedReader(new FileReader(input));
            String line = null;

            // Read price catalog from .cvs file to a catalog hash-table
            while ((line = lpiReader.readLine()) != null) {
                String[] values = line.split(",");
                if(values.length != NUM_ATTR_PURCHASE){
                    throw new Exception("Invalid purchased Item list file: Number of columns per line are not equal to configured value.");
                }
                list.add(values);
            }

            // Close the file buffers
            lpiReader.close();
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return list;

    }

    public void printReceipt(ArrayList<String> receiptStrings, double total){
        // Print the receipt
        try{
            // Open output file, i.e., receipt
            BufferedWriter outBuffer = new BufferedWriter(new FileWriter(receiptFile));

            // Print the receipt
            int size = receiptStrings.size();
            for(int i = 0; i < size; i++){
                outBuffer.write(receiptStrings.get(i));
                outBuffer.write("\n");
            }
            outBuffer.write("====================================================");
            outBuffer.write("\n");
            outBuffer.write(String.format(printInReceiptFormat("Total"," ",""+total)));

            //Close the output stream
            outBuffer.close();
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public boolean checkCompetency(String[] item, String[] catalogValues){
        boolean ret = true;
        try{
            if(catalogValues == null){
                ret = false;
                throw new Exception("Item is not found in the catalog");
            }
            
            if(Double.valueOf(item[PURCHASED_ITEM_AMOUNT].trim()).doubleValue() < 0){
                ret = false;
                throw new NegativeValueException("Item "+item[PURCHASED_ITEM_ID]+" is purchased as negative amount");
            }

            if(Integer.valueOf(catalogValues[ITEM_PRICE].trim()).intValue() < 0){
                ret = false;
                throw new NegativeValueException("Item "+catalogValues[ITEM_NAME]+" has a negative unit price");
            }
            else if(catalogValues[ITEM_UNIT].equals("unit")){
                if(!isInteger(Double.valueOf(item[PURCHASED_ITEM_AMOUNT].trim()).doubleValue())){
                    ret = false;
                    throw new UnitException(catalogValues[ITEM_NAME]);
                }
            }

            if(Integer.valueOf(catalogValues[ITEM_DISCOUNT].trim()).intValue() < 0){
                ret = false;
                throw new NegativeValueException("Item "+catalogValues[ITEM_NAME]+" has a negative discount");
            }

            if(Integer.valueOf(catalogValues[ITEM_GET_ONE_FREE].trim()).intValue() < 0){
                ret = false;
                throw new NegativeValueException("Item "+catalogValues[ITEM_NAME]+" has a negative get-one-free attribute");
            }

        }
        catch(NegativeValueException ne){
            System.err.println("Error: " + ne.getMessage());
        }
        catch(UnitException ue){
            System.err.println("Error: " + ue.getMessage());
        }
        catch(Exception ex){
            System.err.println("Error: " + ex.getMessage());
        }

        return ret;
    }

    public double performAllCalculations(Scheme scheme){
        // Read catalog and purchased item list files
        Hashtable<String, String[]> catalog =  readCatalog(inCatalogFile);
        ArrayList<String[]> purchasedItems = readPurchasedItemList(inPurchasedItemFile);

        // Calculate the price of purchased goods
        int size = purchasedItems.size();
        double total = 0.0;
        ArrayList<String> receiptStrings = new ArrayList<String>();


        for(int i = 0; i < size; i++){
            String[] item = purchasedItems.get(i);
            String[] catalogValues = catalog.get(item[0]);
            if(checkCompetency(item,catalogValues)){
                Pair<String, Double> calValue = scheme.handler(Double.valueOf(catalogValues[ITEM_PRICE].trim()).doubleValue(),
                        Double.valueOf(item[PURCHASED_ITEM_AMOUNT].trim()).doubleValue(), 0,"", catalogValues);
                receiptStrings.add(calValue.getFirst());
                total += calValue.getSecond().doubleValue();
            }
        }

        total = twoDecimalsFormat(total);
        // Print the receipt
        printReceipt(receiptStrings, total);

        return total;
    }

    public void run()
    {
        // Build the chain of responsibility
        Scheme normalScheme = new NormalScheme();
        normalScheme.add(new DiscountScheme());
        normalScheme.add(new GetOneFreeScheme());

        // Perform price calculation of all purchased goods
        performAllCalculations(normalScheme);
    }

    public static void main(String [] args)
    {
        Grocery gc;
        if(args.length == 3){
            gc = new Grocery(args[0],args[1],args[2]);
        }
        else{
            // Instantiate grocery object
            // @param1 - number of columns in catalog file
            // @param2 - number of columns in purchased-list file
            gc = new Grocery("input/catalog.csv","input/purchasedItems.csv","output/receipt.txt");
        }

        // Calculate the cost of purchased items using
        // different price schemes
        gc.run();
    }
}
