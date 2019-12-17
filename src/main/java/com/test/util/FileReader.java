package com.test.util;

import com.google.common.base.Splitter;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileReader {

    public static void main(String[] args) {
        testMethod2();
    }

    public static void testMethod1(){
        Iterator<String> iterator = null;
        try {
            iterator = readFile(URLUtil.baseUrl+"stocks.txt").iterator();
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testMethod2(){
        try {
            List<String> list = readFile(URLUtil.baseUrl+"stocks.txt");
            Gson gson = new Gson();
            System.out.println(gson.toJson(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readFile(String path) throws IOException {
        Path logFile = Paths.get(path);
        try (BufferedReader reader = Files.newBufferedReader(logFile, StandardCharsets.UTF_8)){
            List<String> list = new ArrayList<>();
            String line;
            while (( line = reader.readLine()) != null){
                List<String> strings =  Splitter.on(",").omitEmptyStrings().splitToList(line);
                list.add(strings.get(0));
            }
            return list;
        }

    }

}
