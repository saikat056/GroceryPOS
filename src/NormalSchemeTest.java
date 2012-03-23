import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: saikat
 * Date: 3/17/12
 * Time: 12:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class NormalSchemeTest {
    public NormalScheme normalScheme = new NormalScheme();
    public NormalSchemeTest(){
        normalScheme.add(null);
    }

    @Test
    public void testHandler() throws Exception {
        String[] catalogValues = {"0","Pen","unit","5","10","2"};
        Pair<String,Double> result = normalScheme.handler(4,34,34,"",catalogValues);
        assert(result.getSecond().doubleValue() == (4*34)+34);

        result = normalScheme.handler(4,34,-34,"",catalogValues);
        assert(result.getSecond().doubleValue() == (4*34)-34);

        result = normalScheme.handler(4.5,-2,-34,"",catalogValues);
        assert(result.getSecond().doubleValue() == (4.5*(-2))-34);

    }
}
