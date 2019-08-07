package javacode.arithmetic;

/**
 * @className DayOfYear
 * @Description
 * @Author chungaochen
 * Date 2019/8/1 19:21
 * Version 1.0
 **/
public class DayOfYear {
    public static void main(String[] args) {
        int year = 2019,month=8,day=1;
        int days = getDayOfYear(2019,8,1);
        System.out.println(year + "-" + month + "-" + day + "是这年的第" + (days) + "天。");

    }

    public static int getDayOfYear(int year, int month, int day) {
        if (year < 0 || month < 0 || month > 12 || day < 0 || day > 31) {
            System.out.println("输入错误，请重新输入！");
            return -1;
        }
        int d = 0;
        int days = 0;
        for (int i = 1; i < month; i++) {
            switch (i) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    days = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    days = 30;
                    break;
                case 2:
                    if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
                        days = 29;
                    } else {
                        days = 28;
                    }
                    break;
            }
            d += days;
        }
        return d+day;

    }

}