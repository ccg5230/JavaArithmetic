package javacode.arithmetic.sort;

import java.util.Arrays;

/**
 * @className RadixSort
 * @Description 基数排序
 * <p>
 * 属于“分配式排序”（distribution sort），又称“桶子法”（bucket sort）或bin sort，顾名思义，它是透过键值的部份资讯，
 * 将要排序的元素分配至某些“桶”中，藉以达到排序的作用，基数排序法是属于稳定性的排序，其时间复杂度为O (nlog(r)m)，
 * 其中r为所采取的基数，而m为堆数，在某些时候，基数排序法的效率高于其它的稳定性排序法。
 * </p>
 *
 * <p>
 * 基数排序是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。
 * 由于整数也可以表达字符串（比如名字或日期）和特定格式的浮点数，所以基数排序也不是只能使用于整数。
 * </p>
 * @Author chungaochen
 * Date 2019/8/6 17:42
 * Version 1.0
 **/
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = {720, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        radixSort(arr, 10, 3);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * @Author chungaochen
     * @Description
     * @Date 17:56 17:56
     * @Param [arr, radix, d]
     * @return void
     **/
    public static void radixSort(int[] arr, int radix, int d) { //d表示最大的数有多少位
        // 缓存数组
        int[] tmp = new int[arr.length];
        // buckets用于记录待排序元素的信息
        // buckets数组定义了max-min个桶
        int[] buckets = new int[radix];

        for (int i = 0, rate = 1; i < d; i++) {

            // 重置count数组，开始统计下一个关键字
            Arrays.fill(buckets, 0);
            // 将data中的元素完全复制到tmp数组中
            System.arraycopy(arr, 0, tmp, 0, arr.length);

            // 计算每个待排序数据的子关键字
            for (int j = 0; j < arr.length; j++) {
                int subKey = (tmp[j] / rate) % radix;
                buckets[subKey]++;
            }

            for (int j = 1; j < radix; j++) {
                buckets[j] = buckets[j] + buckets[j - 1];
            }

            // 按子关键字对指定的数据进行排序
            for (int m = arr.length - 1; m >= 0; m--) {
                int subKey = (tmp[m] / rate) % radix;
                arr[--buckets[subKey]] = tmp[m];
            }
            rate *= radix;
        }
    }

}