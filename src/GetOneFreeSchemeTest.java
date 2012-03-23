import org.junit.Test;

/**
* Created by IntelliJ IDEA.
* User: saikat
* Date: 3/17/12
* Time: 12:28 AM
* To change this template use File | Settings | File Templates.
*/
public class GetOneFreeSchemeTest {
    private GetOneFreeScheme getOneFreeScheme = new GetOneFreeScheme();
    public GetOneFreeSchemeTest(){
        getOneFreeScheme.add(null);
    }

    @Test
    public void testUpdateTable() throws Exception {
        assert(getOneFreeScheme.updateTable("1",6,2) == 2);
        assert(getOneFreeScheme.updateTable("1",7,2) == 2);
        assert(getOneFreeScheme.updateTable("1",10,2) == 3);
        assert(getOneFreeScheme.updateTable("1",-10,2) == 0);

        assert(getOneFreeScheme.updateTable("1",0,2) == 0);
        assert(getOneFreeScheme.updateTable("1",20,5) == 3);

    }
}
