package com.test.thread.prime;

/**
 * 这是一个计算密集型的任务 计算1千万内的素数个数
 */
public class SequentialPrimeFinder extends AbstractPrimeFinder{
    @Override
    public int countPrimes(int number) {
        return countPrimesInRange(1,number);
    }

    public static void main(String[] args) {
        new SequentialPrimeFinder().timeAndCompute(10000000);
    }
}
