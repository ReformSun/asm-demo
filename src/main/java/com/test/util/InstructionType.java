package com.test.util;

public class InstructionType {

    public static String getType(int type){
        switch (type){
            case 0:return "INSN";
            case 1:return "INT_INSN";
            case 2:return "VAR_INSN";
            case 3:return "TYPE_INSN";
            case 4:return "FIELD_INSN";
            case 5:return "METHOD_INSN";
            case 6:return "INVOKE_DYNAMIC_INSN";
            case 7:return "JUMP_INSN";
            case 8:return "LABEL";
            case 9:return "LDC_INSN";
            case 10:return "IINC_INSN";
            case 11:return "TABLESWITCH_INSN";
            case 12:return "LOOKUPSWITCH_INSN";
            case 13:return "MULTIANEWARRAY_INSN";
            case 14:return "FRAME";
            case 15:return "LINE";
        }
        return null;
    }
}
