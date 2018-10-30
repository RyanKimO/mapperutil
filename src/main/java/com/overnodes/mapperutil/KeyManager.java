package com.overnodes.mapperutil;


import java.util.Random;

public class KeyManager {

  public static String createKey() {
    Random ran = new Random();
    StringBuffer sb = new StringBuffer();
    int size = 10;
    int num = 0;
    do {
      num = ran.nextInt(75) + 48;
      if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
        sb.append((char) num);
      } else {
        continue;
      }
    } while (sb.length() < size);

    return sb.toString();
  }

}
