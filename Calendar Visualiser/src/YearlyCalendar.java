import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class YearlyCalendar {

    private int year;

    private static Map<String, Integer> months = new LinkedHashMap<>();

    private final String[] weekdays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    private YearlyCalendar(int year) {
        this.year = year;
        setupMonths(year);
    }

    private void setupMonths(int year) {
        months.put("January", 31);
        if ((year % 100 != 0 && year % 4 == 0) || year % 400 == 0) {
            months.put("February", 29);
        } else {
            months.put("February", 28);
        }
        months.put("March", 31);
        months.put("April", 30);
        months.put("May", 31);
        months.put("June", 30);
        months.put("July", 31);
        months.put("August", 31);
        months.put("September", 30);
        months.put("October", 31);
        months.put("November", 30);
        months.put("December", 31);
    }

    public static void visualizeCalendar(int year) {
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
