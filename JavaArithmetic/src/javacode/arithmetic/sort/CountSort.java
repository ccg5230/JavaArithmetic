package javacode.arithmetic.sort;

import java.util.Arrays;
import java.util.Random;

import static javacode.arithmetic.sort.MergeSort.mergeSort;

/**
 * @className CountSort
 * @Description 计数排序:
 * 是一个非基于比较的排序算法，该算法于1954年由 Harold H. Seward 提出。它的优势在于在对一定范围内的整数排序时，
 * 它的复杂度为Ο(n+k)（其中k是整数的范围），快于任何比较排序算法。 [1]  当然这是一种牺牲空间换取时间的做法，
 * 而且当O(k)>O(n*log(n))的时候其效率反而不如基于比较的排序（基于比较的排序的时间复杂度在理论上的下限是O(n*log(n)),
 * 如归并排序，堆排序）
 * @Author chungaochen
 * Date 2019/8/6 16:25
 * Version 1.0
 **/
public class CountSort {

    public static void main(String[] args) {
        Random rand = new Random();
        int length = 20;
        int[] a = new int[length];
        for (int i = 0; i < length; i++) {
            a[i] = rand.nextInt(70);
        }
        long one = System.currentTimeMillis();
        countSort(a);
        long two = System.currentTimeMillis();
        System.out.println("排序之后 \n" + Arrays.toString(a));
        System.out.println("计数排序用时(单位毫秒)：" + (two - one));//800000长度:16ms
    }

    public static void countSort(int[] arr) {
        if(arr == null)
            return;
        int len = arr.length;
        // 保存待排序数组中的最大值，目的是确定临时数组的长度（必须）
        int maxNum = arr[0];
        //保存待排序数组中的最小值，目的是确定最终遍历临时数组时下标的初始值（非必需，只是这样可以加快速度，减少循环次数）
        int minNum = arr[0];
        // for循环就是为了找到待排序数组的最大值和最小值
        for(int i =1; i<len;i++) {
            maxNum = maxNum > arr[i] ? maxNum : arr[i];
            minNum = minNum < arr[i] ? minNum : arr[i];
        }
        //创建一个临时数组:这里k的大小是要排序的数组中，元素大小的极值差+1 :
        // 但是还是浪费了很多空间，因为如果数值差值临时数组大小会远远大于待排序数组
        int k = maxNum - minNum +1;
        int[] tempArr = new int[k];
        // for循环是为了记录待排序数组中每个元素出现的次数，并将该次数保存到临时数组中
        //临时数组的下标大小也与排序数组值大小有关：
        for(int i=0; i<len; ++i) {
            tempArr[arr[i] - minNum]++;//优化过的地方，减小了数组tempArr的大小
        }
        // n=0用来记录待排序数组的下标
        int n =0;
        // 遍历临时数组，重新为待排序数组赋值。
        for(int j=0; j<tempArr.length;j++){
            //临时数组下标即为待排序数组大小前后位置，且下标j = arr[m]-minNum,数组值为待排序数组值出现次数
            while (tempArr[j] >0) {//
                arr[n++] = j + minNum;
                tempArr[j]--;
            }
        }
    }
}