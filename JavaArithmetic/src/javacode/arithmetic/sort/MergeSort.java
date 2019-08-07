package javacode.arithmetic.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @className MergeSort
 * @Description 归并排序
 * <P>
 * 归并排序（MERGE-SORT）是建立在归并操作上的一种有效的排序算法,该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，
 * 称为二路归并。
 * </P>
 * <p>
 * 平均时间复杂度：O(NlogN)
 * </p>
 * @Author chungaochen
 * Date 2019/8/5 21:33
 * Version 1.0
 **/
public class MergeSort {

    public static void main(String[] args) {
        Random rand = new Random();
        int length = 150;
        int[] a = new int[length];
        for (int i = 0; i < length; i++) {
            a[i] = rand.nextInt(100);
        }
        long one = System.currentTimeMillis();
        mergeSort(a, 0, length-1);
        long two = System.currentTimeMillis();
        System.out.println("排序之后 \n" + Arrays.toString(a));
        System.out.println("归并排序用时(单位毫秒)：" + (two - one));//80000长度:31
    }

    /**
     * 递归拆分
     * @param arr   待拆分数组
     * @param left  待拆分数组最小下标
     * @param right 待拆分数组最大下标
     */
    public static void mergeSort(int[] arr, int left, int right) {
        int mid = (left + right) / 2;  // 中间下标
        if (left < right) {
            mergeSort(arr, left, mid); // 递归拆分左半部分排好序
            mergeSort(arr, mid + 1, right); // 递归拆分右半部分排好序
            sort(arr, left, mid, right); // 合并左右
        }
    }

    /**
     * 合并两个有序子序列
     * @param arr   待合并数组
     * @param left  待合并数组最小下标
     * @param mid   待合并数组中间下标
     * @param right 待合并数组最大下标
     */
    private static void sort(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left +1]; // 临时数组，用来保存每次合并之后的结果
        int i = left;
        int j= mid + 1;
        int k =0; // 临时数组的初始下标
        // 这个while循环能够初步筛选出待合并的了两个子序列中的较小数
        while (i <= mid && j <= right) {
            if(arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        //将左边序列中剩余的数(较大数）放入临时数组
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        // 将右边序列中剩余的数(较大数）放入临时数组
        while ((j <= right)) {
            temp[k++] = arr[j++];
        }
        // 将临时数组中的元素位置对应到真真实的数组中
        for(int m = 0; m < temp.length; m++) {
            arr[m + left] = temp[m];
        }
    }
}