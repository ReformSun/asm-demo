/***
 * Excerpted from "Programming Concurrency on the JVM",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/vspcon for more book information.
***/
package com.test.thread.ticker;

import com.test.util.CsvFileReader;
import com.test.util.URLUtil;

import java.util.Map;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class SequentialNAV extends AbstractNAV {
  public double computeNetAssetValue(
    final Map<String, Integer> stocks) throws IOException {
    double netAssetValue = 0.0;
    for(String ticker : stocks.keySet()) {
      netAssetValue += stocks.get(ticker) * CsvFileReader.getValue(1, URLUtil.baseUrl + "yahoo.csv",ticker);
    }
    return netAssetValue;   
  } 
   
  public static void main(final String[] args) 
    throws ExecutionException, IOException, InterruptedException { 
    new SequentialNAV().timeAndComputeValue();
  }
}
