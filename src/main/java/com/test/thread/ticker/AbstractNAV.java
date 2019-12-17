package com.test.thread.ticker;

import com.test.util.URLUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public abstract class AbstractNAV {
    public static Map<String, Integer> readTickers() throws IOException {
        final BufferedReader reader =
                new BufferedReader(new FileReader(URLUtil.baseUrl+"stocks.txt"));

        final Map<String, Integer> stocks = new HashMap<String, Integer>();

        String stockInfo = null;
        while((stockInfo = reader.readLine()) != null) {
            final String[] stockInfoData = stockInfo.split(",");
            final String stockTicker = stockInfoData[0];
            final Integer quantity = Integer.valueOf(stockInfoData[1]);

            stocks.put(stockTicker, quantity);
        }

        return stocks;
    }

    public void timeAndComputeValue()
            throws ExecutionException, InterruptedException, IOException {
        final long start = System.nanoTime();

        final Map<String, Integer> stocks = readTickers();
        final double nav = computeNetAssetValue(stocks);

        final long end = System.nanoTime();

        final String value = new DecimalFormat("$##,##0.00").format(nav);
        System.out.println("Your net asset value is " + value);
        System.out.println("Time (seconds) taken " + (end - start)/1.0e9);
    }

    public abstract double computeNetAssetValue(
            final Map<String, Integer> stocks)
            throws ExecutionException, InterruptedException, IOException;
}

