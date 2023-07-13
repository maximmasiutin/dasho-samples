package dasho.jep429;

import jdk.incubator.concurrent.ScopedValue;

public class jep429_ScopeValue {

    public void sum() {
        var result = jep429_ScopeValue.scopevalue.get();
     System.out.println("Sum = " + (result + 30));
    }
    //Define scope value
    public final static ScopedValue<Integer> scopevalue = ScopedValue.newInstance();
    public void Calculator(){
        //Bind the scope value value "20"
        ScopedValue.where(scopevalue, 20)
                .run(() -> {
                  sum();
                });
    }
}
