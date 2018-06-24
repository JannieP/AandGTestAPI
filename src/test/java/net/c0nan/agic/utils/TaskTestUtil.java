package net.c0nan.agic.utils;

public class TaskTestUtil {
    public static final String BASIC_WORKING = "({[]})";
    public static final String BASIC_NOT_WORKING = "({]})";
    public static final String BASIC_NOT_WORKING2 = "{[}]";

    public static final String BASIC_NOT_WORKING_EDGE = "{[]}]";

    public static final String COMPLEX_WORKING = "if(1=1){String[] text = {\"123\"}; }";
    public static final String COMPLEX_NOT_WORKING = "if(1=1){String[] text = {\"123\"}; })";
    public static final String COMPLEX_NOT_WORKING2 = "if(1=1)({String[] text = {\"123\"}; }";
}
