import org.junit.Test;

/**
* Created by IntelliJ IDEA.
* User: saikat
* Date: 3/17/12
* Time: 2:25 AM
* To change this template use File | Settings | File Templates.
*/
public class PairTest {
    private Pair<String, Double> p1 = new Pair<String, Double>("str1",new Double(34.6));
    private Pair<Integer, Double> p2 = new Pair<Integer, Double>(new Integer(3),new Double(-45.7));
    
    @Test
    public void testGetFirst() throws Exception {
        assert(p1.getFirst().equals("str1"));
        assert(p2.getFirst().intValue() == 3);
    }

    @Test
    public void testGetSecond() throws Exception {
        assert(p1.getSecond().doubleValue() == 34.6);
        assert(p2.getSecond().doubleValue() == -45.7);

    }
}
