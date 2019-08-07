package javacode.arithmetic.sort;

import java.util.*;

/**
 * @className BucketSort
 * @Description 桶排序
 * <p>
 * 或所谓的箱排序，是一个排序算法，工作的原理是将数组分到有限数量的桶子里。
 * 每个桶子再个别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排序）。桶排序是鸽巢排序的一种归纳结果。
 * 当要被排序的数组内的数值是均匀分配的时候，桶排序使用线性时间（Θ（n））。但桶排序并不是 比较排序，他不受到 O(n log n) 下限的影响。
 * </p>
 *
 * <p>
 * 基本思想
 * 桶排序是计数排序的升级版。它利用了函数的映射关系，高效与否的关键就在于这个映射函数的确定。为了使桶排序更加高效，我们需要做到这两点：
 * 在额外空间充足的情况下，尽量增大桶的数量
 * 使用的映射函数能够将输入的 N 个数据均匀的分配到 K 个桶中
 * </p>
 * @Author chungaochen
 * Date 2019/8/6 17:17
 * Version 1.0
 **/
public class BucketSort {

    public static void main(String[] args) {
        Random rand = new Random();
        int length = 1000000;
        int[] a = new int[length];
        for (int i = 0; i < length; i++) {
            a[i] = rand.nextInt(70);
        }
        long one = System.currentTimeMillis();
        bucketSort(a);
        long two = System.currentTimeMillis();
        System.out.println("排序之后 \n" + Arrays.toString(a));
        System.out.println("桶排序用时(单位毫秒)：" + (two - one));//1000000长度:312ms
    }

    public static void bucketSort(int[] arr) {
        if (arr == null)
            return;
        int len = arr.length;
        // 定义桶的个数，这里k的值要视情况而定，这里我们假设待排序数组里的数都是[0,100)之间的。
        int k = 10;
        // 用嵌套集合来模拟桶，外层集合表示桶，内层集合表示桶里边装的元素。也可以用二维数组
        List<List<Integer>> bucket = new ArrayList<>();
        //for循环初始化外层集合即初始化桶
        for (int i = 0; i < k; i++) {
            bucket.add(new ArrayList<>());
        }
        // 循环是为了将待排序数组中的元素通过映射函数分别放入不同的桶里边
        for (int i = 0; i < len; i++) {
            bucket.get(mapping(arr[i])).add(arr[i]);
        }
        // 这个循环是为了将所有的元素个数大于1的桶里边的数据进行排序。
        for (int i = 0; i < k; i++) {
            if (bucket.size() > 1) {
                // 因为这里是用集合来模拟的桶所以用java写好的对集合排序的方法。
                // 其实应该自己写一个方法来排序的。
                Collections.sort(bucket.get(i));
            }

        }
        // 将排好序的数重新放入待排序数组中
        int m = 0;
        for (List<Integer> list : bucket) {
            if (list.size() > 0) {
                for (Integer a : list) {
                    arr[m++] = a;
                }
            }
        }
    }

    /**
     * 映射函数：相当于同样位数的数值在一个桶
     *
     * @param num
     * @return
     */
    public static int mapping(int num) {
        return num / 10;
    }

}