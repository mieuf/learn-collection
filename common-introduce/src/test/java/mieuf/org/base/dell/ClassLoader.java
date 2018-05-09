package mieuf.org.base.dell;

/**
 * Created by Administrator on 2018/5/3.
 * TODO -----
 */
public class ClassLoader {

    public static void main(String[] args) throws ClassNotFoundException {

        JdkProxy jdkProxy = new JdkProxy();
        AI o = (AI)jdkProxy.newProxy(new A());
        o.add();
//        Class<?> aClass = Class.forName("java.lang.String");
//        Class<?> aClass1 = String.class.getClassLoader().loadClass("java.lang.String");
//        System.out.println(aClass);
//        System.out.println(aClass1);
    }


}
