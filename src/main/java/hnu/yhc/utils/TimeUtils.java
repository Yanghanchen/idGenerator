package hnu.yhc.utils;

public class TimeUtils {
    //从2018/1/1 00:00:00起开始计时
    public static final long START = 1514736000000L;

    public static long tillNextTimeUnit(final long lastTime){
        long nowTime=genTime();
        while(nowTime<=lastTime){
            nowTime=genTime();
        }
        return nowTime;
    }

    public static long genTime() {
        return (System.currentTimeMillis()-START);
    }

    public static boolean validateTime(long lastTime,long nowTime){
        if(nowTime<lastTime){
            return false;
        }
        return true;
    }
}
