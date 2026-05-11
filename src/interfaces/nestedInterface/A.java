package interfaces.nestedInterface;

public class A {
    public interface NestedInterface{
        boolean isOdd(int num);//providing no implementation

    }
}
class B implements A.NestedInterface{

    @Override
    public boolean isOdd(int num) {
        return ((num&1)==1);
    }
}

//the nested interface can be declared like public private protected.s
//the top level interface has to be declared either public or default.