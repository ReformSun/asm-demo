package com.learnBasicFunction.readJavaFile;

import org.objectweb.asm.AnnotationVisitor;

public class AnnotationVisiterTest extends AnnotationVisitor{
    public AnnotationVisiterTest(int api) {
        super(api);
    }

    public AnnotationVisiterTest(int api, AnnotationVisitor av) {
        super(api, av);
    }

    @Override
    public void visit(String name, Object value) {
        super.visit(name, value);
    }

    @Override
    public void visitEnum(String name, String desc, String value) {
        super.visitEnum(name, desc, value);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String name, String desc) {
        return super.visitAnnotation(name, desc);
    }

    @Override
    public AnnotationVisitor visitArray(String name) {
        return super.visitArray(name);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
