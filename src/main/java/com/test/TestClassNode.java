package com.test;

import com.test.testclass.TestClass;
import com.test.util.ASMType;
import com.test.util.ByteUtil;
import com.test.util.InstructionType;
import com.test.util.OpcodesUtil;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import java.util.Iterator;
import java.util.List;

import static com.test.util.Common.*;
import static com.test.util.ByteUtil.*;
import static com.test.util.ASMUtil.*;
import static com.test.util.OpcodesUtil.*;


/**
 * 注解运行时可见性
 * 使用的注解需要在vm将在运行期也保留注解
 * 其实就是使用元注解
 * @Retention(RetentionPolicy.RUNTIME) 保证注解不被丢弃
 *
 * 当使用
 */
public class TestClassNode {
    static ClassNode classNode;
    public static void main(String[] args) {
        String classN = "com/test/testclass/TestClass.class";
        byte[] bytes = ByteUtil.getByteArray(testClassBaseUrl+classN);
        classNode = convertToClassNode(bytes);
//        testMethod1();
        testMethod2();
    }


    public static void testMethod2(){
        /**
         * <init>
         *
         *     testMethod6
         */
        for (MethodNode methodNode:classNode.methods){
            if (methodNode.name.equals("<init>")){
                InsnList insnList = methodNode.instructions;
                Iterator<AbstractInsnNode> insnNodeIterator = insnList.iterator();

                while (insnNodeIterator.hasNext()){
                    AbstractInsnNode abstractInsnNode = insnNodeIterator.next();
//                    System.out.println("aa");
                    System.out.println(OpcodesUtil.getVisit(abstractInsnNode.getOpcode()) + "  " + InstructionType.getType(abstractInsnNode.getType()));
//                    System.out.println(OpcodesUtil.getOpcodesString(abstractInsnNode.getOpcode(),null));
//                    System.out.println(abstractInsnNode.getType());
                }
            }
        }
    }

    public static void testMethod1(){

        System.out.println(classNode.name);
        System.out.println(classNode.access);
        System.out.println(getAccessFlagString(classNode.access, ASMType.CLASS));
        List<AnnotationNode> annotationNodes = classNode.visibleAnnotations;
        if (annotationNodes != null){
            System.out.println("annotationNodes");
            for (AnnotationNode annotationNode:annotationNodes){
                System.out.println(annotationNode.values);
                System.out.println(Type.getType(annotationNode.desc).getClassName());
            }
        }
        List<AnnotationNode> invisibleAnnotations = classNode.invisibleAnnotations;
        if (invisibleAnnotations != null){
            System.out.println("invisibleAnnotations");
            for (AnnotationNode annotationNode:invisibleAnnotations){
                System.out.println(annotationNode.values);
                System.out.println(annotationNode.desc);
            }
        }

        List<TypeAnnotationNode> invisibleTypeAnnotations = classNode.invisibleTypeAnnotations;
        if (invisibleTypeAnnotations != null){
            System.out.println("invisibleTypeAnnotations");
            for (TypeAnnotationNode annotationNode:invisibleTypeAnnotations){
                System.out.println(annotationNode.values);
                System.out.println(annotationNode.desc);
            }
        }

        List<TypeAnnotationNode> visibleTypeAnnotations = classNode.visibleTypeAnnotations;
        if (visibleTypeAnnotations != null){
            System.out.println("visibleTypeAnnotations");
            for (TypeAnnotationNode annotationNode:visibleTypeAnnotations){
                System.out.println(annotationNode.values);
                System.out.println(annotationNode.desc);
            }
        }
    }
}
