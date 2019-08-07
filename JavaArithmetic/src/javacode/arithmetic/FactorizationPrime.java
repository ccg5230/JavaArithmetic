package javacode.arithmetic;

import java.util.LinkedList;
import java.util.List;

/**
 * @className FactorizationPrime
 * @Description 将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。
 * 每个合数都可以写成几个质数相乘的形式，其中每个质数都是这个合数的因数，而1既不属于质数也不属于合数。最小的合数是4。
 * 把一个合数用质因数相乘的形式表示出来，叫做分解质因数。如30=2×3×5 。分解质因数只针对合数。
 * 要从最小的质数除起，一直除到结果为质数为止。   
 * @Author chungaochen
 * Date 2019/8/1 17:00
 * Version 1.0
 **/
public class FactorizationPrime {

    public static void main(String[] args) {
        factorizationPrimefactor(30);
        factorizationPrimefactor(13);
    }

    public static void factorizationPrimefactor(long n) {
//        Scanner sc = new Scanner(System.in);
//        Long n = sc.nextLong();
        long m = n;
        int flag = 0;
        List<Long> l = new LinkedList<>();
        long i = 2; //从最小的质数2开始整除
        while (i <= n) {
            if (n % i == 0) {
                l.add(i);
                flag++;
                n = n / i;
                //从最小的质数2开始整除
                i = 2;
            } else {
                // 否则继续
                i++;
            }
        }
        if (flag < 2) //flag==1仅被自己整除了一次
            System.out.println(m + "为质数");
        else {
            System.out.print("\n" + m + "共有" + flag + "个质因数: ");
            l.forEach(e -> {
                System.out.print(e + " ");
            });
            System.out.println(" ");
        }
//        sc.close();
    }

    /**
     * @return boolean
     * @Author chungaochen
     * @Description sqr方法判断是否是质数
     * @Date 17:18 17:18
     * @Param [num]
     **/
    public static boolean isPrime(long num) {
        boolean isPrimes = true;
        for (int j = 2; j * j <= num; j++) {
            if (num % j == 0) {
                isPrimes = false;
                break;
            }
        }
        return isPrimes;
    }
}