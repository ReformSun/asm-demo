package com.test.util;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriterUtil {
    public static void writer(byte[] bytes,String fileName){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./src/test/testClassFile/" + fileName);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
