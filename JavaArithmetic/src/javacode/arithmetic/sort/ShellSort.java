package javacode.arithmetic.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @className ShellSort
 * @Description 希尔排序(Shell's Sort)
 * <p>
 * 是插入排序的一种又称“缩小增量排序”（Diminishing Increment Sort），
 * 是直接插入排序算法的一种更高效的改进版本。希尔排序是非稳定排序算法。该方法因D.L.Shell于1959年提出而得名。
 * </p>
 * <p>
 * 基本思想：
 * 在要排序的一组数中，根据某一增量分为若干子序列，并对子序列分别进行插入排序。
 * 然后逐渐将增量减小,并重复上述过程。直至增量为1,此时数据序列基本有序,最后进行插入排序。
 * </p>
 * @Author chungaochen
 * Date 2019/8/4 17:12
 * Version 1.0
 **/
public class ShellSort {
    public static void main(String[] args) {
        Random rand = new Random();
        int length = 80;
        int[] a = new int[length];
        for (int i = 0; i < length; i++) {
            a[i] = rand.nextInt(16);
        }
        long one = System.currentTimeMillis();
        shellSort(a);
        long two = System.currentTimeMillis();
        System.out.println("c排序之后 \n" +Arrays.toString(a));
        System.out.println("数组长度值为：" + length +" ================");
        System.out.println("希尔排序用时(单位毫秒)：" + (two - one));//80000长度:6372
    }

    public static void shellSort(int[] arr) {
        if(arr == null)
            return;
        int len = arr.length;
        int k = len /2; // 初始的增量为数组长度的一半
        // while循环控制按增量的值来划不同分子序列，每完成一次增量就减少为原来的一半
        // 增量的最小值为1，即最后一次对整个数组做直接插入排序
        while (k>0) {
            // 里边其实就是升级版的直接插入排序了，是对每一个子序列进行直接插入排序，
            // 所以直接将直接插入排序中的‘1’变为‘k’就可以了。
            for(int i=k; i<len; i++) { //根据增量分为若干子序列
                int j = i;
                int target = arr[i];
                while (j>=k && target<arr[j-k]) {
                    arr[j] = arr[j-k];
                    j -= k;
                }
                arr[j] = target;
            }
//            System.out.println("增量为"+k+"排序之后" + Arrays.toString(arr));
            k /= 2;
        }


    }
}