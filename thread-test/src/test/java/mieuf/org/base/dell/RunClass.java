package mieuf.org.base.dell;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Date 2020/8/7 14:12
 * @Created by zhangbing
 * @Description TODO
 */

public class RunClass implements Runnable {
    private volatile int state;

    private static final long stateOffset;

    private static Unsafe unsafe = reflectGetUnsafe();

    static {
        try {
            stateOffset = unsafe.objectFieldOffset
                    (RunClass.class.getDeclaredField("state"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    public RunClass(int state){
//        this.state = state;
    }

    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " try swap start " + state + " " + stateOffset);
        System.out.println(Thread.currentThread().getName() + " " + unsafe.getInt(this,stateOffset));
        boolean b = unsafe.compareAndSwapInt(this, stateOffset, 0, 10);
        System.out.println(Thread.currentThread().getName() + " " + unsafe.getInt(this,stateOffset));
        System.out.println(Thread.currentThread().getName() + " " + b + " try swap end " + state);
    }
}
