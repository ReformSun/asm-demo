package com.learnBasicFunction.readJavaFile;

import org.objectweb.asm.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test1 {
    public static void main(String[] args) {

        try{
         testMethod1();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void testMethod1() throws IOException {

        Path path = Paths.get("./src/test/testClassFile/TestClass.class");
        InputStream inputStream = Files.newInputStream(path);
        ClassReader classReader = new ClassReader(inputStream);
        classReader.accept(new ClassVisitorTest(Opcodes.ASM5),2);
    }
}
