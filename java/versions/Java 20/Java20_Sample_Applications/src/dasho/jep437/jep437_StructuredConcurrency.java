package dasho.jep437;
import jdk.incubator.concurrent.ScopedValue;
import jdk.incubator.concurrent.StructuredTaskScope;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class jep437_StructuredConcurrency {
    public final static ScopedValue<Integer> scopevalue = ScopedValue.newInstance();
    public static void test(){
        jep437_StructuredConcurrency scopevalued = new jep437_StructuredConcurrency();
        ScopedValue.where(scopevalue, 20).run(() -> {
            try {
                System.out.println(scopevalued.handleStructure());
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public handle handleStructure() throws ExecutionException, InterruptedException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<Integer> user = scope.fork(this::findAccount);
            Future<Integer> order = scope.fork(this::fetchOrder);

            scope.join();           // Join both forks and wait for all the tasks to be completed
            scope.throwIfFailed();  // it there are errors in the single subtask then this line will get executed
            //and every other incomplete tasks will get cancelled

            // Here, both forks have succeeded, so compose their results
            System.out.println("Done Compiling and code is running fine");
            return new handle(user.resultNow(), order.resultNow());
        }
    }
    private int findAccount(){
        return 30;
    }
    private int fetchOrder(){
        return 40;
    }
    record handle(int user, int order){
    }
}
