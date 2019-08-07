package javacode.arithmetic.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @className SelctionSort
 * @Description 选择排序（Selection sort）是一种简单直观的排序算法。
 * <p>
 * 它的工作原理是：第一次从待排序的数据元素中选出最小（或最大）的一个元素，
 * 存放在序列的起始位置，然后再从剩余的未排序元素中寻找到最小（大）元素，然后放到已排序的序列的末尾。以此类推，
 * 直到全部待排序的数据元素的个数为零。选择排序是不稳定的排序方法。
 * </p>
 * <P>
 * 时间复杂度
 * 选择排序的交换操作介于 0 和 (n - 1） 次之间。选择排序的比较操作为 n (n - 1） / 2 次之间。选择排序的赋值操作介于 0 和 3 (n - 1） 次之间。
 * 比较次数O(n^2），比较次数与关键字的初始状态无关，总的比较次数N=(n-1）+(n-2）+...+1=n*(n-1）/2。交换次数O(n），最好情况是，已经有序，交换0次；
 * 最坏情况交换n-1次，逆序交换n/2次。交换次数比冒泡排序少多了，由于交换所需CPU时间比比较所需的CPU时间多，n值较小时，选择排序比冒泡排序快。 [1]
 * 其他排序算法的复杂度如右图所示。
 * 稳定性
 * 选择排序是给每个位置选择当前元素最小的，比如给第一个位置选择最小的，在剩余元素里面给第二个元素选择第二小的，依次类推，直到第n-1个元素，
 * 第n个元素不用选择了，因为只剩下它一个最大的元素了。那么，在一趟选择，如果一个元素比当前元素小，而该小的元素又出现在一个和
 * 当前元素相等的元素后面，那么交换后稳定性就被破坏了。比较拗口，举个例子，序列5 8 5 2 9，我们知道第一遍选择第1个元素5会和2交换，
 * 那么原序列中两个5的相对前后顺序就被破坏了，所以选择排序是一个不稳定的排序算法。 [1]
 * </P>
 * <p>
 * 选择排序可以说是冒泡排序的改良版，相对于冒泡排序来说，比较的次数并没有改变，但是数据交换的次数大大减少。
 * 平均时间复杂度：O(n2)
 * </p>

 * <p>
 * 排序算法有很多，包括插入排序，冒泡排序，堆排序，归并排序，选择排序，计数排序，基数排序，桶排序，快速排序等。
 * 插入排序，堆排序，选择排序，归并排序和快速排序，冒泡排序都是比较排序，它们通过对数组中的元素进行比较来实现排序，
 * 其他排序算法则是利用非比较的其他方法来获得有关输入数组的排序信息。
 * </p>
 * @Author chungaochen
 * Date 2019/8/3 16:09
 * Version 1.0
 **/
public class SelctionSort {

    /**
     * @Author chungaochen
     * @Description 选择排序，由于每一轮只选择最小（大）的数，所以不到最后一轮都不能确定是否排好序
     * @Date 16:31 16:31
     * @Param [arr]
     * @return void
     **/
    public static void selectSort(int[] arr) {
        if (arr == null)
            return;
        int len = arr.length;
        // i控制循环次数，长度为len的数组只需要循环len-1次
        for (int i = 0; i < len - 1; i++) {
            // minIndex 用来保存每次比较后较小数的下标。
            int minIndex = i;
            // j控制比较次数，因为每次循环结束之后最小的数都已经放在了最前面，
            // 所以下一次循环的时候就可以跳过这个数，所以j的初始值为i+1而不需要每次循环都从0开始，并且j<len即可
            for (int j = i + 1; j < len; j++) {
                //每比较一次都需要将较小数的下标记录下来
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            // 当完成一次循环时，就需要将本次循环选取的最小数移动到本次循环开始的位置。
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
            // 打印每次循环结束之后数组的排序状态（方便理解）
//            System.out.println("第" + (i + 1) + "次循环之后效果：" + Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        int[] arr= new int[30];
        Random r = new Random(5);
        for(int i=0; i<30; i++) {
            int r1 = r.nextInt(55);//上限
            arr[i] = r1;
        }
        System.out.println("排序之前：" + Arrays.toString(arr));
        selectSort(arr);
    }
}