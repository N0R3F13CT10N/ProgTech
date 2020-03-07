package task2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Employee {
    private String name;
    private int salary;
    private int year;

    public Employee() {
        name = "";
        salary = 0;
        year = 0;
    }

    public Employee(String name, int salary, int year) {
        this.name = name;
        this.salary = salary;
        this.year = year;
    }

    public int experience(){
        LocalDate now = LocalDate.now();
        return now.getYear() - year;
    }

    public long days_since_employment(){
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.parse(year + "-" + "01-01");
        return ChronoUnit.DAYS.between(date, now);
    }

    @Override
    public String toString() {
        return name + " earns " + salary + "$ and works since " + year;
    }
}

