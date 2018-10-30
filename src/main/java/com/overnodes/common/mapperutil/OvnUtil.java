package com.overnodes.common.mapperutil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OvnUtil {
    //Per Minute
    public static final int REGISTER_TOKEN_EXPIRE_TIME = 60*24; // 1day


    public static String getDate(){
        Date today = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        return date.format(today);
    }

    public static String concatenate(List<String> listOfItems, String separator) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> stit = listOfItems.iterator();

        while (stit.hasNext()) {
            sb.append(stit.next());
            if (stit.hasNext()) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }
}
