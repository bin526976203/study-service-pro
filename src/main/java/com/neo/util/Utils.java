package com.neo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @author moxianbin
 * @date 2019/3/2.
 */
public class Utils {

    public static String getUnixTime(LocalDateTime date){
        return DateUtils.dateToStr(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static String getLearnId(int length){
        Random randGen = null;
        char[] numbersAndLetters = null;
        Object initLock = new Object();

        if (length < 1) {
            return null;
        }
        if (randGen == null) {
            synchronized (initLock) {
                if (randGen == null) {
                    randGen = new Random();
                    numbersAndLetters = ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
                }
            }
        }
        char [] randBuffer = new char[length];
        for (int i=0; i<randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(32)];
        }
        return new String(randBuffer);

    }
}
