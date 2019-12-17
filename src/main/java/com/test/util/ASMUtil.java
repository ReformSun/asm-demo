package com.test.util;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

public class ASMUtil {
    public static ClassNode convertToClassNode(byte[] classBytes)
    {
        ClassReader reader = new ClassReader(classBytes);
        ClassNode result = new ClassNode(458752);
        reader.accept(result, 0);
        return result;
    }
}
