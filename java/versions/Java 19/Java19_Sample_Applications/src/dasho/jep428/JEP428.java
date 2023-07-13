package dasho.jep428;
import java.util.concurrent.ExecutionException;
import jdk.incubator.concurrent.StructuredTaskScope;
import java.util.concurrent.Future;
public class JEP428 {
    public Response handleStructure() throws ExecutionException, InterruptedException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<String> user = scope.fork(this::findAccount);
            Future<String> order = scope.fork(this::fetchOrder);

            scope.join();           // Join both forks and wait for all the tasks to be completed
            scope.throwIfFailed();  // it there are errors in the single subtask then this line will get executed
                                    //and every other incomplete tasks will get cancelled

            // Here, both forks have succeeded, so compose their results
            return new Response(user.resultNow(), order.resultNow());
        }
    }
    private String findAccount(){
        return "Thread is completed successfully";
    }
    private String fetchOrder(){
        return "No errors found";
    }
    record Response(String user, String order) {
    }
}