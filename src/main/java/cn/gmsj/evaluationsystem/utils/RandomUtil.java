package cn.gmsj.evaluationsystem.utils;

import java.util.Random;

/**
 * @author 13562
 */
public class RandomUtil {

    public static String sixAuthCode() {
        String authCode = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int r = random.nextInt(10);
            authCode = authCode + r;

        }
        return authCode;
    }
}
