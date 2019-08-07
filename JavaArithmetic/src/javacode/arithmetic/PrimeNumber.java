package javacode.arithmetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @className PrimeNumber
 * @Description 质数又称素数。一个大于1的自然数，除了1和它自身外，不能被其他自然数整除的数叫做质数；否则称为合数。
 * 2是最小质数
 * @Author chungaochen
 * Date 2019/8/1 11:12
 * Version 1.0
 **/
public class PrimeNumber {

    public static void main(String[] args) {
        HashMap map ;
        long timePoint1 = System.currentTimeMillis();
        sqrPrime(149);
        long timePoint2 = System.currentTimeMillis();
        System.out.println("++++++++++++++++++++++++++++++++");
        selectPrimes(149);
        long timePoint3 = System.currentTimeMillis();
        System.out.println("++++++++++++++++++++++++++++++++");
//        sixNPrime(200000);
//        long timePoint4 = System.currentTimeMillis();
//        System.out.println("++++++++++++++++++++++++++++++++");
        System.out.println("sqrt法耗时: " + String.valueOf(timePoint2 - timePoint1)); // 200000耗时146 ms
        System.out.println("埃氏筛法耗时: " + String.valueOf(timePoint3 - timePoint2)); // 200000耗时82 ms
//        System.out.println("6N±1法耗时: " + String.valueOf(timePoint4 - timePoint3)); // 214 ms
    }

    /**
     * @return boolean
     * @Author chungaochen
     * @Description sqrt 法求素数：
     * 常规方式——对正整数n，如果用2到它的平方根之间的所有整数去除，均无法整除，则n为质数：
     * @Date 11:26 11:26
     * @Param [n]
     **/
    public static void sqrPrime(int num) {
        boolean[] isPrimes = new boolean[num + 1];
        for (int i = 2; i < isPrimes.length; i++) {
            isPrimes[i] = true;
        }

        for (int i = 3; i <= num; i++) {
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrimes[i] = false;
                    break;
                }
            }
        }
        System.out.print("质数有: ");
        for (int i = 2; i < isPrimes.length; i++) {
            if (isPrimes[i]) {
                System.out.print(i + " ");
            }
        }
    }

    /**
     * @return void
     * @Author chungaochen
     * @Description 埃氏筛选法求素数的基本思想是：
     * 把从1开始的、某一范围内的正整数从小到大顺序排列， 1不是素数，首先把它筛掉。
     * 剩下的数中选择最小的数是素数，然后去掉它的倍数。依次类推，直到筛子为空时结束。
     * @Date 11:56 11:56
     * @Param [n]
     **/
    public static void selectPrimes(int num) {
        boolean[] isPrimes = new boolean[num + 1];
        for (int i = 2; i < isPrimes.length; i++) {
            isPrimes[i] = true;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (isPrimes[i] == true) {
                //素数的倍数不是素数
                for (int j = 2; i * j <= num; j++) {
                    isPrimes[i * j] = false;
                }
            }
        }

        System.out.print("质数有: ");
        for (int i = 2; i < isPrimes.length; i++) {
            if (isPrimes[i]) {
                System.out.print(i + " ");
            }
        }
    }

    /**
     * @return boolean
     * @Author chungaochen
     * @Description 用6N±1法求素数:算法不正确多出很多素数，121不是素数。
     * <p>
     * 任何一个自然数，总可以表示成为如下的形式之一：
     * 6N，6N+1，6N+2，6N+3，6N+4，6N+5 (N=0，1，2，…)
     * 显然，当N≥1时，6N，6N+2，6N+3，6N+4都不是素数，只有形如6N+1和6N+5的自然数有可能是素数。所以，除了2和3之外，所有的素数都可以表示成6N±1的形式(N为自然数)。
     * 根据上述分析，我们可以构造另一面筛子，只对形如6 N±1的自然数进行筛选，这样就可以大大减少筛选的次数，从而进一步提高程序的运行效率和速度。
     * </P>
     * @Date 12:30 12:30
     * @Param [num]
     **/
    public static void sixNPrime(int num) {
        List<Integer> list = new ArrayList<>();
        if (num > 3) {
            list.add(2);
        }
        if (num > 5) {
            list.add(3);
        }
        if (num > 7) {
            list.add(5);
        }
        if (num > 10) {
            list.add(7);
            int k = 0;
            for (int i = 3; i < num; i = i + 3) {
                for (int j = 0; j < 2; j++) {
                    k = 2 * (i + j) - 1;
                    if ((k < num) && ( k % 5 == 0 ? false : (k % 7 == 0 ? false :true))) {
                        list.add(k);
                    }
                }
            }
        }

        Iterator i = list.iterator();
        System.out.print("质数有: ");
        while (i.hasNext()) {
            System.out.print(i.next() + " ");
        }
    }
}