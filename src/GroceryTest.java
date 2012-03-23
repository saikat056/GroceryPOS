import org.junit.Test;

/**
* Created by IntelliJ IDEA.
* User: saikat
* Date: 3/17/12
* Time: 1:40 AM
* To change this template use File | Settings | File Templates.
*/
public class GroceryTest {
    private Grocery gc = new Grocery("test/catalog.csv","test/purchasedItems.csv","test/receipt.txt");
    @Test
    public void testReadCatalog() throws Exception {

    }

    @Test
    public void testReadPurchasedItemList() throws Exception {

    }

    @Test
    public void testCheckCompetency() throws Exception {
        String[] item = {"0","4"};
        String[] catalogValues = {"1","Beef","pound","5","5","1"};
        assert (gc.checkCompetency(item, catalogValues) == true);

        String[] item2 = {"0","4"};
        String[] catalogValues2 = {"1","Beef","pound","5","5","1"};
        assert (gc.checkCompetency(item2, catalogValues2) == true);

        String[] item3 = {"0","4"};
        String[] catalogValues3 = {"1","Pen","unit","3","5","1"};
        assert (gc.checkCompetency(item3, catalogValues3) == true);

    }

    @Test
    public void testPerformAllCalculations() throws Exception {
        Grocery testGC = new Grocery("test/testCatalog.csv",
                "test/testPurchasedItems.csv","test/testReceipt.txt");

        // Build the chain of responsibility
        Scheme normalScheme = new NormalScheme();
        assert(testGC.performAllCalculations(normalScheme) == 194);

        normalScheme.add(new DiscountScheme());
        assert(testGC.performAllCalculations(normalScheme) == 175.1);

        normalScheme.add(new GetOneFreeScheme());
        assert(testGC.performAllCalculations(normalScheme) == 152.6);

    }
}
