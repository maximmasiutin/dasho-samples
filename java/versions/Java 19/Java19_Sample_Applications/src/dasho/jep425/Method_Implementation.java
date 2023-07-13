package dasho.jep425;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

public class Method_Implementation {

    // Using ofVirtual method not started to create a thread and use of start and join method
    public void jep425OfVirtual() throws InterruptedException {

        var virtualThread = Thread.ofVirtual().unstarted(() ->
        System.out.println("Current Thread= "+ Thread.currentThread()+" Thread created by Virtual method"));
        System.out.println("Current Thread id = " + Thread.currentThread().threadId());
        virtualThread.start();
        virtualThread.join();


    }

    // Using startVirtualThread and isVirtual method  create a thread
    // Check thread is virtual or not using isVirtual method
    public void jep425_StartVirtualThread() {
        Runnable runnable = () ->
                System.out.println("Current thread is virtual= " + Thread.currentThread().isVirtual());

        Thread.startVirtualThread(runnable);


    }

    // Using Executors method create a thread
    // It is working in the thread-per-request style
    public void executors() {

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            System.out.println("Task created by Executors Method of thread");
            IntStream.range(0, 5).forEach(task -> {
                executor.submit(() -> {
                    try {
                        Thread.sleep(Duration.ofSeconds(1));
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }

                    System.out.println("Task = " + task);
                });
            });
        }
    }

    //Thread Builder
    public void threadBuilder() {
        Runnable runnable = () -> System.out.println("Thread created by using Thread Builder method");

        Thread.Builder builder = Thread.ofVirtual();
        builder.start(runnable);
    }

    //Thread Builder factory
    public void threadBuilderFactory() {
        Runnable task = () -> System.out.println("Thread created by using Thread Builder Factory method");
        ThreadFactory factory = Thread.ofVirtual().factory();
        Thread virtualThreadFromAFactory = factory.newThread(task);
        virtualThreadFromAFactory.start();
    }

    // thread Group
    public void threadgroup(){
        ThreadGroup tgroup = new ThreadGroup("Group A");
        Runnable runnable = () -> System.out.println("Current thread is virtual= " + Thread.currentThread().isVirtual());

        Thread.startVirtualThread(runnable);
        System.out.println("Thread Group Parent = "+tgroup.getParent());
        System.out.println("Thread Group Name = "+tgroup.getName());

    }

    // Using Closeable
    public void Closeable() {
        try (CloseableExecutorService service = new CloseableExecutorService(Executors.newSingleThreadExecutor())) {

            service.execute(() -> System.out.println("Parent Task 1"));
            service.execute(() -> {
                for (int i = 0; i < 3; i++) {
                    System.out.println("Task: " + i);
                }
            });
            service.execute(() -> System.out.println("Parent Task 2"));
        }
    }

    // Getting data from url using thread
     public static void handleUrlVirtualThreads() {
        int nThreads = 2;
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, nThreads).forEach(i -> executor.submit(() -> {
                try {
                    var url = new URL("https://openjdk.org/jeps/436");
                    try (var in = url.openStream()) {
                       System.out.println( new String(in.readNBytes(109), StandardCharsets.UTF_8));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }));
        }
    }

}


