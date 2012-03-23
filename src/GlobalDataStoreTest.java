/**
* Created by IntelliJ IDEA.
* User: saikat
* Date: 3/16/12
* Time: 11:36 PM
* To change this template use File | Settings | File Templates.
*/
public class GlobalDataStoreTest {
    GlobalDataStore gs = new GlobalDataStore();
    @org.junit.Test
    public void testPrintInReceiptFormat() throws Exception {
        String first ="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh";
        String second = "jn  jnjsn@@334####";
        String third = "&&&%$#@!~)(*&^^%%$+_+_+";
        String result = String.format("%-10s  %-20s  %-20s\n",first,second,third);
        assert(result.equals(gs.printInReceiptFormat(first,second,third)));
    }

    @org.junit.Test
    public void testTwoDecimalsFormat() throws Exception {
        double d1 = 2.33;
        double d2 = 2.333;
        double d3 = 2.34;
        assert (d1 == gs.twoDecimalsFormat(2.3333333));
        assert (d2 != gs.twoDecimalsFormat(2.3333333));
        assert (d3 == gs.twoDecimalsFormat(2.3393333));

    }

    @org.junit.Test
    public void testIsInteger() throws Exception {
        assert (gs.isInteger(5) == true);
        assert (gs.isInteger(6.5) == false);
        assert (gs.isInteger(-3)== true);
        assert (gs.isInteger(-4.5) == false);
        assert (gs.isInteger((double)22/7) == false);
    }
}
