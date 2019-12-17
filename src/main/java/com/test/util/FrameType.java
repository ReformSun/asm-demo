package com.test.util;


/**
 * 栈映射帧：由名为stack_map_frame的可辨识联合体 来表示
 * 包含长度为1字节的tag
 * 表明当前使用的联合体是哪一种，其后跟0个或者多个字节标记有关更多信息
 */
public class FrameType {
    public static String getType(int type){
        switch (type){
            case -1:return "F_NEW";
            case 0:return "F_FULL";
            case 1:return "F_APPEND";
            case 2:return "F_CHOP";
            case 3:return "F_SAME";// 0~63
            case 4:return "F_SAME1";
        }
        return null;
    }
}
