package com.test.util;





import com.google.common.base.Splitter;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvFileReader {
    public List<String[]> readCsvFile(String path) throws IOException {
        Path logFile = Paths.get(path);
        try (BufferedReader reader = Files.newBufferedReader(logFile, StandardCharsets.UTF_8)){
            List<String[]> list = new ArrayList<>();
            String line;
            while (( line = reader.readLine()) != null){
                String[] strings = (String[]) Splitter.on(",").omitEmptyStrings().splitToList(line).toArray();
                list.add(strings);
            }
            return list;
        }
    }

    /**
     * 读取股票价格
     * 模拟网络延迟
     *
     * @param valueIndex 股票价格所在csv文件中值位置
     * @param path
     * @param ticker 股票代号
     * @return
     * @throws IOException
     */
    public static double getValue(int valueIndex,String path,String ticker) throws IOException {
        Path logFile = Paths.get(path);
        try (BufferedReader reader = Files.newBufferedReader(logFile, StandardCharsets.UTF_8)){
            String line;
            while (( line = reader.readLine()) != null){
                List<String> strings =  Splitter.on(",").omitEmptyStrings().splitToList(line);
                if (strings.get(0).equals(ticker)){
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return Double.parseDouble(strings.get(valueIndex));
                }
            }
            return 0;
        }
    }
}
