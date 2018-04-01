package cn.bravedawn.airthmeticwebappse.common.util;

/**
 * Created by 冯晓 on 2018/3/17.
 */
public class Const {

    // cookies name.
    public final static String LOGIN_USER = "loginUser";

    // cookies time.
    public static final int SESSION_TIME = 60 * 60 * 24;

    public interface Operator {

        String add = "+";
        String division = "/";
        String subtraction = "-";
        String multiplication = "*";
    }
}
