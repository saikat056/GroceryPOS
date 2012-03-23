/**
 * Created by IntelliJ IDEA.
 * User: saikat
 * Date: 3/17/12
 * Time: 12:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class NegativeValueException extends Exception{
    private String msg;
    public NegativeValueException(String msg){
        super(msg);
        this.msg = msg;
    }

    public String getMessage(){
        return  msg;
    }
}
