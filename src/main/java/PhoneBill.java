import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *.author:d
 *
 */
public class PhoneBill {
  private final int num1000 = 1000;
  private final int num60 = 60;
  private final int num600 = 60000;
  private final int num25 = 25;
  private final int num3 = 3;

  String getBill(final String time, final String time0) throws ParseException {
    double sum;
    SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
    Date date1 = format1.parse(time);
    Date date2 = format1.parse(time0);
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(date1);
    Calendar cal2 = Calendar.getInstance();
    cal2.setTime(date2);
    long phonetime = cal2.getTime().getTime() - cal1.getTime().getTime();
    long minute;
    if (phonetime >= 0 && phonetime / num1000 < num60 || phonetime % num600 > 0) {
      minute = phonetime / num1000 / num60 + 1;
    } else {
      minute = phonetime / num1000 / num60;
    }
    if (cal1.get(Calendar.MONTH) == Calendar.OCTOBER
                && cal1.get(Calendar.DATE) >= num25
                && cal1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
      if (cal1.get(Calendar.HOUR_OF_DAY) == 2
                    && cal2.get(Calendar.HOUR_OF_DAY) == 2
                    && cal2.get(Calendar.HOUR_OF_DAY) < num3
                    && cal2.get(Calendar.MINUTE) <= cal1.get(Calendar.MINUTE)) {
        minute += num60;
      }
      sum = money(minute);
    } else {
      if (cal2.get(Calendar.MONTH) == Calendar.OCTOBER
              && cal2.get(Calendar.DATE) >= num25
              && cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
        if (cal2.get(Calendar.HOUR_OF_DAY) >= 2) {
          minute += num60;
        }
      } else {
        if ((cal1.get(Calendar.MONTH) == Calendar.MARCH && cal1.get(Calendar.DATE) >= num25
                              && cal1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
          if (cal1.get(Calendar.HOUR_OF_DAY) < 2
                  && cal2.get(Calendar.HOUR_OF_DAY) >= num3) {
            minute -= num60;
          }
        } else {
          if ((cal2.get(Calendar.MONTH) == Calendar.MARCH
                  && cal2.get(Calendar.DATE) >= num25
                  && cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
            if (cal2.get(Calendar.HOUR_OF_DAY) >= num3) {
              minute -= num60;
            }
          }
        }
      }
      sum = money(minute);
    }
    SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
    String starttime = format2.format(date1);
    String endtime = format2.format(date2);
    return Double.toString(sum);
  }

  /**
   *.author:d
   *
   */

  public static double money(final long minute1) {

    long minute = minute1;
    final int num100 = 100;
    final int num0 = 0;
    final int num12 = 1200;
    final int num20 = 20;
    final double num5 = 0.05;
    final double num01 = 0.1;
    final double num1 = 1;
    double money = num0;
    if (minute > num12 || minute <= num0) {
      System.out.println("通话时间输入错误，请重新输入");
    } else {
      if (minute <= num20) {
        money = minute * num5;
      } else {
        money = num1 + (minute - num20) * num01;
      }
    }
    return (double) Math.round(money * num100) / num100;
  }
}


