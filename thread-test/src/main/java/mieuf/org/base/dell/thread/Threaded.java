package mieuf.org.base.dell.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/4/28.
 * TODO -----
 */
public class Threaded {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                return null;
            }
        });
        executorService.execute(new Runnable(){

            @Override
            public void run() {

            }
        });
    }
}
