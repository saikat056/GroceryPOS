import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: saikat
 * Date: 3/17/12
 * Time: 12:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class DiscountSchemeTest {
    public DiscountScheme discountScheme = new DiscountScheme();
    public DiscountSchemeTest(){
        discountScheme.add(null);
    }

    @Test
    public void testHandler() throws Exception {
        String[] catalogValues = {"0","Pen","unit","5","10","2"};
        Pair<String,Double> result = discountScheme.handler(4,34,34,"",catalogValues);
        assert(result.getSecond().doubleValue() == 34*0.9);

        result = discountScheme.handler(4,34,-34,"",catalogValues);
        assert(result.getSecond().doubleValue() == (-34)*0.9);

        String[] catalogValues1 = {"0","Oil","pound","5","-30","2"};
        result = discountScheme.handler(4.5,-2,333,"",catalogValues1);
        assert(result.getSecond().doubleValue() == 333*(1.3));

    }
}
