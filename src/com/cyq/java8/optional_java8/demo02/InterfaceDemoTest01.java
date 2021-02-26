package com.cyq.java8.optional_java8.demo02;

/**
 * Java中的接口可以有默认的静态方法
 */
public class InterfaceDemoTest01 implements MyInterface {

    public static void main(String[] args) {
        InterfaceDemoTest01 interfaceDemoTest01 = new InterfaceDemoTest01();
        interfaceDemoTest01.show();
        MyInterface.say();
    }

}
