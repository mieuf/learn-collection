package mieuf.org.base.dell.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/4/25.
 * TODO -----
 */
public class MyThread {
    static Thread thread1 = new Thread(){
        @Override
        public void run(){
            System.out.println("thread 1 ...");
        }
    };

    static Thread thread2 = new Thread(){
        @Override
        public void run(){
            System.out.println("thread 2 ...");
        }
    };

    static Thread thread3 = new Thread(){
        @Override
        public void run(){
            System.out.println("thread 3 ...");
        }
    };
    //FIFO队列 先进先出
    static ExecutorService service = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws InterruptedException {

        System.out.println("hello world");
        thread1.start();
//        // join()主线程等子线程执行完，再执行
        thread1.join();
//        thread2.start();
//        thread2.join();
//        thread3.start();
//        thread3.join();
        service.submit(thread1);
        service.submit(thread2);
        service.submit(thread3);
        service.shutdown();
    }
}
