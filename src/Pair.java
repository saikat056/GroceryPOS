/**
 * Created by IntelliJ IDEA.
 * User: saikat
 * Date: 3/16/12
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Pair<A,B>{
    // First element of type A
    private A first;

    // Second element of type B
    private B second;

    // Constructor
    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    // Get first element
    public A getFirst() {
        return first;
    }

    // Set first element
    public void setFirst(A first) {
        this.first = first;
    }

    // Get second element
    public B getSecond() {
        return second;
    }

    // Set second element
    public void setSecond(B second) {
        this.second = second;
    }


}
