package javacode.arithmetic;

/**
 * @className NarcissisticNumber
 * @Description
 * @Author chungaochen
 * Date 2019/8/1 16:40
 * Version 1.0
 **/
public class NarcissisticNumber {
    public static void main(String[] args) {
        printNarcissistic(209);
    }

    /**
     * @Author chungaochen
     * @Description 打印出所有的 "水仙花数 "
     * 所谓 "水仙花数 "是指一个三位数，其各位数字立方和等于该数本身。
     * 例如：153是一个 "水仙花数 "，因为153=1的三次方＋5的三次方＋3的三次方。
     * @Date 16:42 16:42
     * @Param [num]
     * @return void
     **/
    public static  void printNarcissistic(int num) {
        int g,s,b;
        for(int i=100; i <=num; i++) {
            g = i % 10; //个位
            s = i / 10 % 10; //十位
            b = i / 100; //百位
            if(b*b*b + s*s*s + g*g*g ==i) {
                System.out.println(i);
            }
        }

    }
}