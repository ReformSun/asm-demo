package com.test.util;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class ByteUtil {
    public static final int DEFAULT_BUFFER_SIZE = 8192;

    /**
     *
     * @param jarPath
     * @param className com/opensymphony/xwork2/ActionInvocation.class
     * @return
     */
    public static byte[] getByteArray(String jarPath,String className){
        JarInputStream jarInputStream;
        try {
            jarInputStream  = new JarInputStream(new FileInputStream(jarPath));
            try {
                JarEntry entry = null;
                while ((entry = jarInputStream.getNextJarEntry()) != null) {
                    if (entry.getName().endsWith(".class") && entry.getName().equals(className)) {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        int bufferSize = 4096;
                        byte[] buffer = new byte[bufferSize];
                        int bytesNumRead = 0;
                        while ((bytesNumRead = jarInputStream.read(buffer)) != -1) {
                            baos.write(buffer, 0, bytesNumRead);
                        }
                        return baos.toByteArray();
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getByteArray(String classPath){
        InputStream inputStream = getStream(classPath);
        if (inputStream != null){
            try {
                return read(inputStream,DEFAULT_BUFFER_SIZE,true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static InputStream getStream(String classPath){
        Path path = Paths.get(classPath);
        try {
            InputStream inputStream = Files.newInputStream(path);
            return inputStream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] read(final InputStream input, int expectedSize, final boolean closeInputStream) throws IOException {
        if (expectedSize <= 0) {
            expectedSize = 8192;
        }
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream(expectedSize);
        copy(input, outStream, expectedSize, closeInputStream);
        return outStream.toByteArray();
    }
    private static int copy(final InputStream input, final OutputStream output, final int bufferSize, final boolean closeStreams) throws IOException {
        try {
            if (0 == bufferSize) {
                return 0;
            }
            final byte[] buffer = new byte[bufferSize];
            int count = 0;
            int n = 0;
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
                count += n;
            }
            return count;
        }
        finally {
            if (closeStreams) {
                input.close();
                output.close();
            }
        }
    }



    public static byte[] getClassBytes(final ClassLoader classLoader, String name) throws IOException {
        name = name.replaceAll("\\.", "/") + ".class";
        InputStream iStream = null;
        if (classLoader == null) {
            return null;
        }
        else {
            iStream = classLoader.getResourceAsStream(name);
        }
        if (iStream != null) {
            try {
                final ByteArrayOutputStream oStream = new ByteArrayOutputStream();
                copy(iStream, oStream,DEFAULT_BUFFER_SIZE,true);
                return oStream.toByteArray();
            }
            finally {
                iStream.close();
            }
        }
        return null;
    }
}
