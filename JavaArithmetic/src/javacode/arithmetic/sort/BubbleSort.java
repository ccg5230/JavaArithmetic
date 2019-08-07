package javacode.arithmetic.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @className BubbleSort
 * @Description 冒泡排序：数据交换次数多耗时长
 * <p>
 * 基本思想：两个数比较大小，较大的数下沉，较小的数冒起来。
 * 原理：
 * 1、从第一个数据开始，与第二个数据相比较，如果第二个数据小于第一个数据，则交换两个数据的位置。
 * 2、指针由第一个数据移向第二个数据，第二个数据与第三个数据相比较，如果第三个数据小于第二个数据，则交换两个数据的位置。
 * 3、依此类推，完成第一轮排序。第一轮排序结束后，最大的元素被移到了最右面。
 * 4、依照上面的过程进行第二轮排序，将第二大的排在倒数第二的位置。
 * 5、重复上述过程，没排完一轮，比较次数就减少一次。
 *
 * 时间复杂度
 * 若文件的初始状态是正序的，一趟扫描即可完成排序。所需的关键字比较次数C和记录移动次数M均达到最小值：Cmin=n-1,Mmin=0。
 * 所以，冒泡排序最好的时间复杂度为O(n)。
 * 若初始文件是反序的，需要进行  趟排序。每趟排序要进行  次关键字的比较(1≤i≤n-1)，且每次比较都必须移动记录三次来达到交换记录位置。
 * 在这种情况下，比较和移动次数均达到最大值：
 * Cmax=n(n-1)/2=O(n²)
 * Mmax=3n(N-1)/2=O(n²)
 * 冒泡排序的最坏时间复杂度为O(n²)。
 * 综上，因此冒泡排序总的平均时间复杂度为O(n²)。
 * 算法稳定性
 * 冒泡排序就是把小的元素往前调或者把大的元素往后调。比较是相邻的两个元素比较，交换也发生在这两个元素之间。所以，如果两个元素相等，
 * 是不会再交换的；如果两个相等的元素没有相邻，那么即使通过前面的两两交换把两个相邻起来，这时候也不会交换，所以相同元素的前后顺序并没有改变，所以冒泡排序是一种稳定排序算法。
 * </p>
 * @Author chungaochen
 * Date 2019/8/3 14:44
 * Version 1.0
 **/
public class BubbleSort {

    /**
     * @Author chungaochen
     * @Description 冒泡排序升序
     * @Date 15:24 15:24
     * @Param [arr]
     * @return void
     **/
    public static void bubbleSort(int[] arr) {
        if (arr == null)
            return;
        int len = arr.length;
        //i控制循环次数，长度为len的数组只需要循环len-1轮，i的起始值为0所以i<len-1
        for (int i = 0; i < len - 1; i++) {
            // j控制比较次数，第i次循环内需要比较len-i次
            // 但是由于是由arr[j]跟arr[j+1]进行比较，所以为了保证arr[j+1]不越界，j<len-i-1
            for (int j = 0; j < len - i - 1; j++) {//先找到最大数，指针从后往前移动
                // 如果前一个数比后一个数大，则交换位置将大的数往后放。
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void bubbleSort2(int [] arr){
        int temp;//临时变量
        for(int i=0; i<arr.length-1; i++){   //表示趟数，一共arr.length-1次。
            for(int j=arr.length-1; j>i; j--){
                if(arr[j] < arr[j-1]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
    }

    /**
     * @Author chungaochen
     * @Description
     * <p>
     * 优化：
     * 针对问题：
     * 数据的顺序排好之后，冒泡算法仍然会继续进行下一轮的比较，直到arr.length-1次，后面的比较没有意义的。
     *
     * 方案：
     * 设置标志位flag，如果发生了交换flag设置为true；如果没有交换就设置为false。
     * 这样当一轮比较结束后如果flag仍为false，即：这一轮没有发生交换，说明数据的顺序已经排好，没有必要继续进行下去。
     * </p>
     * @Date 15:35 15:35
     * @Param [arr]
     * @return void
     **/
    public static void BubbleSortImprove(int [] arr){

        int temp;
        boolean flag;//是否交换的标志
        for(int i=0; i<arr.length-1; i++){
            // 每次遍历标志位都要先置为false，才能判断后面的元素是否发生了交换
            flag = false;
            for(int j=arr.length-1; j>i; j--){ //选出该趟排序的最大值往后移动
                if(arr[j] < arr[j-1]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    flag = true;    //只要有发生了交换，flag就置为true
                }
            }
            // 判断标志位是否为false，如果为false，说明后面的元素已经有序，就直接return
            if(!flag) {
//                System.out.println("数组在本轮已经排序好的");
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] a1= new int[8];
        Random r = new Random(5);
        for(int i=0; i<8; i++) {
            int r1 = r.nextInt(6);//上限
            a1[i] = r1;
        }
        int[] ar= new int[30];
        for(int i=0; i<30; i++) {
            int max=20,min=1;
            int ran2 = (int) (Math.random()*(max-min)+min);
            ar[i] = ran2;
        }
        bubbleSort(a1);
        System.out.println(Arrays.toString(a1));
        bubbleSort2(a1);
        System.out.println(Arrays.toString(a1));
        System.out.println(Arrays.toString(ar));
        BubbleSortImprove(ar);
        System.out.println(Arrays.toString(ar));
    }
}