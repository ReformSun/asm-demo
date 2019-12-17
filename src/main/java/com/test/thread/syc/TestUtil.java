package com.test.thread.syc;

public class TestUtil {
    private boolean aBoolean = false;
    private synchronized void setChange(){
        aBoolean = true;
    }

    private void startPrint(String msg){
        System.out.println(msg);
        synchronized (this){
            if (!aBoolean){
                return;
            }
            clearChanged();

        }
        System.out.println("结束：" + msg);
    }
    private synchronized void clearChanged() {
        aBoolean = false;
    }


    public void commond(){
//        for (int i = 0; i < 1000; i++) {
//            int finalI = i;
//            Thread thread = new Thread(()->{
//                synchronized (TestUtil.this){
//                    setChange();
//                    startPrint("线程" + finalI);
//                }
//
//            });
//            thread.start();
//        }

        Thread thread = new Thread(()->{
            synchronized (TestUtil.this){
                setChange();
                startPrint("线程0");
            }

        });
        thread.start();

        Thread thread1 = new Thread(()->{
            synchronized (TestUtil.this){
                setChange();
                startPrint("线程1");
            }

        });
        thread1.start();

        Thread thread2 = new Thread(()->{
            synchronized (TestUtil.this){
                setChange();
                startPrint("线程2");
            }

        });
        thread2.start();

        Thread thread3 = new Thread(()->{
            synchronized (TestUtil.this){
                setChange();
                startPrint("线程3");
            }

        });
        thread3.start();

        Thread thread4 = new Thread(()->{
            synchronized (TestUtil.this){
                setChange();
                startPrint("线程4");
            }

        });
        thread4.start();

        Thread thread5 = new Thread(()->{
            synchronized (TestUtil.this){
                setChange();
                startPrint("线程5");
            }

        });
        thread5.start();




    }
}
