package javacode.arithmetic.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @className Quicksort
 * @Description 快速排序（Quicksort）是对冒泡排序的一种改进。
 * 基本思想：（分治）
 *
 * 先从数列中取出一个数作为key值；
 * 将比这个数小的数全部放在它的左边，大于或等于它的数全部放在它的右边；
 * 对左右两个小数列重复第二步，直至各区间只有1个数。
 * 平均时间复杂度：O(N*logN)
 *
 * 算法特性
 * 在平均状况下，快速排序排序 n 个项目需要要 Ο(nlogn) 次比较，在最坏状况下则需要 Ο(n2) 次比较，但这种状况并不常见。
 * 事实上，快速排序通常明显比其他 Ο(nlogn) 算法更快，因为它的内部循环（inner loop）可以在大部分的架构上很有效率地被实现出来。
 * 快速排序的最坏运行情况是 O(n²)，比如说顺序数列的快排。但它的平摊期望时间是 O(nlogn)，且 O(nlogn) 记号中隐含的常数因子很小，
 * 比复杂度稳定等于 O(nlogn) 的归并排序要小很多。所以，对绝大多数顺序性较弱的随机数列而言，快速排序总是优于归并排序。
 * @Author chungaochen
 * Date 2019/8/4 23:21
 * Version 1.0
 **/
public class Quicksort {

    public static void main(String[] args) {
        Random rand = new Random();
        int length = 80000;
        int[] a = new int[length];
        for (int i = 0; i < length; i++) {
            a[i] = rand.nextInt(100);
        }
        long one = System.currentTimeMillis();
        quickSort(a, 0, length-1);
        long two = System.currentTimeMillis();
        System.out.println("排序之后 \n" + Arrays.toString(a));
        System.out.println("快速排序用时(单位毫秒)：" + (two - one));//80000长度:187
    }

    /**
     * 分区过程
     * @param arr   待分区数组
     * @param start  待分区数组最小下标
     * @param end 待分区数组最大下标
     */
    public static void quickSort(int[] arr, int start, int end) {
        int pivot = arr[start];
        int i = start;
        int j = end;
        while (i<j) {
            while ((i<j)&&(arr[j]>pivot)) { //从右向左找第一个小于key的值
                j--;
            }
            while ((i<j)&&(arr[i]<pivot)) { //从左向右找第一个大于key的值
                i++;
            }
            if ((arr[i]==arr[j])&&(i<j)) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        if (i-1>start) quickSort(arr,start,i-1); //递归调用
        if (j+1<end) quickSort(arr,j+1,end); //递归调用
    }

    /**
     * @Author chungaochen
     * @Description 减少交换次数，提高效率
     * @Date 23:55 23:55
     * @Param [targetArr, start, end]
     * @return void
     **/
    private<T extends Comparable<? super T>> void quickSortImprove(T[]targetArr,int start,int end) {
        int i=start,j=end;
        T key=targetArr[start];

        while(i<j)
        {
            /*按j--方向遍历目标数组，直到比key小的值为止*/
            while(j>i&&targetArr[j].compareTo(key)>=0)
            {
                j--;
            }
            if(i<j)
            {
                /*targetArr[i]已经保存在key中，可将后面的数填入*/
                targetArr[i]=targetArr[j];
                i++;
            }
            /*按i++方向遍历目标数组，直到比key大的值为止*/
            while(i<j&&targetArr[i].compareTo(key)<=0)
            /*此处一定要小于等于零，假设数组之内有一亿个1，0交替出现的话，而key的值又恰巧是1的话，那么这个小于等于的作用就会使下面的if语句少执行一亿次。*/
            {
                i++;
            }
            if(i<j)
            {
                /*targetArr[j]已保存在targetArr[i]中，可将前面的值填入*/
                targetArr[j]=targetArr[i];
                j--;
            }
        }
        /*此时i==j*/
        targetArr[i]=key;

        /*递归调用，把key前面的完成排序*/
        if (i-1>start) this.quickSortImprove(targetArr,start,i-1);
        /*递归调用，把key后面的完成排序*/
        if (j+1<end) this.quickSortImprove(targetArr,j+1,end);
    }

}