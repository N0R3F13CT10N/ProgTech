package task2;

import java.util.Arrays;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(){
        day = 1;
        month = 1;
        year = 1;
    }

    public Date(int d, int m, int y){
        day = d;
        month = m;
        year = y;
    }

    public void next_year(){
        year += 1;
    }

    public void pre_pre_day(){
        if(day > 2)
            day -= 2;
        else {
            if(month == 1) {
                year -= 1;
                month = 12;
            }
            else
                month--;
            if (month == 2)
                if (year % 4 == 0 & (year % 100 != 0 | year % 400 == 0))
                    day = day - 2 + 29;
                else
                    day = day - 2 + 28;
            else if (Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(month))
                day = day - 2 + 31;
            else
                day = day - 2 + 30;
        }
    }

    public boolean month_day_match(){
        return day == month;
    }

    public void next_mont(){
        month = (month + 1) % 12;
    }

    @Override
    public String toString() {
        return day +"." + month + "." + year;
    }

}
