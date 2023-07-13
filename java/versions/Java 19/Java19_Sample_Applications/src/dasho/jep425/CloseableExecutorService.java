package dasho.jep425;

import java.util.concurrent.ExecutorService;

@SuppressWarnings("checkstyle:LeftCurly")
public class CloseableExecutorService implements AutoCloseable {
    private ExecutorService service;

    public CloseableExecutorService(ExecutorService service) {
        super();
        this.service = service;
    }

    public void execute(Runnable runnable) {
        if (service == null) {
            return;
        }
        service.execute(runnable);
    }

    @Override
    public void close() {
        if (service != null) {
            service.shutdown();
        }
    }
}
