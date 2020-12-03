package com.cyq.assert_;

/**
 * assert关键字演示
 */
public class AssertDemo01 {

    public static void main(String[] args) {
        assert true;
        System.out.println("Assert为true...");
        assert false : "Error";
        System.out.println("Assert为false...");
    }

}
