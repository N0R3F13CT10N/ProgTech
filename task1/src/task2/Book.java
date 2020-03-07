package task2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Book {
    private String name;
    private int pages;
    private double price;
    private int year;

    public Book(){
        name = "Sample text";
        pages = 0;
        price = 0;
        year = 0;
    }

    public Book(String n, int p, double pr, int y){
        name = n;
        pages = p;
        price = pr;
        year = y;
    }

    @Override
    public String toString() {
        return "task2.Book \""
                + name + '\"' + " published in "
                + year + " with " + pages +
                " pages costs " + price + "$";
    }

    public double page_price(){
        return price / pages;
    }

    public long days_passed(){
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.parse(year + "-" + "01-01");
        return ChronoUnit.DAYS.between(date, now);
    }
}
