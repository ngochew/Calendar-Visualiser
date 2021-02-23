import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class YearlyCalendar {

    private int year;

    private static Map<String, Integer> months = new LinkedHashMap<>();

    private final String[] WEEKDAYS = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private final int BIG_MONTH = 31;
    private final int SMALL_MONTH = 30;
    private final int LEAP_YEAR_FEBRUARY = 29;
    private final int NOT_LEAP_YEAR_FEBRUARY = 28;


    private YearlyCalendar(int year) {
        this.year = year;
        setupMonths(year);
    }

    private void setupMonths(int year) {
        months.put("January", BIG_MONTH);
        months.put("February",getFebruaryDays(year));
        months.put("March", BIG_MONTH);
        months.put("April", SMALL_MONTH);
        months.put("May", BIG_MONTH);
        months.put("June", SMALL_MONTH);
        months.put("July", BIG_MONTH);
        months.put("August", BIG_MONTH);
        months.put("September", SMALL_MONTH);
        months.put("October", BIG_MONTH);
        months.put("November", SMALL_MONTH);
        months.put("December", BIG_MONTH);
    }

    private int getFebruaryDays(int year) {
        if ((year % 100 != 0 && year % 4 == 0) || year % 400 == 0) {
            return LEAP_YEAR_FEBRUARY;
        }
        return NOT_LEAP_YEAR_FEBRUARY;
    }

    public static void visualizeCalendar(int year) {

        if (year < 1){
            throw new IllegalArgumentException();
        }

        YearlyCalendar yearlyCalendar = new YearlyCalendar(year);

        int currentMonthAsInteger = 0;

        System.out.println("-----------------" + year + "-----------------" + System.lineSeparator());

        for (Map.Entry<String, Integer> currentMonth : months.entrySet()) {

            Date date = new GregorianCalendar(year, currentMonthAsInteger, 1).getTime();
            int firstDayOfMonth = date.getDay();
            int currentDay = 1;

            final String WHITESPACES = "                                     ";
            String spacesToAdd = WHITESPACES.substring(0, firstDayOfMonth * 6);

            System.out.println(WHITESPACES.substring(0, 15) + currentMonth.getKey());
            System.out.println("Sun   Mon   Tue   Wed   Thu   Fri   Sat");

            System.out.print(spacesToAdd);
            for (int i = firstDayOfMonth; i < Calendar.DAY_OF_WEEK; i++) {
                System.out.print(currentDay + WHITESPACES.substring(0, 5));
                currentDay++;
            }

            System.out.println();

            while (currentDay <= currentMonth.getValue()) {
                for (int i = 0; i < Calendar.DAY_OF_WEEK; i++) {

                    if (currentDay < 10) {
                        System.out.print(currentDay + WHITESPACES.substring(0, 5));
                    } else {
                        System.out.print(currentDay + WHITESPACES.substring(0, 4));
                    }

                    currentDay++;

                    if (currentDay > currentMonth.getValue()) {
                        break;
                    }
                }
                System.out.println();
            }
            currentMonthAsInteger++;
            System.out.println("--------------------------------------");
        }
    }

    public static void main(String[] args) {
        YearlyCalendar.visualizeCalendar(2020);
    }
}
