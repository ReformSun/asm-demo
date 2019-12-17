package com.test.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OpcodesUtil {

    public static void main(String[] args) {
        System.out.println(getOpcodesString(0x18,ASMType.FIELD));
//        System.out.println(getAccessFlagString(33,ASMType.CLASS));
    }

    public static String getOpcodesString(int flag,ASMType type){
        String s = null;
        if (type != null){
            s = getAccessFlag(flag,type);
        }
        if (s != null)return s;
        s = getMinorVersion(flag);
        if (s != null)return s;
        s = getValueOfType(flag);
        if (s != null)return s;
        s = getReference_kindField(flag);
        if (s != null)return s;
        s = getVisit(flag);
        return s;
    }

    public static Set<String> getAccessFlagString(int flag, ASMType type){
        Set<String> set = new HashSet<>();
        if ((flag & 0x0001) != 0){
            String s = getAccessFlag(0x0001,type);
            if (s != null){
                set.add(s);
            }
        }
        if ((flag & 0x0002) != 0){
            String s = getAccessFlag(0x0002,type);
            if (s != null){
                set.add(s);
            }
        }

        if ((flag & 0x0004) != 0){
            String s = getAccessFlag(0x0004,type);
            if (s != null){
                set.add(s);
            }
        }

        if ((flag & 0x0008) != 0){
            String s = getAccessFlag(0x0008,type);
            if (s != null){
                set.add(s);
            }
        }

        if ((flag & 0x0010) != 0){
            String s = getAccessFlag(0x0010,type);
            if (s != null){
                set.add(s);
            }
        }

        if ((flag & 0x0020) != 0){
            String s = getAccessFlag(0x0020,type);
            if (s != null){
                set.add(s);
            }
        }

        if ((flag & 0x0040) != 0){
            String s = getAccessFlag(0x0040,type);
            if (s != null){
                set.add(s);
            }
        }

        if ((flag & 0x0080) != 0){
            String s = getAccessFlag(0x0080,type);
            if (s != null){
                set.add(s);
            }
        }

        if ((flag & 0x0100) != 0){
            String s = getAccessFlag(0x0100,type);
            if (s != null){
                set.add(s);
            }
        }

        if ((flag & 0x0200) != 0){
            String s = getAccessFlag(0x0200,type);
            if (s != null){
                set.add(s);
            }
        }
        if ((flag & 0x0400) != 0){
            String s = getAccessFlag(0x0400,type);
            if (s != null){
                set.add(s);
            }
        }
        if ((flag & 0x0800) != 0){
            String s = getAccessFlag(0x0800,type);
            if (s != null){
                set.add(s);
            }
        }
        if ((flag & 0x1000) != 0){
            String s = getAccessFlag(0x1000,type);
            if (s != null){
                set.add(s);
            }
        }
        if ((flag & 0x2000) != 0){
            String s = getAccessFlag(0x2000,type);
            if (s != null){
                set.add(s);
            }
        }
        if ((flag & 0x8000) != 0){
            String s = getAccessFlag(0x8000,type);
            if (s != null){
                set.add(s);
            }
        }
        if ((flag & 0x20000) != 0){
            String s = getAccessFlag(0x20000,type);
            if (s != null){
                set.add(s);
            }
        }
        return set;
    }



    private static String getAccessFlag(int flag,ASMType type){

        if (type == ASMType.MODULE_){
            switch (flag){
                case 0x1000 : return "ACC_SYNTHETIC";
                case 0x8000 : return "ACC_MANDATED";
            }
        }

        if (type == ASMType.MODULE_REQUIRES){
            switch (flag){
                case 0x0020 : return "ACC_TRANSITIVE";
                case 0x0040 : return "ACC_STATIC_PHASE";
            }
        }

        if (type == ASMType.MODULE){
            switch (flag){
                case 0x0020 : return "ACC_OPEN";
                case 0x8000 : return "ACC_MANDATED";
            }
        }

        if (type == ASMType.PARAMETER){
            switch (flag){
                case 0x0010 : return "ACC_FINAL";
                case 0x1000 : return "ACC_SYNTHETIC";
                case 0x8000 : return "ACC_MANDATED";
            }
        }

        if (type == ASMType.FIELD){
            switch (flag){
                case 0x0001 : return "ACC_PUBLIC";// 1
                case 0x0002 : return "ACC_PRIVATE";//
                case 0x0004 : return "ACC_PROTECTED";
                case 0x0008 : return "ACC_STATIC";
                case 0x0010 : return "ACC_FINAL";// 16
                case 0x0040 : return "ACC_VOLATILE";// 64
                case 0x0080 : return "ACC_TRANSIENT";// 128
                case 0x1000 : return "ACC_SYNTHETIC";
                case 0x4000 : return "ACC_ENUM";
                case 0x20000 : return "ACC_DEPRECATED";
            }
        }



        if (type == ASMType.CLASS){
            switch (flag){
                case 0x0001 : return "ACC_PUBLIC";
                case 0x0002 : return "ACC_PRIVATE";
                case 0x0004 : return "ACC_PROTECTED";
                case 0x0010 : return "ACC_FINAL";
                case 0x0020 : return "ACC_SUPER";
                case 0x0200 : return "ACC_INTERFACE";
                case 0x0400 : return "ACC_ABSTRACT";
                case 0x1000 : return "ACC_SYNTHETIC";
                case 0x2000 : return "ACC_ANNOTATION";
                case 0x4000 : return "ACC_ENUM";
                case 0x8000 : return "ACC_MODULE";
                case 0x20000 : return "ACC_DEPRECATED";
            }
        }

        if (type == ASMType.METHOD){
            switch (flag){
                case 0x0001 : return "ACC_PUBLIC";
                case 0x0002 : return "ACC_PRIVATE";
                case 0x0004 : return "ACC_PROTECTED";
                case 0x0008 : return "ACC_STATIC";
                case 0x0010 : return "ACC_FINAL";
                case 0x0020 : return "ACC_SYNCHRONIZED";
                case 0x0040 : return "ACC_BRIDGE";
                case 0x0080 : return "ACC_VARARGS";
                case 0x0100 : return "ACC_NATIVE";
                case 0x0400 : return "ACC_ABSTRACT";
                case 0x0800 : return "ACC_STRICT";
                case 0x1000 : return "ACC_SYNTHETIC";
                case 0x20000 : return "ACC_DEPRECATED";
            }
        }

        return null;
    }


    private static String getMinorVersion(int flag){
        switch (flag){
            case 3 << 16 | 45 : return "V1_1";
            case 3 << 16 | 46 : return "V1_2";
            case 3 << 16 | 47 : return "V1_3";
            case 3 << 16 | 48 : return "V1_4";
            case 3 << 16 | 49 : return "V1_5";
            case 3 << 16 | 50 : return "V1_6";
            case 3 << 16 | 51 : return "V1_7";
            case 3 << 16 | 52 : return "V1_8";
            case 3 << 16 | 53 : return "V9";
            case 3 << 16 | 54: return "V10";
            case 3 << 16 | 55 : return "V11";
            case 3 << 16 | 56 : return "V12";
            case 3 << 16 | 57 : return "V13";
        }
        return null;
    }

    private static String getValueOfType(int flag){
        switch (flag){
            case 4 : return "T_BOOLEAN";
            case 5 : return "T_CHAR";
            case 6 : return "T_FLOAT";
            case 7 : return "T_DOUBLE";
            case 8 : return "T_BYTE";
            case 9 : return "T_SHORT";
            case 10 : return "T_INT";
            case 11: return "T_LONG";
        }
        return null;
    }

    private static String getReference_kindField(int flag){
        switch (flag){
            case 1 : return "H_GETFIELD";
            case 2 : return "H_GETSTATIC";
            case 3 : return "H_PUTFIELD";
            case 4 : return "H_PUTSTATIC";
            case 5 : return "H_INVOKEVIRTUAL";
            case 6 : return "H_INVOKESTATIC";
            case 7 : return "H_INVOKESPECIAL";
            case 8 : return "H_NEWINVOKESPECIAL";
            case 9 : return "H_INVOKEINTERFACE";
        }
        return null;
    }

   public static String getVisit(int flag){
       String s = null;
       s = getVisiTableSwitchInsn(flag);
       if (s != null)return s;
       s = getVisitFieldInsn(flag);
       if (s != null)return s;
       s = getVisitIincInsn(flag);
       if (s != null)return s;
       s = getVisitInsn(flag);
       if (s != null)return s;
       s = getVisitIntInsn(flag);
       if (s != null)return s;
       s = getVisitInvokeDynamicInsn(flag);
       if (s != null)return s;
       s = getVisitJumpInsn(flag);
       if (s != null)return s;
       s = getVisitLdcInsn(flag);
       if (s != null)return s;
       s = getVisitLookupSwitch(flag);
       if (s != null)return s;
       s = getVisitMethodInsn(flag);
       if (s != null)return s;
       s = getVisitMultiANewArrayInsn(flag);
       if (s != null)return s;
       s = getVisitTypeInsn(flag);
       if (s != null)return s;
       s = getVisitVarInsn(flag);
       if (s != null)return s;
       return flag + "";
   }




    private static String getVisitInsn(int flag){
        switch (flag){
            case 0 : return "NOP";
            case 1 : return "ACONST_NULL";
            case 2 : return "ICONST_M1";
            case 3 : return "ICONST_0";
            case 4 : return "ICONST_1";
            case 5 : return "ICONST_2";
            case 6 : return "ICONST_3";
            case 7 : return "ICONST_4";
            case 8 : return "ICONST_5";
            case 9 : return "LCONST_0";
            case 10 : return "LCONST_1";
            case 11 : return "FCONST_0";
            case 12 : return "FCONST_1";
            case 13 : return "FCONST_2";
            case 14 : return "DCONST_0";
            case 15 : return "DCONST_1";
            case 46 : return "IALOAD";
            case 47 : return "LALOAD";
            case 48 : return "FALOAD";
            case 49 : return "DALOAD";
            case 50 : return "AALOAD";
            case 51 : return "BALOAD";
            case 52 : return "CALOAD";
            case 53 : return "SALOAD";
            case 79 : return "IASTORE";
            case 80 : return "LASTORE";
            case 81 : return "FASTORE";
            case 82 : return "DASTORE";
            case 83 : return "AASTORE";
            case 84 : return "BASTORE";
            case 85 : return "CASTORE";
            case 86 : return "SASTORE";
            case 87 : return "POP";
            case 88 : return "POP2";
            case 89 : return "DUP";
            case 90 : return "DUP_X1";
            case 91 : return "DUP_X2";
            case 92 : return "DUP2";
            case 93 : return "DUP2_X1";
            case 94 : return "DUP2_X2";
            case 95 : return "SWAP";
            case 96 : return "IADD";
            case 97 : return "LADD";
            case 98 : return "FADD";
            case 99 : return "DADD";
            case 100 : return "ISUB";
            case 101 : return "LSUB";
            case 102 : return "FSUB";
            case 103 : return "DSUB";
            case 104 : return "IMUL";
            case 105 : return "LMUL";
            case 106 : return "FMUL";
            case 107 : return "DMUL";
            case 108 : return "IDIV";
            case 109 : return "LDIV";
            case 110 : return "FDIV";
            case 111 : return "DDIV";
            case 112 : return "IREM";
            case 113 : return "LREM";
            case 114 : return "FREM";
            case 115 : return "DREM";
            case 116 : return "INEG";
            case 117 : return "LNEG";
            case 118 : return "FNEG";
            case 119 : return "DNEG";
            case 120 : return "ISHL";
            case 121 : return "LSHL";
            case 122 : return "ISHR";
            case 123 : return "LSHR";
            case 124 : return "IUSHR";
            case 125 : return "LUSHR";
            case 126 : return "IAND";
            case 127 : return "LAND";
            case 128 : return "IOR";
            case 129 : return "LOR";
            case 130 : return "IXOR";
            case 131 : return "LXOR";
            case 133 : return "I2L";
            case 134 : return "I2F";
            case 135 : return "I2D";
            case 136 : return "L2I";
            case 137 : return "L2F";
            case 138 : return "L2D";
            case 139 : return "F2I";
            case 140 : return "F2L";
            case 141 : return "F2D";
            case 142 : return "D2I";
            case 143 : return "D2L";
            case 144 : return "D2F";
            case 145 : return "I2B";
            case 146 : return "I2C";
            case 147 : return "I2S";
            case 148 : return "LCMP";
            case 149 : return "FCMPL";
            case 150 : return "FCMPG";
            case 151 : return "DCMPL";
            case 152 : return "DCMPG";
            case 172 : return "IRETURN";
            case 173 : return "LRETURN";
            case 174 : return "FRETURN";
            case 175 : return "DRETURN";
            case 176 : return "ARETURN";
            case 177 : return "RETURN";
            case 190 : return "ARRAYLENGTH";
            case 191 : return "ATHROW";
            case 194 : return "MONITORENTER";
            case 195 : return "MONITOREXIT";
        }
        return null;
    }

    private static String getVisitIntInsn(int flag){
        switch (flag){
            case 16 : return "BIPUSH";
            case 17: return "SIPUSH";
            case 188 : return "NEWARRAY";
        }
        return null;
    }

    private static String getVisitLdcInsn(int flag){
        switch (flag){
            case 18 : return "LDC";
        }
        return null;
    }
    private static String getVisitMultiANewArrayInsn(int flag){
        switch (flag){
            case 197 : return "MULTIANEWARRAY";
            case 199 : return "IFNONNULL";
        }
        return null;
    }
    private static String getVisitMethodInsn(int flag){
        switch (flag){
            case 182 : return "INVOKEVIRTUAL";
            case 183 : return "INVOKESPECIAL";
            case 184 : return "INVOKESTATIC";
            case 185 : return "INVOKEINTERFACE";
        }
        return null;
    }
    private static String getVisitInvokeDynamicInsn(int flag){
        switch (flag){
            case 186 : return "INVOKEDYNAMIC";
        }
        return null;
    }
    private static String getVisitTypeInsn(int flag){
        switch (flag){
            case 187 : return "NEW";
            case 189 : return "ANEWARRAY";
            case 192 : return "CHECKCAST";
            case 193 : return "INSTANCEOF";
        }
        return null;
    }


    private static String getVisiTableSwitchInsn(int flag){
        switch (flag){
            case 170 : return "TABLESWITCH";
        }
        return null;
    }
    private static String getVisitLookupSwitch(int flag){
        switch (flag){
            case 171 : return "LOOKUPSWITCH";
        }
        return null;
    }
    private static String getVisitFieldInsn(int flag){
        switch (flag){
            case 178 : return "GETSTATIC";
            case 179 : return "PUTSTATIC";
            case 180 : return "GETFIELD";
            case 181 : return "PUTFIELD";
        }
        return null;
    }

    private static String getVisitVarInsn(int flag){
        switch (flag){

            case 21 : return "ILOAD";
            case 22 : return "LLOAD";
            case 23 : return "FLOAD";
            case 24 : return "DLOAD";
            case 25 : return "ALOAD";
            case 54 : return "ISTORE";
            case 55 : return "LSTORE";
            case 56 : return "FSTORE";
            case 57 : return "DSTORE";
            case 58 : return "ASTORE";
            case 69 : return "RET";
        }
        return null;
    }

    private static String getVisitIincInsn(int flag){
        switch (flag){
            case 132 : return "IINC";
        }
        return null;
    }

    private static String getVisitJumpInsn(int flag){
        switch (flag){
            case 153 : return "IFEQ";
            case 154 : return "IFNE";
            case 155 : return "IFLT";
            case 156 : return "IFGE";
            case 157 : return "IFGT";
            case 158 : return "IFLE";
            case 159 : return "IF_ICMPEQ";
            case 160 : return "IF_ICMPNE";
            case 161 : return "IF_ICMPLT";
            case 162 : return "IF_ICMPGE";
            case 163 : return "IF_ICMPGT";
            case 164 : return "IF_ICMPLE";
            case 165 : return "IF_ACMPEQ";
            case 166 : return "IF_ACMPNE";
            case 167 : return "GOTO";
            case 168 : return "JSR";
            case 198 : return "IFNULL";
        }
        return null;
    }

    // ASM specific stack map frame types, used in {@link ClassVisitor#visitFrame}.
    private static String getOtherOpcodes(int flag){
        switch (flag){
            case -1 : return "F_NEW";
            case 0 : return "F_FULL";
            case 1 : return "F_APPEND";
            case 2 : return "F_CHOP";
            case 3 : return "F_SAME";
            case 4 : return "F_SAME1";
        }
        return null;
    }



}
