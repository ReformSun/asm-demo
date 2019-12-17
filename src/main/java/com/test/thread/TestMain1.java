package com.test.thread;

import java.util.Properties;

public class TestMain1 {
    private static boolean done = false;
    public static void main(String[] args) {
        testMethod1();
    }

    public static void testMethod1(){
        new Thread(()->{
            int i = 0;
            while (!done){
                i++;
                System.out.println("done !");
            }

        }).start();

        System.out.println("os:" + System.getProperty("os.name"));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        done = true;
        System.out.println("flag done set to true");
    }

}
