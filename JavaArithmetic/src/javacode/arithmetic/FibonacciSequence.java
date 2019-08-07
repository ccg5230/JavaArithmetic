package javacode.arithmetic;

/**
 * @className FibonacciSequence
 * @Description <p>
 * 斐波那契数列（Fibonacci sequence），又称黄金分割数列、因数学家列昂纳多·斐波那契（Leonardoda Fibonacci）以兔子繁殖为例子而引入，
 * 故又称为“兔子数列”，指的是这样一个数列：1、1、2、3、5、8、13、21、34、……在数学上，斐波纳契数列以如下被以递推的方法定义：
 * F(1)=1，F(2)=1, F(n)=F(n-1)+F(n-2)（n>=3，n∈N*）在现代物理、准晶体结构、化学等领域，斐波纳契数列都有直接的应用，
 * 为此，美国数学会从1963年起出版了以《斐波纳契数列季刊》为名的一份数学杂志，用于专门刊载这方面的研究成果。
 * </p>
 * @Author chungaochen
 * Date 2019/8/1 10:26
 * Version 1.0
 **/
public class FibonacciSequence {
    /**
     * @Author chungaochen
     * @Description
     * 古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，
     * 假如兔子都不死，问每个月的兔子总数为多少？
     * @Date 10:28 10:28
     * @Param [args]
     * @return void
     **/
    public static void main(String[] args) {
        System.out.println(fibonacciNormal(10));
        System.out.println(fibonacciRecursion(10));
        System.out.println(fibonacciArray(10));
    }

    /**
     * @Author chungaochen
     * @Description 递推实现方式
     * @Date 10:46 10:46
     * @Param [n] 输出多少项的值
     * @return int
     **/
    public static int fibonacciNormal(int n){
        if(n <= 2){
            return 1;
        }
        int n1 = 1, n2 = 1, sn = 0;
        for(int i = 3; i <= n; i ++){
            sn = n1 + n2;
            n1 = n2;
            n2 = sn;
        }
        return sn;
    }

    /**
     * @Author chungaochen
     * @Description 递归方式实现
     * @Date 10:52 10:52
     * @Param [n]
     * @return int
     **/
    public static int fibonacciRecursion(int n) {
        if(n <= 2) {
            return 1;
        }
        return fibonacciRecursion(n-1) + fibonacciRecursion(n-2);
    }

    /**
     * @Author chungaochen
     * @Description 使用数组
     * @Date 10:59 10:59
     * @Param [n]
     * @return int
     **/
    public static int fibonacciArray(int n) {
        if(n < 0) {
            return -1;
        }
        if(n <= 2) {
            return 1;
        }
        int[] fibon = new int[n];
        fibon[0] = fibon[1] =1;
        for(int i=2; i<n; i++) {
            fibon[i] = fibon[i-1] + fibon[i-2];
        }
        return fibon[n-1];
    }



}