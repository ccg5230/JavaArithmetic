package javacode.arithmetic.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @className InsertionSort
 * @Description 插入排序
 * <p>
 * 平均时间复杂度：O(n2)
 * 基本思想
 * 插入排序的思想打牌的人肯定很容易理解，就是见缝插针。首先就默认数组中的第一个数的位置是正确的，即已经排序。然后取下一个数，
 * 与已经排序的数按从后向前的顺序依次比较， 如果该数比当前位置排好序的数小，则将排好序的数的位置向后移一位。
 * 重复上一步骤，直到找到合适的位置。 找到位置后就结束比较的循环，将该数放到相应的位置。
 * 分类
 * 包括：直接插入排序，二分插入排序（又称折半插入排序），链表插入排序，希尔排序（又称缩小增量排序）。
 * 属于稳定排序的一种（通俗地讲，就是两个相等的数不会交换位置） 。
 * 直接插入排序
 * 直接插入排序是一种简单的插入排序法，其基本思想是：把待排序的记录按其关键码值的大小逐个插入到一个已经排好序的有序序列中，
 * 直到所有的记录插入完为止，得到一个新的有序序列。
 * 折半插入排序（二分插入排序）
 * 将直接插入排序中寻找A[i]的插入位置的方法改为采用折半比较，即可得到折半插入排序算法。在处理A[i]时，A[0]……A[i-1]已经按关键码值排好序。
 * 所谓折半比较，就是在插入A[i]时，取A[i-1/2]的关键码值与A[i]的关键码值进行比较，如果A[i]的关键码值小于A[i-1/2]的关键码值，
 * 则说明A[i]只能插入A[0]到A[i-1/2]之间，故可以在A[0]到A[i-1/2-1]之间继续使用折半比较；否则只能插入A[i-1/2]到A[i-1]之间，
 * 故可以在A[i-1/2+1]到A[i-1]之间继续使用折半比较。如此担负，直到最后能够确定插入的位置为止。一般在A[k]和A[r]之间采用折半，
 * 其中间结点为A[k+r/2]，经过一次比较即可排除一半记录，把可能插入的区间减小了一半，故称为折半。执行折半插入排序的前提是文件记录必须按顺序存储。
 * </p>
 * @Author chungaochen
 * Date 2019/8/3 16:32
 * Version 1.0
 **/
public class InsertionSort {

    public static void main(String[] args) {
        Random rand = new Random();
        int length = 80000;
        int[] a = new int[length];
        int[] b = new int[length];
        int[] c = new int[length];
        int[] d = new int[length];
        int[] e = new int[length];
        for (int i = 0; i < length; i++) {
            a[i] = rand.nextInt(100);
            b[i] = a[i];
            c[i] = a[i];
            d[i] = a[i];
            e[i] = a[i];
        }
//        System.out.println("a排序之前 \n" +Arrays.toString(a));
//        System.out.println("b排序之前 \n" +Arrays.toString(b));
//        System.out.println("c排序之前 \n" +Arrays.toString(c));
//        System.out.println("d排序之前 \n" +Arrays.toString(d));

        long one = System.currentTimeMillis();
        insertSortImprove(a);
        long two = System.currentTimeMillis();
//        System.out.println("a排序之后 \n" +Arrays.toString(a));

        BubbleSort.BubbleSortImprove(b);
        long three = System.currentTimeMillis();
//        System.out.println("b排序之后 \n" +Arrays.toString(b));


        SelctionSort.selectSort(c);
        long four = System.currentTimeMillis();
//        System.out.println("c排序之后 \n" +Arrays.toString(c));


        binaryInsert(d);
        long five = System.currentTimeMillis();
//        System.out.println("d排序之后 \n" +Arrays.toString(d));

        ShellSort.shellSort(e);
        long six = System.currentTimeMillis();

        System.out.println("数组长度值为：" + length +" ================");
        System.out.println("直接插入排序用时(单位毫秒)：" + (two - one));//80000长度:6372,改进型1031
        System.out.println("冒泡排序用时(单位毫秒)：" + (three - two));//80000长度:14138
        System.out.println("选择排序用时(单位毫秒)：" + (four - three));//80000长度:3824
        System.out.println("二分插入排序用时(单位毫秒)：" + (five - four));//80000长度:758
        System.out.println("希尔（插入）排序用时(单位毫秒)：" + (six - five));//80000长度:16
    }

