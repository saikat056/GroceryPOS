/**
 * Created by IntelliJ IDEA.
 * User: saikat
 * Date: 3/16/12
 * Time: 8:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class UnitException extends Exception{
    private String itemName;
    public UnitException(String itemName){
        super("Error: Amount must be an integer value for the item "+itemName);
        this.itemName = itemName;
    }

    public String getMessage(){
        return  "Amount must be an integer value for the item "+itemName;
    }
}
