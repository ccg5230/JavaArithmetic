package javacode.arithmetic.sort;

import java.util.Arrays;
import java.util.Random;

import static javacode.arithmetic.sort.MergeSort.mergeSort;

/**
 * @className HeapSort
 * @Description 堆排序:
 * <p>
 * 堆排序（Heapsort）是指利用堆这种数据结构所设计的一种排序算法。堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：
 * 即子结点的键值或索引总是小于（或者大于）它的父节点。堆排序可以说是一种利用堆的概念来排序的选择排序。
 * 分为两种方法：
 * 大顶堆：每个结点的值都大于它的左右子结点的值，升序排序用大顶堆。
 * 小顶堆：每个结点的值都小于它的左右子结点的值，降序排序用小顶堆。
 * 平均时间复杂度：O(NlogN)
 * </p>
 * <p>
 * 算法步骤
 * 创建一个堆 H[0……n-1]；
 * 把堆首（最大值）和堆尾互换；
 * 把堆的尺寸缩小 1，并调用 shift_down(0)，目的是把新的数组顶端数据调整到相应位置；
 * 重复步骤 2，直到堆的尺寸为 1。
 * </p>
 * @Author chungaochen
 * Date 2019/8/6 15:22
 * Version 1.0
 **/
public class HeapSort {
    public static void main(String[] args) {
        Random rand = new Random();
        int length = 80;
        int[] a = new int[length];
        for (int i = 0; i < length; i++) {
            a[i] = rand.nextInt(100);
        }
        long one = System.currentTimeMillis();
        mergeSort(a, 0, length-1);
        long two = System.currentTimeMillis();
        System.out.println("排序之后 \n" + Arrays.toString(a));
        System.out.println("堆排序用时(单位毫秒)：" + (two - one));//500000长度:124
    }

    /**
     * 选择排序-堆排序
     *
     * @param array 待排序数组
     * @return 已排序数组
     */
    public static int[] heapSort(int[] array) {

        // 初始化大顶堆（从最后一个非叶节点开始，从左到右，由下到上）
        //这里元素的索引是从0开始的,所以最后一个非叶子结点array.length/2 - 1
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);  //调整堆
        }

        // 下面，开始排序逻辑
        for (int j = array.length - 1; j > 0; j--) {
            // 元素交换,作用是去掉大顶堆
            // 把大顶堆的根元素，放到数组的最后；换句话说，就是每一次的堆调整之后，都会有一个元素到达自己的最终位置
            swap(array, 0, j);
            // 元素交换之后，毫无疑问，最后一个元素无需再考虑排序问题了。
            // 接下来我们需要排序的，就是已经去掉了部分元素的堆了，这也是为什么此方法放在循环里的原因
            // 而这里，实质上是自上而下，自左向右进行调整的
            adjustHeap(array, 0, j);
        }
        return array;
    }

    /**
     * 整个堆排序最关键的地方
     *
     * @param array  待组堆
     * @param i      起始结点
     * @param length 堆的长度
     */
    public static void adjustHeap(int[] array, int i, int length) {
        // 先把当前元素取出来，因为当前元素可能要一直移动
        int temp = array[i];
        //2*i+1为左子树i的左子树(因为i是从0开始的),2*k+1为k的左子树
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            // 让k先指向子节点中最大的节点
            if (k + 1 < length && array[k] < array[k + 1]) {  //如果有右子树,并且右子树大于左子树
                k++;
            }
            //如果发现结点(左右子结点)大于根结点，则进行值的交换
            if (array[k] > temp) {
                swap(array, i, k);
                // 如果子节点更换了，那么，以子节点为根的子树会受到影响,所以，循环对子节点所在的树继续进行判断
                i = k;  //这一步决定了下一个步骤执行到的是左子树还是右子树
            } else {  //不用交换，直接终止循环
                break;
            }
        }
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param a   元素的下标
     * @param b   元素的下标
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}