    /**
     * @return void
     * @Author chungaochen
     * @Description 直接插入法
     * @Date 22:50 22:50
     * @Param [array]
     **/
    public static void insertSort(int array[]) {
        int length = array.length;
        for (int i = 1; i < length; i++) {//从数组第二个元素開始排序，由于第一个元素本身肯定是已经排好序的
            //为后面未排序的1个数找到在已排序列里中的位置插入
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) { //找到一个比待插入大的数，则交换
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * @return void
     * @Author chungaochen
     * @Description 针对上面的这个排序算法改进：首先上面的这个每次替换都要定义一个temp赋值需要插入的数，这样会造成不必要的浪费：
     * 所以我们可以吧所有的大于需要插入的数先保存，然后进行比较，然后将最后的正确位置空出来。吧之前保存的需要插入的数放到正确位置上；
     * 如：arr={1,2,3,5,2} 对最后一个2进行插入，
     * @Date 15:47 15:47
     * @Param [array]
     **/
    public static void insertSortImprove(int array[]) {
        int length = array.length;
        for (int i = 1; i < length; i++) {//从第二个数开始才需要插入
            int temp = array[i]; //需要插入的数先保存起来，
            int j = 0;
            for (j = i; j > 0 && array[j - 1] > temp; j--) {//这个较上面有一定的优化
                array[j] = array[j - 1]; //把大于需要插入的数往后移动。最后不大于temp的数就空出来j
            }
            array[j] = temp; //j--之后，不大于temp即为插入位置
        }
    }

    public static void binaryInsert(int[] ary) {
        int setValueCount = 0;
        int len = ary.length;
        // 从数组第二个元素開始排序，由于第一个元素本身肯定是已经排好序的
        for(int i=1; i<len; i++) { //复杂度n
            int needInsert = ary[i];
            //利用二分查找定位插入位置
//            int index = binarySearchAsc(ary, ary[i], 0, i-1); //复杂度：O(logn)
            int index = binarySearchAsc2(ary, ary[i], 0, i-1); //复杂度：O(logn)
            //将目标插入数组位置，同时右移目标位置及右边的元素
            for(int j=i; j>index; j--) {
                ary[j] = ary[j-1];//从末位开始移动，由于末位数据已保存到needInsert
                setValueCount++;
            }
            ary[index] = needInsert;
            setValueCount++;
        }
        System.out.println("\n 设值次数(setValueCount)=====> " + setValueCount);
    }

    /**
     * @Author chungaochen
     * @Description 二分查找 升序 递归
     * @Date 16:21 16:21
     * @Param ary, 给定已排序的待查数组
     * @Param target 查找目标
     * @Param  from, to 当前查找的范围起止点
     * @return int 返回目标在数组中，按顺序应在的位置
     **/
    private static int binarySearchAsc(int[] ary, int target, int from, int to) {
        int range = to - from;
        // 假设范围大于0，即存在两个以上的元素，则继续拆分
        if (range > 0) {
            // 选定中间位
            int mid = (to + from) / 2;
            // 假设临界位不满足，则继续二分查找
            if (ary[mid] > target) {
                /*
                 * mid > target, 升序规则，target较小，应交换位置 前置， 即target定位在mid位置上，
                 * 依据 查找思想， 从from到 mid-1觉得有序， 所以to=mid-1
                 */
                return binarySearchAsc(ary, target, from, mid - 1);
            } else {
                /*
                 * mid < target, 升序规则，target较大，不交换位置，查找比較的起始位置应为mid+1
                 */
                return binarySearchAsc(ary, target, mid + 1, to);
            }
        } else {
            if (ary[from] > target) {//如 5,4, 要插入的是4
                return from;
            } else {
                return from + 1;
            }
        }
    }

    /**
     * @Author chungaochen
     * @Description 二分查找 降序 递归
     * @Date 16:21 16:21
     * @Param ary, 给定已排序的待查数组
     * @Param target 查找目标
     * @Param  from, to 当前查找的范围起止点
     * @return int 返回目标在数组中，按顺序应在的位置
     **/
    private static int binarySearchDesc(int[] ary, int target, int from, int to) {
        int range = to - from;
        if (range > 0) {
            int mid = (from + to) >>> 1;
            if (ary[mid] > target) {
                return binarySearchDesc(ary, target, mid + 1, to);
            } else {
                return binarySearchDesc(ary, target, from, mid - 1);
            }
        } else {
            if (ary[from] > target) {//如 5,4, 要插入的是4
                return from + 1;
            } else {
                return from;
            }
        }
    }

    /**
     * 二分查找 降序， 非递归
     */
    private static int binarySearchDesc2(int[] ary, int target, int from, int to) {
        for (; from < to; ) {
            int mid = (from + to) >>> 1;
            if (ary[mid] > target) {
                from = mid + 1;
            } else {
                to  = mid -1;
            }
        }
        if (ary[from] > target) {//如 5,4, 要插入的是4
            return from + 1;
        } else {
            return from;
        }
    }

    /**
     * 二分查找 升序， 非递归
     */
    private static int binarySearchAsc2(int[] ary, int target, int from, int to) {
        for (; from < to; ) {
            int mid = (from + to) >>> 1;
            if (ary[mid] > target) {
                to  = mid -1;
            } else {
                from = mid + 1;
            }
        }
        if (ary[from] > target) {//如 5,4, 要插入的是4
            return from ;
        } else {
            return from + 1;
        }
    }
}