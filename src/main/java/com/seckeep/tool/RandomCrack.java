package com.seckeep.tool;

import org.springframework.stereotype.Controller;

/**
 * Created by 60dimension on 16/5/30.
 */
@Controller
public class  RandomCrack {
    protected static final long a = 0x5DEECE66DL;
    protected static final long b = 0xBL;
    protected static final long m = (1L << 48) - 1;
    public static int Crack(int xint0,int xint1)
    {
        long i;
        long seed = -1L;
        long x0 = (xint0 & 0xFFFFFFFFL) << 16;
        long x1 = (xint1 & 0xFFFFFFFFL);
        for (i = 0; i < 0xFFFFL; i++) {
            seed = (((x0 + i) * a) + b) & m;
            if ((seed >>> 16) == x1) {

                break;
            }
            seed = -1L;
        }
        if (seed == -1L) {
            throw new RuntimeException("Input Error!");
        } else {

           // System.out.println("Cracked random number:" + (int) (((seed * a) + b & m) >>> 16));
            return  (int) (((seed * a) + b & m) >>> 16);
        }
    }
}
