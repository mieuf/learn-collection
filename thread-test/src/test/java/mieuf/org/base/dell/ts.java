package mieuf.org.base.dell;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Date 2020/8/7 15:16
 * @Created by zhangbing
 * @Description TODO
 */

public class ts {
    private static int apple = 10;
    private int orange = 10;

    public static void main(String[] args) throws Exception {
        Unsafe unsafe = getUnsafeInstance();

        Field appleField = ts.class.getDeclaredField("apple");
        System.out.println("Location of Apple: " + unsafe.staticFieldOffset(appleField));  //apple在类实例中的偏移量

        Field orangeField = ts.class.getDeclaredField("orange");
        System.out.println("Location of Orange: " + unsafe.objectFieldOffset(orangeField));


        System.out.println(orangeField.getInt(new ts())); 
        System.out.println(appleField.getInt(new ts())); //apple在类实例中的值
    }

    private static Unsafe getUnsafeInstance() throws SecurityException, NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }

}
