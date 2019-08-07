package javacode.arithmetic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @className GCDAndLCM
 * @Description <P>
 * 最大公约数(Greatest Common Divisor(GCD))
 * 最大公因数，也称最大公约数、最大公因子，指两个或多个整数共有约数中最大的一个。a，b的最大公约数记为（a，b），
 * 同样的，a，b，c的最大公约数记为（a，b，c），多个整数的最大公约数也有同样的记号。求最大公约数有多种方法，
 * 常见的有质因数分解法、短除法、辗转相除法、更相减损法。与最大公约数相对应的概念是最小公倍数，a，b的最小公倍数记为[a，b]。
 * </P>
 * <p>
 * 最小公倍数(Least Common Multiple(LCM))
 * 两个或多个整数公有的倍数叫做它们的公倍数，其中除0以外最小的一个公倍数就叫做这几个整数的最小公倍数。整数a，b的最小公倍数记为[a，b]，
 * 同样的，a，b，c的最小公倍数记为[a，b，c]，多个整数的最小公倍数也有同样的记号。
 * 与最小公倍数相对应的概念是最大公约数，a，b的最大公约数记为（a，b）。关于最小公倍数与最大公约数，
 * 我们有这样的定理：(a,b)[a,b]=ab(a,b均为整数)
 * </p>
 * @Author chungaochen
 * Date 2019/8/1 17:55
 * Version 1.0
 **/
public class GCDAndLCM {

    public static void main(String[] args) {
        long timePoint1 = System.currentTimeMillis();
        int gcd = divisionAlgorithm(3000000, 5000000);
        System.out.println(gcd);
        long timePoint2 = System.currentTimeMillis();
        System.out.println(primeGCD(3000000, 5000000));
        long timePoint3 = System.currentTimeMillis();
        System.out.println(equalGCD(3000000, 5000000));
        long timePoint4 = System.currentTimeMillis();
        System.out.println(shortDivision(3000000, 5000000));
        long timePoint5 = System.currentTimeMillis();
        System.out.println(exhaustion(3000000, 5000000));
        long timePoint6 = System.currentTimeMillis();
        System.out.println("辗转相除法(耗时: " + String.valueOf(timePoint2 - timePoint1)); //1ms
        System.out.println("质因数分解法(耗时: " + String.valueOf(timePoint3 - timePoint2));//54ms
        System.out.println("更相减损术 (耗时: " + String.valueOf(timePoint4 - timePoint3));//0ms
        System.out.println("短除法(耗时: " + String.valueOf(timePoint5 - timePoint4));//0ms
        System.out.println("穷举法(耗时: " + String.valueOf(timePoint6 - timePoint5));//21ms
        //最小公倍数
        System.out.println("最小公倍数: " +  (3000000*5000000/gcd));
    }

    /**
     * @return int
     * @Author chungaochen
     * @Description 辗转相除法(欧几里德算法)  高效稳定
     * 用辗转相除法求几个数的最大公约数，可以先求出其中任意两个数的最大公约数，再求这个最大公约数与第三个数的最大公约数，依次求下去，
     * 直到最后一个数为止。最后所得的那个最大公约数，就是所有这些数的最大公约数。
     * @Date 18:06 18:06
     * @Param [m, n]
     **/
    public static int  divisionAlgorithm(int m, int n) {
        int result = 0;
        if (m < n) {// 保证被除数大于除数
            m = m + n;
            n = m - n;
            m = m -n;
        }
        while (n != 0) {
            result = m % n;
            m = n;
            n = result;
        }
        return m;
    }

    /**
     * @Author chungaochen
     * @Description
     * 质因数分解法：质因数分解法，将两个数的所有质因子分解出来，然后将公共的因子
     *相乘，得到的就是最大公约数，速度也很慢
     * @Date 18:10 18:10
     * @Param [m, n]
     * @return int
     **/
    public static int primeGCD(int m, int n) {
        int result = 1;
        Set<Integer> set1 = getFactor(m);
        Set<Integer> set2 = getFactor(n);
        // 取交集
        set1.retainAll(set2);
        // 取最大
        result = Collections.max(set1);
        return result;
    }

    /**
     * @Author chungaochen
     * @Description 更相减损术”,也叫更相减损术，出自《九章算术》 <br>
     * 即“可半者半之，不可半者，副置分母、子之数，以少减多，更相减损，求其等也。以等数约之。”
     * @Date 18:12 18:12
     * @Param [m, n]
     * @return int
     **/
    public static int equalGCD(int m, int n) {
        while (m != n) {
            if (m > n)
                m -= n;
            else
                n -= m;
        }
        return m;
    }

    /**
     * @Author chungaochen
     * @Description
     * <p>短除法: 先用这几个数的公约数连续去除，一直除到所有的商互质为止,
     * 然后把所有的除数连乘起来，所得的积就是这几个数的最大公约数。</p>
     * 短除法本质上还是质因数分解法   <br>
     * 缺点：当质因数较大时，会计算比较困难
     * @Date 18:32 18:32
     * @Param [num1, num2]
     * @return int
     **/
    public static int shortDivision(int num1, int num2) {
        // 先获得绝对值，保证负数也可以求
        num1 = Math.abs(num1);
        num2 = Math.abs(num2);
        // 区分数值大小，为后面终止条件做准备
        int min = num1 > num2 ? num2 : num1;
        int max = num1 > num2 ? num1 : num2;
        // 设初始最大公约数为 1
        int gcd = 1;
        // 连续求出两个数的公约数，并累积，直到两个数互质（除了1，没有其它公约数）
        // 终止条件就是当 i 一直判断到 较小的那个数，都还没发现公约数，即可认定互质
        int i = 2;
        while (i <= min) {
            // 如果 i 是两个数的约数，则进行短除
            if (min % i == 0 && max % i == 0) { //找到一个公约数
                min /= i;
                max /= i;
                gcd *= i;   // 公约数累积
                i = 2;      // i 从 2 开始，重新开始找商的约数
            } else {
                // 否则继续判断
                i++;
            }
        }
        return gcd;
    }

    /**
     * @Author chungaochen
     * @Description 简单穷举法, 从2开始到较小的数, 速度最慢
     * @Date 18:25 18:25
     * @Param [num1, num2]
     * @return int
     **/
    public static int exhaustion(int num1, int num2) {
        // 先获得绝对值，保证负数也可以求
        num1 = Math.abs(num1);
        num2 = Math.abs(num2);
        // 找到小的那个数
        int min = num1 > num2 ? num2 : num1;
        // 初始最大公约数为 1
        int gcd = 1;
        // 穷举, 直接从 2 开始
        for (int i = 2; i <= min; i++) {
            // 如果 i 能被两个数同时约分，则是它们的公约数，但不一定是最大的
            if (num1 % i == 0 && num2 % i == 0) {
                // gcd 从最小的公约数，一直到最大的公约数
                gcd = i;
            }
        } // for
        return gcd;
    }

    /**
     * @Author chungaochen
     * @Description 获取某一数值的所有因数
     * @Date 18:09 18:09
     * @Param [m]
     * @return java.util.Set<java.lang.Integer>
     **/
    private static Set<Integer> getFactor(int m) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 2; i <= m; i++) {
            if (m % i == 0) {
                set.add(i);
            }
        }
        return set;
    }
}