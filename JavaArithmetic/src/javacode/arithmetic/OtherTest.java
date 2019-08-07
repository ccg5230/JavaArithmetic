package javacode.arithmetic;

/**
 * @className OtherTest
 * @Description
 * @Author chungaochen
 * Date 2019/8/1 22:03
 * Version 1.0
 **/
public class OtherTest {
    public static void main(String[] args) {
//        getSumQuence(3);
//        pascalsTriangle(10);
        getPeach();
    }

    /**
     * @return double
     * @Author chungaochen
     * @Description 有一分数序列：2/1，3/2，5/3，8/5，13/8，21/13...求出这个数列的前n项之和
     * @Date 22:04 22:04
     * @Param [n]
     **/
    public static double getSumQuence(int num) {
        double sum = 0;
        double n = 2, d = 1;
        for (int i = 1; i <= num; i++) {
            sum += n / d;
            n = n + d;
            d = n - d;
        }
        System.out.println(sum);
        return sum;
    }

    /**
     * @Author chungaochen
     * @Description 打印出杨辉三角形（要求打印出10行如下图）      
     *            1   
     *           1   1   
     *         1   2    1   
     *       1    3   3    1   
     *     1    4    6   4    1   
     * 1    5    10   10    5    1   
     * @Date 22:22 22:22
     * @Param [num]
     * @return void
     **/
    public static void pascalsTriangle(int num) {
        int[][] a = new int[num][num];
        for(int i=0; i<num; i++) {
            a[i][i] = 1;
            a[i][0] = 1;
        }
        for(int i=2; i<num; i++) {
            for(int j=1; j<i; j++) {
                a[i][j] = a[i-1][j-1] + a[i-1][j];
            }
        }
        for(int i=0; i<num; i++) {
            for(int k=0; k<2*(num-i)-1; k++) {
                System.out.print(" ");
            }
            for(int j=0; j<=i; j++) {
                System.out.print(a[i][j] + "   ");
            }
            System.out.println();
        }
    }

    /**
     * @Author chungaochen
     * @Description 海滩上有一堆桃子，五只猴子来分。第一只猴子把这堆桃子凭据分为五份，多了一个，这只猴子把多的一个扔入海中，
     * 拿走了一份。第二只猴子把剩下的桃子又平均分成五份，又多了一个，它同样把多的一个扔入海中，拿走了一份，第三、第四、
     * 第五只猴子都是这样做的，问海滩上原来最少有多少个桃子？
     * @Date 22:48 22:48
     * @Param []
     * @return void
     **/
    public static void getPeach() {
        int i,m,j=0,k,count;
        for(i=4;i<10000;i+=4) {
            count=0;
            m=i;
            for(k=0;k<5;k++){
                j=i/4*5+1;
                i=j;
                if(j%4==0)
                    count++;
                else break;
            }
            i=m;
            if(count==4)
            {System.out.println("原有桃子 "+j+" 个");
                break;}
        }
    }

}
