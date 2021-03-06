package com.test.customClassWriter;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;

import java.io.IOException;
import java.io.InputStream;

class ClassMetadata {
    private static final int HEADER_MODIFIERS_OFFSET = 0;
    private static final int HEADER_SUPER_CLASS_CONSTANT_POOL_OFFSET = 4;
    private static final int HEADER_NUM_INTERFACES_OFFSET = 6;
    private static final int HEADER_INITIAL_INTERFACE_OFFSET = 8;
    private Type type;
    private ClassLoader classLoader;
    int modifiers;
    String superClass;
    ClassMetadata superClassMetadata;
    String[] interfaces;
    ClassMetadata[] interfaceMetadata;
    private ClassReader cr;

    public ClassMetadata(String type, ClassLoader loader) {
        this.classLoader = loader == null?ClassLoader.getSystemClassLoader():loader;
        this.type = Type.getObjectType(type);
        this.cr = this.getClassReader(type, this.classLoader);
        this.modifiers = this.cr.readUnsignedShort(this.cr.header + 0);
        char[] buf = new char[2048];
        int cpSuperIndex = this.cr.getItem(this.cr.readUnsignedShort(this.cr.header + 4));
        this.superClass = cpSuperIndex == 0?null:this.cr.readUTF8(cpSuperIndex, buf);
        this.interfaces = new String[this.cr.readUnsignedShort(this.cr.header + 6)];
        int nextInterface = this.cr.header + 8;

        for(int i = 0; i < this.interfaces.length; ++i) {
            this.interfaces[i] = this.cr.readClass(nextInterface, buf);
            nextInterface += 2;
        }

    }

    public ClassReader getClassReader() {
        return this.cr;
    }

    String[] getInterfaceNames() {
        return this.interfaces;
    }

    private ClassReader getClassReader(String type, ClassLoader classLoader) {
        String resource = type.replace('.', '/') + ".class";
        InputStream is = null;

        ClassReader cr;
        try {
            is = classLoader.getResourceAsStream(resource);
            cr = new ClassReader(is);
        } catch (IOException var14) {
            throw new RuntimeException("unable to access resource: " + resource, var14);
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (Exception var13) {
                    ;
                }
            }

        }

        return cr;
    }

    String getName() {
        return this.type.getInternalName();
    }

    ClassMetadata getSuperclass() {
        if(this.superClass == null) {
            return null;
        } else {
            if(this.superClassMetadata == null) {
                this.superClassMetadata = new ClassMetadata(this.superClass, this.classLoader);
            }

            return this.superClassMetadata;
        }
    }

    ClassMetadata[] getInterfaces() {
        if(this.interfaceMetadata == null) {
            this.interfaceMetadata = new ClassMetadata[this.interfaces.length];

            for(int i = 0; i < this.interfaces.length; ++i) {
                this.interfaceMetadata[i] = new ClassMetadata(this.interfaces[i], this.classLoader);
            }
        }

        return this.interfaceMetadata;
    }

    boolean isInterface() {
        return (this.modifiers & 512) > 0;
    }

    private boolean implementsInterface(ClassMetadata other) {
        if(this == other) {
            return true;
        } else {
            for(ClassMetadata c = this; c != null; c = c.getSuperclass()) {
                ClassMetadata[] var3 = c.getInterfaces();
                int var4 = var3.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    ClassMetadata in = var3[var5];
                    if(in.type.equals(other.type) || in.implementsInterface(other)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    private boolean isSubclassOf(ClassMetadata other) {
        for(ClassMetadata c = this; c != null; c = c.getSuperclass()) {
            ClassMetadata sc = c.getSuperclass();
            if(sc != null && sc.type.equals(other.type)) {
                return true;
            }
        }

        return false;
    }

    public boolean isAssignableFrom(ClassMetadata other) {
        return this == other || other.implementsInterface(this) || other.isSubclassOf(this) || other.isInterface() && this.type.getDescriptor().equals("Ljava/lang/Object;");
    }
}
