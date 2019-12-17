package com.test.thread.prime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentPrimeFinder extends AbstractPrimeFinder{
    private int poolSize;
    private int numberOfParts;

    public ConcurrentPrimeFinder(int poolSize, int numberOfParts) {
        this.poolSize = poolSize;
        this.numberOfParts = numberOfParts;
    }

    @Override
    public int countPrimes(int number) {
        int count = 0;
        final List<Callable<Integer>> partitions = new ArrayList<>();
        final int chunksPerPatition = number / numberOfParts;
        for (int i = 0; i < numberOfParts; i++) {
            final int lower = (i * chunksPerPatition) + 1;
            final int upper = (i == numberOfParts - 1) ? number : lower + chunksPerPatition -1;
            partitions.add(()->{
                return countPrimesInRange(lower,upper);
            });
        }
        final ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        try {
            final List<Future<Integer>> resultFromParts = executorService.invokeAll(partitions,10000, TimeUnit.SECONDS);
            executorService.shutdown();
            for (Future<Integer> future:resultFromParts){
                count += future.get();
            }

            return count;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return 0;
    }


    public static void main(String[] args) {
        new ConcurrentPrimeFinder(4,100).timeAndCompute(10000000);
    }
}
